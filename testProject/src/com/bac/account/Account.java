package com.bac.account;

import java.util.Date;

import com.bac.account.type.*;
import com.bac.account.util.AccountUtil;

/**
 * class to represent Bank Account
 * @author anikm1
 *
 */
public class Account {
	
	private int accountNumber;
	private long amountInCents;
	private String accountType;
	//private Date lastWithdraw;
	
	/**
	 * Constructor for the class
	 * @param acctNum int
	 * @param amount long
	 */
	public Account(int acctNum, long amount, String accountType)
	{
		this.accountNumber = acctNum;
		this.amountInCents = amount;
		this.accountType = accountType;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public synchronized long getAmount()
	{
		return this.amountInCents;
	}
	
	public synchronized void depositMoney(long amount) throws Exception
	{
		if(amount > 0)
		{
			this.amountInCents = this.amountInCents + amount;
		}
		else
		{
			throw new Exception("Invalid Amount");
		}
	}
	
	public synchronized long drwaMoney(long amount) throws Exception
	{
		if( (amount <= 0) || (amount > this.amountInCents) )
		{
			throw new Exception("Invalid Amount");
		}
		
		this.amountInCents = this.amountInCents - amount;
		
		/**if(AccountType.MAXI_SAVINGS.equals(accountType))
		{
			this.lastWithdraw = new Date();
		}**/
		
		return this.amountInCents;
	}
	
	public synchronized long calculateInterest()
	{
		long interestEarned = 0;
		
		if(AccountType.CHECKING.equals(accountType))
		{
			interestEarned = AccountUtil.calculateInterestEarnedPerAnnum(amountInCents, 0.1);
		}
		else if(AccountType.SAVINGS.equals(accountType))
		{
			if(this.amountInCents > 100000)
			{
				interestEarned =  AccountUtil.calculateInterestEarnedPerAnnum(100000, 0.1) + 
						AccountUtil.calculateInterestEarnedPerAnnum(this.amountInCents - 100000, 0.2);;
			}
			else
			{
				interestEarned = AccountUtil.calculateInterestEarnedPerAnnum(100000, 0.1);
			}
		}
		else if(AccountType.MAXI_SAVINGS.equals(accountType))
		{
			if(this.amountInCents > 200000)
			{
				interestEarned = AccountUtil.calculateInterestEarnedPerAnnum(100000, 2) + 
						AccountUtil.calculateInterestEarnedPerAnnum(100000, 5) +
						AccountUtil.calculateInterestEarnedPerAnnum(this.amountInCents - 200000, 10);
			}
			else if(this.amountInCents > 100000)
			{
				interestEarned = AccountUtil.calculateInterestEarnedPerAnnum(100000, 2) + 
						AccountUtil.calculateInterestEarnedPerAnnum(this.amountInCents - 100000, 5);
			}
			else
			{
				interestEarned = AccountUtil.calculateInterestEarnedPerAnnum(100000, 2);
			}
		}
		
		return interestEarned;
	}
}
