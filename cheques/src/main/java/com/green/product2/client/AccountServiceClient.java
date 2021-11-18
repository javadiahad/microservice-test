/**
 * 
 */
package com.green.product2.client;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


/**
 * @author Ahad
 *
 */

@Component
public class AccountServiceClient {

	private RestTemplate restTemplate;

	@Autowired
	public AccountServiceClient(@Value("${accounts-service.base-url}") String baseUrl) {
        this.restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();	    
	}


	public Optional<TransferResponse> doTransfer(String accountFrom,String accountTo,BigDecimal amount) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		// request body 
		TransferRequest tr = new TransferRequest(accountFrom, accountTo, amount);		
		// build the request
		return Optional.of(restTemplate.postForEntity("/api/transfers",tr,TransferResponse.class ).getBody());
		
	}	

}
