/**
 * 
 */
package com.green.product1.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.green.product1.domain.Account;

/**
 * @author Ahad
 *
 */
public interface AccountRepository extends CrudRepository<Account, Integer> {
	
	public Optional<Account> findByCode(String code);

}
