/**
 * 
 */
package com.green.product1.web.local.contract;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.green.product1.util.JsonUtil.*;


import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.product1.domain.Account;
import com.green.product1.domain.Transaction;
import com.green.product1.dtos.TransferRequest;
import com.green.product1.repositories.AccountRepository;
import com.green.product1.repositories.TransactionRepository;
import com.green.product1.web.services.TransferService;

/**
 * @author Ahad
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TransferService.class)
public class TransferServiceAPITest {
	private static String expectedContentType = "application/json";

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AccountRepository ar;

	@MockBean
	private TransactionRepository tr;

	
	
	@Test
	@DisplayName("")
	final void givenTwoAccountAndAmountWhenDoTransferThenSucceedAndReturnId() throws Exception {
		when(ar.findByCode("A100")).thenReturn(Optional.of(new Account("A100", BigDecimal.ONE)));
		when(ar.findByCode("A200")).thenReturn(Optional.of(new Account("A200", BigDecimal.ONE)));
		when(tr.save(any())).thenReturn(new Transaction(1, BigDecimal.ONE));
		mvc.perform(post("/api/transfers").content(asJson(new TransferRequest("A100", "A200", BigDecimal.ONE)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(expectedContentType))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber()).andReturn().getResponse();
	}
	

	@Test
	@DisplayName("")
	final void givenWrongAccountWhenTransferThenShouldThrowException() throws Exception {
		when(ar.findByCode("A100")).thenReturn(Optional.empty());
		when(ar.findByCode("A200")).thenReturn(Optional.of(new Account("A200", BigDecimal.ONE)));
		when(tr.save(any())).thenReturn(new Transaction(1, BigDecimal.ONE));
		mvc.perform(post("/api/transfers").content(asJson(new TransferRequest("A100", "A200", BigDecimal.ONE)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound())
				.andReturn().getResponse();

	}
	
	@Test
	@DisplayName("")
	final void givenBigAmountWhenTransferThenShouldThrowInsufficentException() throws Exception {
		when(ar.findByCode("A100")).thenReturn(Optional.of(new Account("A100", BigDecimal.ONE)));
		when(ar.findByCode("A200")).thenReturn(Optional.of(new Account("A200", BigDecimal.valueOf(100))));
		when(tr.save(any())).thenReturn(new Transaction(1, BigDecimal.ONE));
		mvc.perform(post("/api/transfers").content(asJson(new TransferRequest("A100", "A200", BigDecimal.valueOf(100))))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().is4xxClientError())
				.andReturn().getResponse();

	}
	
	
	

}
