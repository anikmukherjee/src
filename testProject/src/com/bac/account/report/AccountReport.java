package com.bac.account.report;

import java.util.List;

import com.bac.customer.*;
import com.bac.account.*;

public class AccountReport {
	
	/** method to show report showing the 
	list of customers and how many accounts they have
	*/
	public static void getAccountInfo(List<Customer> customers)
	{
		if(customers != null && customers.size() > 0)
		{
			for(Customer customer: customers)
			{
				//synchronized(customer)
				//{
					List<Account> accounts = customer.getAccounts();
					
					if(accounts != null)
					{
						System.out.println("Customer : " + customer.getCustomerId() 
								+ " has " + accounts.size() + " account(s)");
					}
					else
					{
						System.out.println("Customer : " + customer.getCustomerId()
								+ " does not have any account");
					}
				//}
			}
		}
	}
	
	/**
	 * Method to get a report showing the total 
	 *	interest paid by the bank on all accounts
	 **/
	 public long getAllInterestInfo(List<Customer> customers)
	 {
		 long interestPaidInCents = 0;
		 
		 if(customers != null && customers.size() > 0)
		 {
			for(Customer customer: customers)
			{
				//synchronized(customer)
				//{
					List<Account> accounts = customer.getAccounts();
					
					if(accounts != null && accounts.size() > 0)
					{
						for(Account account : accounts)
						{
							interestPaidInCents += account.calculateInterest();
						}
					}
				//}
			}
		 }
		 
		 return interestPaidInCents;
	 }
}
