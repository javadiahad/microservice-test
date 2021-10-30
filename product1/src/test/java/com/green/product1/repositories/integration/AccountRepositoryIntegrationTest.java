/**
 * 
 */
package com.green.product1.repositories.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.green.product1.domain.Account;
import com.green.product1.repositories.AccountRepository;

/**
 * @author Ahad
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryIntegrationTest {

	@Autowired
	private AccountRepository ar;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		ar.deleteAll();
	}

	@Test
	public void givenAccountFindByCodeShouldReturnResult() {
		ar.save(new Account("A110", BigDecimal.ZERO));
		Optional<Account> acc = ar.findByCode("A110");
		assertThat(acc.isPresent(),is(equalTo(true)));
		assertThat(acc.get(),hasProperty("balance",equalTo(BigDecimal.ZERO)));		
	}

}
