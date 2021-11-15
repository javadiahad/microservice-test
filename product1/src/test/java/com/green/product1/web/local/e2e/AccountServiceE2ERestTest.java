/**
 * 
 */
package com.green.product1.web.local.e2e;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.green.product1.util.JsonUtil.*;





import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.product1.domain.Account;
import com.green.product1.dtos.TransferRequest;
import com.green.product1.repositories.AccountRepository;

import io.restassured.http.ContentType;

/**
 * @author Ahad
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountServiceE2ERestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private AccountRepository ar;
	
	
	private static final String account="A300";
	private static final String account2="A400";



	
	@BeforeEach
	public void setup() throws Exception {
		//seed database		
		ar.save(new Account(account,"Bill Gates", "My cash in hand", BigDecimal.valueOf(100)));
		ar.save(new Account(account2,"Poor People", "Living expenses", BigDecimal.valueOf(200)));

	}
	
	
	@AfterEach
	public void tearDown() throws Exception {
		ar.deleteAll();
		
	}
	
	@Test
	public void shouldReturnAllAccounts() throws Exception {
		given().
		// when	some accounts	
		when().get(String.format("http://localhost:%s/api/accounts", port))
		// then:all accounts should should be returned.
				.then().log().all().assertThat().statusCode(is(200))
				.and().body(containsString("100")).and().body(containsString("200"));		
	}
		
	@Test
	public void shouldReturnBalance() throws Exception {
		given().//One account already exists
		pathParam("account",account).
		// when		
		when().get(String.format("http://localhost:%s/api/accounts/balance/{account}", port))
				// then:account current balance should be returned.
				.then().log().all().assertThat().statusCode(is(200))
				.and().body(containsString("100"));
		
	}

}
