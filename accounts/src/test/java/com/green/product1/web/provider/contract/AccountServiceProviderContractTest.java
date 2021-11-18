/**
 * 
 */
package com.green.product1.web.provider.contract;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
//import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.green.product1.domain.Account;
import com.green.product1.domain.Transaction;
import com.green.product1.dtos.TransferRequest;
import com.green.product1.repositories.AccountRepository;
import com.green.product1.repositories.TransactionRepository;
import com.green.product1.web.services.AccountService;
import com.green.product1.web.services.TransferService;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;

/**
 * @author Ahad
 *
 */

@ExtendWith(SpringExtension.class)
@Provider("accountservice")
@PactFolder("../pacts")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountServiceProviderContractTest {

	@LocalServerPort
	private int port;

	@MockBean
	private AccountRepository ar;

	@Mock
	private AccountService accountService;

	@BeforeEach
	void before(PactVerificationContext context) throws Exception {
		context.setTarget(new HttpTestTarget("localhost", port));
	}

	@TestTemplate
	@ExtendWith(PactVerificationInvocationContextProvider.class)
	void testTemplate(PactVerificationContext context) {
		context.verifyInteraction();
	}

	@State("Two accounts exist")
	public void findAll() {
		List<Account> al = new ArrayList<>();
		al.add(new Account("A100", "Bill Gates", "My cash in hand", BigDecimal.valueOf(100000000)));
		al.add(new Account("A200", "Bill Gates", "My saving account", BigDecimal.valueOf(200000000)));
		when(ar.findAll()).thenReturn(al);
	}

}
