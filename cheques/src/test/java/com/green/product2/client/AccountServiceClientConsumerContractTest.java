package com.green.product2.client;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
//import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import static org.junit.jupiter.api.Assertions.assertThrows;



@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, 
		properties = "accounts-service.base-url:http://localhost:8081", classes = AccountServiceClient.class)
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "transferservice", port = "8081")
public class AccountServiceClientConsumerContractTest {

	@Autowired
	private AccountServiceClient accountService;

		
	@Pact(consumer = "chequesclient")
	public RequestResponsePact transferPact(PactDslWithProvider builder) {
		Map<String, String> headers = new HashMap();
	    headers.put("Content-Type", "application/json");

		PactDslJsonBody requestBody = new PactDslJsonBody();
		requestBody.stringType("accountFrom","A100")
			    .stringType("accountTo","A200")
			    .numberType("amount",BigDecimal.valueOf(100)).closeObject();		
		
		PactDslJsonBody responseBody = new PactDslJsonBody();		
		responseBody.numberType("id", 111L)
		.decimalType("amount", BigDecimal.valueOf(100))
		.closeObject();
		
		return builder.given("Two Accounts with sufficient balance exist")
				.uponReceiving("A request for transfering money between two accounts")
				.path("/api/transfers")
				.method("POST")
				.headers(headers)
				.body(requestBody)
				//.body("{\"accountFrom\":\"A100\",\"accountTo\":\"A200\",\"amount\":100}")
				.willRespondWith()
				.status(200)
				.headers(headers)
				.body(responseBody)
				//.body("{\"id\":111,\"amount\":100}")
				.toPact();
	}

	
	@Test
	//@Disabled
	@PactTestFor(pactMethod = "transferPact")
	public void testDoTransfer() {
		Optional<TransferResponse> res = accountService.doTransfer("A100", "A200", BigDecimal.valueOf(100));
		assertThat("Service reponse is null", res.isPresent());
		assertThat(111, is(equalTo(res.get().getId())));
		assertThat(BigDecimal.valueOf(100), is(equalTo(res.get().getAmount())));
	}

	
		
}
