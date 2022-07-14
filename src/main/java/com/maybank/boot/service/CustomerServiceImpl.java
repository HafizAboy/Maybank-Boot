package com.maybank.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maybank.boot.model.Customers;
import com.maybank.boot.repository.CustomerRepository;

/**
 * @author Hafiz
 * @version 0.01
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public Customers saveCustomer(Customers customer) throws Exception {
		return customerRepo.save(customer);
	}

	@Override
	public List<Customers> findAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Customers findByUsername(String username) {
		return customerRepo.findByUsername(username);
	}

	@Override
	public Customers findByCustomerId(String customerId) {
		return customerRepo.findByCustomerId(customerId);
	}

	@Override
	public Customers updateCustomer(Customers customer) throws Exception {
		return saveCustomer(customer);
	}

	@Override
	public Customers deleteByUsername(String username) throws Exception {
		return customerRepo.deleteByUsername(username);
	}

	@Override
	public Customers deleteByCustomerId(String customerId) throws Exception {
		return customerRepo.deleteByCustomerId(customerId);
	}

	@Override
	public void deleteCustomer(Customers customer) throws Exception {
		customerRepo.delete(customer);
	}
}
