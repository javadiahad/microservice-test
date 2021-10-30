/**
 * 
 */
package com.green.product2.client;

import java.math.BigDecimal;

/**
 * @author Ahad
 *
 */
public class TransferResponse {
	
	private Integer id;
	
	private BigDecimal amount;
	
	

	public TransferResponse() {
		super();
	}



	public TransferResponse(Integer id, BigDecimal amount) {
		super();
		this.id = id;
		this.amount = amount;
	}
	
	

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
		return "PaymentResponse [id=" + id + ", amount=" + amount + "]";
	}
	
	
	

}
