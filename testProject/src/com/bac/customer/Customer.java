package com.bac.customer;

import java.util.ArrayList;
import java.util.List;

import com.bac.account.Account;

/**
 * Customer class to hold customer and accounts info
 * @author anikm1
 *
 */
public class Customer {
	
	private long customerId;
	private List<Account> accounts;
	
	public Customer(long customerId)
	{
		this.customerId = customerId;
		accounts = new ArrayList<Account>();
	}
	
	public long getCustomerId()
	{
		return customerId;
	}
	
	public synchronized void setAccounts(List<Account> accounts)
	{
		this.accounts = accounts;
	}
	
	public synchronized List<Account> getAccounts()
	{
		return this.accounts;
	}
	
	public synchronized void addAccount(Account account)
	{
		accounts.add(account);
	}
}
