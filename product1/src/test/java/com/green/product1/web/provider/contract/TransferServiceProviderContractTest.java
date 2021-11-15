/**
 * 
 */
package com.green.product1.web.provider.contract;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
@Provider("transferservice")
@PactFolder("../pacts")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferServiceProviderContractTest {

	@LocalServerPort
	private int port;

	@MockBean
	private AccountRepository ar;

	@MockBean
	private TransactionRepository tr;

	@Mock
	private TransferService accountService;

	@BeforeEach
	void before(PactVerificationContext context) throws Exception {
		context.setTarget(new HttpTestTarget("localhost", port));
	}

	@TestTemplate
	@ExtendWith(PactVerificationInvocationContextProvider.class)
	void testTemplate(PactVerificationContext context) {
		context.verifyInteraction();
	}

	@State("Two Account with sufficient balance exists")
	public void transfer() {
		when(ar.findByCode("A100")).thenReturn(Optional.of(new Account("A100","Bill Gates", "My cash in hand", BigDecimal.valueOf(100))));
		when(ar.findByCode("A200")).thenReturn(Optional.of(new Account("A200","Poor People", "Living expenses", BigDecimal.ONE)));
		when(tr.save(any())).thenReturn(new Transaction(111, BigDecimal.valueOf(100).setScale(2)));
		//It's also possible to mock accountService itself 
		 //when(accountService.transfer(new TransferRequest("A100", "A200", BigDecimal.valueOf(100))
        //.thenReturn(new Transaction(111, BigDecimal.valueOf(100)));
	}
	
	@State("Two Account exists,source account balance is Insufficient")
	public void transferRaiseException() {
		when(ar.findByCode("A100")).thenReturn(Optional.of(new Account("A100","Poor People", "Living expenses", BigDecimal.valueOf(100))));
		when(ar.findByCode("A300")).thenReturn(Optional.of(new Account("A300","Bill Gates", "My cash in hand", BigDecimal.ONE)));
		when(tr.save(any())).thenReturn(new Transaction(111, BigDecimal.valueOf(101)));		
	}


}
