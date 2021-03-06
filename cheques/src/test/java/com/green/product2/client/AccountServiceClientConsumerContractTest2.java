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
		properties = "accounts-service.base-url:http://localhost:8082", classes = AccountServiceClient.class)
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "transferservice", port = "8082")
public class AccountServiceClientConsumerContractTest2 {

	@Autowired
	private AccountServiceClient accountService;

		
	
	@Pact(consumer = "chequesclient")
	public RequestResponsePact transferExceptionalPact(PactDslWithProvider builder) {
		Map<String, String> headers = new HashMap();
		headers.put("Content-Type", "application/json");

		PactDslJsonBody requestBody = new PactDslJsonBody();
		requestBody.stringType("accountFrom","A100")
		.stringType("accountTo","A300")
		.numberType("amount",BigDecimal.valueOf(101).setScale(2)).closeObject();		

		PactDslJsonBody responseBody = new PactDslJsonBody();		
		responseBody.stringType("errorCode", "ACC-1000")
		.closeObject();

		return builder.given("Two Accounts exist,source account balance is insufficient")
				.uponReceiving("A request for transfering an money between two accounts")
				.path("/api/transfers")
				.method("POST")
				.headers(headers)
				.body(requestBody)
				.willRespondWith()
				.status(400)
				//.headers(headers)	
				.body(responseBody)
				.toPact();
	}
	
	
	
	@Test
	//@Disabled
	@PactTestFor(pactMethod = "transferExceptionalPact")
	public void testDoTransferExceptional() {
		HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () ->
		accountService.doTransfer("A100", "A300", BigDecimal.valueOf(101).setScale(2))
		);
		assertThat(HttpStatus.BAD_REQUEST, is(exception.getStatusCode()));

	}
	
	
	
		
}
