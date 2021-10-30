package com.green.product1.web.services;

import java.math.BigDecimal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.green.product1.domain.Account;
import com.green.product1.domain.Transaction;
import com.green.product1.dtos.TransferRequest;
import com.green.product1.exceptions.DataNotFoundException;
import com.green.product1.repositories.AccountRepository;
import com.green.product1.repositories.TransactionRepository;


/**
 * @author Ahad
 *
 */
@RestController
@RequestMapping("accounts")
public class AccountService  {
	
	@Value("${myConfig.value:true}") 
	private  boolean myCofigIsEnabled;
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	
	
	
	@PostMapping("/transfer")
	public Transaction transfer( @RequestBody TransferRequest req ) {
		Account source = accountRepository.findByCode(req.getAccountFrom())
				.orElseThrow(() -> new DataNotFoundException("ACC-1000","Account %s not found!",req.getAccountFrom()));
		
		Account target = accountRepository.findByCode(req.getAccountTo())
				.orElseThrow(()->new DataNotFoundException("ACC-1000","Account %s not found!",req.getAccountTo())) ;

		Transaction trn=new Transaction(req.getAmount());
		trn.withdrawAccount(source);
		trn.depositAccount(target);
		accountRepository.save(source);
		accountRepository.save(target);
		return transactionRepository.save(trn);
	}
	
	
	@GetMapping("/balance/{accountCode}")
	public BigDecimal getBalance(@PathVariable("accountCode") String accountCode) {
		Account account = accountRepository.findByCode(accountCode)
				.orElseThrow(()->new DataNotFoundException("ACC-1000","Account %s not found!",accountCode)) ;
		
		return account.getBalance();
	}
}