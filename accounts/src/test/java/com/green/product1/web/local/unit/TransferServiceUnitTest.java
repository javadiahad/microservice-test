package com.green.product1.web.local.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.green.product1.domain.Account;
import com.green.product1.domain.Transaction;
import com.green.product1.dtos.TransferRequest;
import com.green.product1.exceptions.DataNotFoundException;
import com.green.product1.exceptions.ServiceBusinessException;
import com.green.product1.repositories.AccountRepository;
import com.green.product1.repositories.TransactionRepository;
import com.green.product1.web.services.AccountService;
import com.green.product1.web.services.TransferService;

/**
 * @author Ahad
 *
 */
@ExtendWith(MockitoExtension.class)
class TransferServiceUnitTest {

	@Mock
	private AccountRepository ar;
	
	@Mock	
	private TransactionRepository tr;
		
	@InjectMocks
	private TransferService ts;
	
	
	@Test
	@DisplayName("")
	final void givenTwoAccountAndAmountWhenTransferThenSucceedAndReturnId() {
		when(ar.findByCode("A100")).thenReturn(Optional.of(new Account( "A100","Bill Gates", "My cash in hand", BigDecimal.ONE)));
		when(ar.findByCode("A200")).thenReturn(Optional.of(new Account( "A200","Poor People", "Living expenses", BigDecimal.ONE)));
		when(tr.save(any())).thenReturn(new Transaction(1,BigDecimal.ONE));
		Transaction res = ts.transfer(new TransferRequest("A100", "A200", BigDecimal.ONE));
		assertNotNull(res);
		assertNotNull(res.getId());
	}
		
	
	@Test
	@DisplayName("")
	final void givenWrongAccountWhenTransferThenShouldThrowException() {
		when(ar.findByCode("A100")).thenReturn(Optional.empty());		
		DataNotFoundException ex = assertThrows(DataNotFoundException.class,()-> ts.transfer(new TransferRequest("A100", "A200", BigDecimal.ONE)));
		assertEquals(String.format("Account %s not found!","A100"),ex.getMessage());
		assertEquals("ACC-1000",ex.getErrorCode());
	}


}
