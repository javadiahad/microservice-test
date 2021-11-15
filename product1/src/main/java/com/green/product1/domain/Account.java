package com.green.product1.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.product1.exceptions.ServiceBusinessException;

/**
 * @author Ahad
 *
 */
@Entity
public class Account {

	@Id
	@GeneratedValue
	@JsonIgnore
	private Integer id;

	private String code;

	private BigDecimal balance;

	private String holderName;

	private String name;

	public Account() {
		super();
	}

	public BigDecimal widthrawFromAccount(BigDecimal amount) {
		if (balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0)
			throw new ServiceBusinessException("ACC-1000", "Insufficient Balance for account %s !", code);
		balance = balance.subtract(amount);
		return balance;

	}

	public BigDecimal depositIntoAccount(BigDecimal amount) {
		balance = balance.add(amount);
		return balance;

	}

	public Account(Integer id, String code, String holderName, String name, BigDecimal balance) {
		super();
		this.id = id;
		this.code = code;
		this.balance = balance;
		this.holderName = holderName;
		this.name = name;
	}

	public Account(String code, String holderName, String name, BigDecimal balance) {
		super();
		this.code = code;
		this.balance = balance;
		this.holderName = holderName;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getBalance() {
		return balance;
	}
	
	

	/**
	 * @return the holderName
	 */
	public String getHolderName() {
		return holderName;
	}

	/**
	 * @param holderName the holderName to set
	 */
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Account other = (Account) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
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
		return "Account [id=" + id + ", code=" + code + ", balance=" + balance + "]";
	}

}
