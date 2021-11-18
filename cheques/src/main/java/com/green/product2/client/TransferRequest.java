/**
 * 
 */
package com.green.product2.client;

import java.math.BigDecimal;

/**
 * @author Ahad
 *
 */
public class TransferRequest {
	String accountFrom ;
	String accountTo ;
	BigDecimal amount;
	
	
	
	
	public TransferRequest(String accountFrom, String accountTo, BigDecimal amount) {
		super();
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.amount = amount;
	}

		
	
	/**
	 * @return the accountFrom
	 */
	public String getAccountFrom() {
		return accountFrom;
	}






	/**
	 * @param accountFrom the accountFrom to set
	 */
	public void setAccountFrom(String accountFrom) {
		this.accountFrom = accountFrom;
	}






	/**
	 * @return the accountTo
	 */
	public String getAccountTo() {
		return accountTo;
	}






	/**
	 * @param accountTo the accountTo to set
	 */
	public void setAccountTo(String accountTo) {
		this.accountTo = accountTo;
	}






	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}






	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}






	@Override
	public String toString() {
		return "TransferRequest [accountFrom=" + accountFrom + ", accountTo=" + accountTo + ", amount=" + amount + "]";
	}	
	
	
}
