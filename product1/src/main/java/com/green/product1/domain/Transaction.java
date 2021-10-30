/**
 * 
 */
package com.green.product1.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Ahad
 *
 */
@Entity
public class Transaction {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private BigDecimal amount ;
	
	
	

	public Transaction(BigDecimal amount) {
		super();		
		this.amount = amount;
	}
	
	

	public Transaction(Integer id, BigDecimal amount) {
		super();
		this.id = id;
		this.amount = amount;
	}



	public BigDecimal withdrawAccount(Account acc) {
		return acc.widthrawFromAccount(amount);		
	}
	
	public BigDecimal depositAccount(Account acc) {
		return acc.depositIntoAccount(amount);		
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + "]";
	}
	
	
	
	
	

}
