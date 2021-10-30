package com.green.product1.domain.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.green.product1.domain.Account;
import com.green.product1.exceptions.ServiceBusinessException;


/**
 * @author Ahad
 *
 */
class AccountTest {

	private  Account acc;

	@BeforeEach
	void setUp() throws Exception {
		//given
		acc=new Account(1, "A1", new BigDecimal(10));
	}

	
	@Test
	final void testWidthrawFromAccount() {
		//when//then
		assertThat(BigDecimal.ZERO,is(acc.widthrawFromAccount(new BigDecimal(10))));
				
	}

	
	@Test
	final void testWidthrawFromAccountInsufficientBalance() {
		//when//then
		Assertions.assertThrows(ServiceBusinessException.class, () -> {
			acc.widthrawFromAccount(new BigDecimal(12));
		});

	}
	
	@Test
	final void testDepositIntoAccount() {
		//when//then
		assertThat(BigDecimal.valueOf(20),is(acc.depositIntoAccount(new BigDecimal(10))));
				
	}
	
}
