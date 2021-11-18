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
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.green.product1.domain.Transaction;
import com.green.product1.repositories.TransactionRepository;

/**
 * @author Ahad
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionRepositoryIntegrationTest {

	@Autowired
	private TransactionRepository tr;

	@AfterEach
	void tearDown() throws Exception {
		tr.deleteAll();
	}

	@Test
	final void givenTransactionWhenSavedShouldBePersisted() {
		Transaction trn = tr.save(new Transaction(BigDecimal.ONE));
		Optional<Transaction> trnres = tr.findById(trn.getId());
		assertThat(trnres.isPresent(), is(equalTo(true)));
		assertThat(trnres.get(), hasProperty("amount", equalTo(BigDecimal.ONE)));
	}
}
