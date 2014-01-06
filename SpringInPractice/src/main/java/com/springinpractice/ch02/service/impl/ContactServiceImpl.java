package com.springinpractice.ch02.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.springinpractice.ch02.model.Contact;
import com.springinpractice.ch02.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

	private static final String CREATE_SQL =
			"insert into contact (last_name, first_name, mi, email) " +
			"values (:lastName, :firstName, :mi, :email)";
	
	private static final String FIND_ALL_SQL =
			"select id, last_name, first_name, mi, email from contact";
	
	private static final String FIND_ALL_BY_EMAIL_LIKE_SQL =
			"select id, last_name, first_name, mi, email from conact " +
			"where email like :email";

	private static final String FIND_ONE_SQL =
			"select id, last_name, first_name, mi, email from contact " +
			"where id = :id";
	
	private static final String UPDATE_SQL =
			"update contact set last_name = :lastName, " +
			"first_name = :firstName, mi = :mi, email = :email " +
			"where id = :id";
	
	private static final String DELETE_SQL =
			"delete from contact where id = :id";
	
	@Inject private NamedParameterJdbcOperations jdbcTemplate;
	@Inject private ContactRowMapper contactRowMapper;
	
	public void createContact(Contact contact) {
		
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("lastName", contact.getLastName())
			.addValue("firstName", contact.getFirstName())
			.addValue("mi", contact.getMiddleInitial())
			.addValue("email", contact.getEmail());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(CREATE_SQL, params, keyHolder);
		contact.setId(keyHolder.getKey().longValue());
		
	} // end createContact()
	
	
	public List<Contact> getContacts() {
		
		return jdbcTemplate.query(
				FIND_ALL_SQL, new HashMap<String, Object>(), contactRowMapper);
		
	} // end getContacts()
	
	
	public List<Contact> getContactsByEmail(String email) {
		
		SqlParameterSource params =
				new MapSqlParameterSource("email", "%" + email + "%");
		return jdbcTemplate.query(
				FIND_ALL_BY_EMAIL_LIKE_SQL, params, contactRowMapper);
		
	} // end getContractsByEmail()
	
	
	public Contact getContact(Long id) {
		
		SqlParameterSource params =
				new MapSqlParameterSource("id", id);
		return jdbcTemplate.queryForObject(
				FIND_ONE_SQL, params, contactRowMapper);
		
	} // end getContact()
	
	
	public void updateContact(Contact contact) {
		
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("id", contact.getId())
			.addValue("lastName", contact.getLastName())
			.addValue("firstName", contact.getFirstName())
			.addValue("mi", contact.getMiddleInitial())
			.addValue("email", contact.getEmail());
		jdbcTemplate.update(UPDATE_SQL, params);
		
	} // end updateContact()
	
	
	public void deleteContact(Long id) {
		
		jdbcTemplate.update(DELETE_SQL, 
				new MapSqlParameterSource("id", id));
		
	} // end deleteContact()
	
} // end ContactServiceImpl class

