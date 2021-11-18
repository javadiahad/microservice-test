/**
 * 
 */
package com.green.product1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.green.product1.domain.Transaction;

/**
 * @author Ahad
 *
 */
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
