// Source project: sip01, branch: 04 (Maven Project)
package com.springinpractice.ch01;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springinpractice.ch01.model.Account;
import com.springinpractice.ch01.service.AccountService;

public class ConsoleApp {
	
	public static void main(String args[]) throws Exception {
		
		@SuppressWarnings("resource")
		ApplicationContext appCtx =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		AccountService accountService =
				(AccountService)appCtx.getBean("accountService");
		
		List<Account> delinquentAccounts = accountService.findDeliquentAccounts();
				
		for (Account a : delinquentAccounts) {
			System.out.println(a.getAccountNo());
		} // end for loop
		
		System.out.println("Done running ConsoleApp");
		
		
	} // end main

} // end ConsoleApp class
