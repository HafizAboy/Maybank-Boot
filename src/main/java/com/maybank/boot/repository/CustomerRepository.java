package com.maybank.boot.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maybank.boot.model.Customers;

/**
 * @author Hafiz
 * @version 0.01
 */
public interface CustomerRepository extends JpaRepository<Customers, UUID> {

	Customers findByUsername(String username);
	Customers deleteByUsername(String username) throws Exception;
	Customers findByCustomerId(String customerId);
	Customers deleteByCustomerId(String customerId) throws Exception;
}
