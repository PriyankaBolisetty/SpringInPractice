// Source project: sip01, branch: 04 (Maven Project)
package com.springinpractice.ch01.model;

import java.math.BigDecimal;
import java.util.Date;

public class Account {

	private String accountNo;
	private BigDecimal balance;
	private Date lastPaidOn;
	
	
	public Account(String accountNo, BigDecimal balance, Date lastPaidOn) {
		
		this.accountNo = accountNo;
		this.balance = balance;
		this.lastPaidOn = lastPaidOn;
		
	} // end constructor
	
	
	public String getAccountNo() {
		
		return accountNo;
		
	} // end getAccontNo()
	
	
	public BigDecimal getBalance() {
		
		return balance;
		
	} // end getBalance()
	
	
	public Date getLastPaidOn() {
		
		return lastPaidOn;
		
	} // end getLastPaidOn()
	
} // end Account class
