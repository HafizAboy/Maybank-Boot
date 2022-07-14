package com.maybank.boot.api;

import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maybank.boot.constant.ErrorEnum;
import com.maybank.boot.exception.WebserviceException;
import com.maybank.boot.model.Customers;
import com.maybank.boot.service.CustomerService;
import com.maybank.boot.view.CustomerView;

import io.swagger.annotations.ApiOperation;

/**
 * @author Aboy
 * @version 0.01
 */
@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerService customerService;

	/**
	 * Fetch a list of customers
	 * @return a list of customers
	 * @throws Exception 
	 */
	@RequestMapping(path="/allCustomers", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Fetch all customers")
	public ResponseEntity<?> getAllCustomers() throws Exception {
		List<Customers> customers = (List<Customers>) customerService.findAllCustomers();

		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	/**
	 * Finds a customer by <code>username</code>
	 * 
	 * @param username customer's username
	 * 
	 * @return the {@link Customers} object
	 * @throws Exception 
	 */
	@RequestMapping(path = "/getCustomerByUsername/{username}", 
			method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a customer")
	public ResponseEntity<?> getCustomerByUsername(@PathVariable String username) throws Exception {
		Customers customer = new Customers();
		customer = customerService.findByUsername(username);
		
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	/**
	 * Finds a customer by <code>customerId</code>
	 * 
	 * @param customerId customer's customerId
	 * 
	 * @return the {@link Customers} object
	 * @throws Exception 
	 */
	@RequestMapping(path = "/getCustomerByCustomerId/{customerId}", 
			method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a customer")
	public ResponseEntity<?> getCustomerByCustomerId(@PathVariable String customerId) throws Exception {
		Customers customer = new Customers();
		customer = customerService.findByCustomerId(customerId);
		
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	/**
	 * Add a customer
	 * 
	 * @param customer
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(path = "/addCustomer",
			method = RequestMethod.POST,
			consumes =  MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create a new customer")
	public ResponseEntity<Customers> addCustomer(@RequestBody CustomerView customer) throws Exception {

		Customers savedCustomer = new Customers();
		Customers customerObj = new Customers();

		// validate the input
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<CustomerView>> violations = validator.validate(customer);

		logger.error("size of violations : " + violations.size());

		for (ConstraintViolation<CustomerView> constraintViolation : violations) {
			logger.error("constraintViolation: field \"" + constraintViolation.getPropertyPath() + "\"," + constraintViolation.getMessage());
			throw new WebserviceException(ErrorEnum.REQUIRED_ELEMENT_MISSING, constraintViolation.getMessage());
		}
		// validation - End

		if(violations.size() == 0) {
			logger.info("Validation Success!");
			customerObj.setCustomerId(customer.getCustomerId());
			customerObj.setEmail(customer.getEmail());
			customerObj.setFirstName(customer.getFirstName());
			customerObj.setLastName(customer.getLastName());
			customerObj.setUsername(customer.getUsername());

			try {
				logger.info("Saving Customer");
				savedCustomer =  customerService.saveCustomer(customerObj);
				logger.info("Customer creation completed");
			}catch (Exception e) {
				logger.error("Error:- Unable to create customer");
				throw new WebserviceException(ErrorEnum.SAVING_UNSUCCESSFUL, ErrorEnum.SAVING_UNSUCCESSFUL.getDescription());
			}
		}
		return new ResponseEntity<Customers>(savedCustomer, HttpStatus.CREATED);
	}

	/**
	 * Updates the customer
	 * 
	 * @param customer
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(path = "/updateCustomer",
			method = RequestMethod.PUT)
	@ApiOperation(value = "Update a customer")
	public ResponseEntity<Customers> updateCustomer(@RequestBody CustomerView customer) throws Exception {

		Customers savedCustomer = new Customers();
		Customers customerFindByUsername = customerService.findByUsername(customer.getUsername());
		// validate the input
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<CustomerView>> violations = validator.validate(customer);

		logger.error("size of violations : " + violations.size());

		for (ConstraintViolation<CustomerView> constraintViolation : violations) {
			logger.error("constraintViolation: field \"" + constraintViolation.getPropertyPath() + "\"," + constraintViolation.getMessage());
			throw new WebserviceException(ErrorEnum.REQUIRED_ELEMENT_MISSING, constraintViolation.getMessage());
		}
		// validation - End

		if(violations.size() == 0) {
			logger.info("Validation Success!");

			customerFindByUsername.setFirstName(customer.getFirstName());
			customerFindByUsername.setLastName(customer.getLastName());
			customerFindByUsername.setEmail(customer.getEmail());

			try {
				logger.info("Update Customer");
				savedCustomer = customerService.updateCustomer(customerFindByUsername);
				logger.info("Customer updated");
			}catch (Exception e) {
				logger.error("Error:- Unable to update customer");
				throw new WebserviceException(ErrorEnum.SAVING_UNSUCCESSFUL, ErrorEnum.SAVING_UNSUCCESSFUL.getDescription());
			}
		}
		
		return new ResponseEntity<Customers>(savedCustomer, HttpStatus.OK);
	}


	/**
	 * Deletes customer identified with <code>username</code>
	 * @param username
	 * @throws Exception 
	 */
	@RequestMapping(path = "/deleteCustomer/{username}", 
			method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a customer")
	public ResponseEntity<?> deleteCustomer(@PathVariable String username) throws Exception {

		Customers customerFindByUsername = customerService.findByUsername(username);
		try {
			logger.info("Delete Customer");
			customerService.deleteCustomer(customerFindByUsername);
			logger.info("Customer Deleted");
		}catch (Exception e) {
			logger.error("Error:- Unable to delete customer");
			throw new WebserviceException(ErrorEnum.DELETION_UNSUCCESSFUL, ErrorEnum.DELETION_UNSUCCESSFUL.getDescription());
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
