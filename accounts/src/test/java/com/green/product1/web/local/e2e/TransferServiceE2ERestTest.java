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
public class TransferServiceE2ERestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private AccountRepository ar;
	
	
	private static final String source="A100";
	private static final String target="A200";


	
	@BeforeEach
	public void setup() throws Exception {
		//seed database
		ar.save(new Account(source,"Bill Gates", "My cash in hand", BigDecimal.valueOf(80)));
		ar.save(new Account(target,"Poor People", "Living expenses", BigDecimal.valueOf(20)));
	}
	
	
	@AfterEach
	public void tearDown() throws Exception {
		ar.deleteAll();
		
	}
	
	
	@Test
	@DisplayName("Scenario:transfering money")	
	public void shouldTransferMoney() throws Exception {
		
		// given:The Account Holder login in to system 
		// given: two account, account "A100" with balance=80$ and account "A200" with balance=20$
		// and the Account Holder selects account number "A100" as source and "A200" as target 
		// enters $30
		given()
		.contentType(ContentType.JSON)
		.body(asJson(new TransferRequest("A100", "A200", BigDecimal.valueOf(30))))
		
		// when: the Account Holder click transfer button
		.when().post(String.format("http://localhost:%s/api/transfers", port))
		
		// then:30$ should be transfered and tracking id should be returned
		// then: account "A100" new balance should be 50$
		// then: account "A200" new balance should be 50$
		.then().log().all().statusCode(is(200)).assertThat().body("id", notNullValue());
		//The following part assertion of can be omitted
		assertThat(BigDecimal.valueOf(50).setScale(2),is(ar.findByCode(source).get().getBalance()));
		assertThat(BigDecimal.valueOf(50).setScale(2),is(equalTo(ar.findByCode(target).get().getBalance())));
	}		
	

}
