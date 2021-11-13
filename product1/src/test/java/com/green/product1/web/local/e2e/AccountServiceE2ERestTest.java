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


	
	@BeforeEach
	public void setup() throws Exception {
		//seed database		
		ar.save(new Account(account, BigDecimal.valueOf(100)));
	}
	
	
	@AfterEach
	public void tearDown() throws Exception {
		ar.deleteAll();
		
	}
	
	
		
	@Test
	public void shouldReturnBalance() throws Exception {
		given().
		pathParam("account",account).
		// when		
		when().get(String.format("http://localhost:%s/accounts/balance/{account}", port))
				// then:requested amount should be transfered
				// then account source new balance=50$ and account target new balance=50$
				.then().log().all().assertThat().statusCode(is(200))
				.and().body(containsString("100"));
		
	}

}
