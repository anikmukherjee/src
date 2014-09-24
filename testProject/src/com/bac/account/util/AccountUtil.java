package com.bac.account.util;

import com.bac.account.Account;

public class AccountUtil {
	
	public static long calculateInterestEarnedPerAnnum(long amount, double percent)
	{
		return (long)(amount * percent);
	}
	
	public static long calculateInterestEarnedPerAnnum(long amount, int percent)
	{
		return amount * percent;
	}
	
	public synchronized void transfer(Account fromAccount, 
			Account toAccount, long amount) throws Exception
	{		
		if(amount > 0)
		{
			synchronized(fromAccount)
			{
				if(fromAccount.getAmount() >= amount)
				{
					synchronized(toAccount)
					{
						fromAccount.drwaMoney(amount);
						toAccount.depositMoney(amount);
					}
				}
				else
				{
					throw new Exception("Not enough balance, "
							+ "unable to transfer money");
				}
			}
		}
		else
		{
			throw new Exception("Invalid Amount");
		}
	}
	
}
