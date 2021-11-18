/**
 * 
 */
package com.green.product1.domain.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.green.product1.domain.Account;
import com.green.product1.domain.Transaction;

/**
 * @author Ahad
 *
 */
class TransactionTest {
	private  Account acc;
	private  Transaction trn;



	@BeforeEach
	void setUp() throws Exception {
		//given
		acc=new Account(1, "A1","Bill Gates", "My cash in hand", new BigDecimal(20));
		trn=new Transaction(BigDecimal.valueOf(20));
	}

	/**
	 * Test method for {@link com.green.product1.domain.Transaction#withdrawAccount(com.green.product1.domain.Account)}.
	 */
	@Test
	final void testWithdrawAccount() {
		assertThat(BigDecimal.ZERO,is(trn.withdrawAccount(acc)));
	}

	/**
	 * Test method for {@link com.green.product1.domain.Transaction#depositAccount(com.green.product1.domain.Account)}.
	 */
	@Test
	final void testDepositAccount() {
		assertThat(BigDecimal.valueOf(40),is(trn.depositAccount(acc)));
	}

}
