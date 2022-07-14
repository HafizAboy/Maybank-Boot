package com.maybank.boot.service;

import java.util.List;

import com.maybank.boot.model.Customers;

/**
 * @author Hafiz
 * @version 0.01
 */
public interface CustomerService {

    List<Customers> findAllCustomers();
    Customers findByUsername(String username);
    Customers findByCustomerId(String customerId);
	Customers saveCustomer(Customers customer) throws Exception;
	Customers updateCustomer(Customers customer) throws Exception;
	Customers deleteByUsername(String username) throws Exception;
	Customers deleteByCustomerId(String customerId) throws Exception;
	void deleteCustomer(Customers customer) throws Exception;
}
