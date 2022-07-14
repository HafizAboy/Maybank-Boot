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
import com.maybank.boot.model.CreditCards;
import com.maybank.boot.model.Customers;
import com.maybank.boot.service.CreditCardService;
import com.maybank.boot.service.CustomerService;
import com.maybank.boot.view.CreditCardView;

import io.swagger.annotations.ApiOperation;

/**
 * @author Aboy
 * @version 0.01
 */
@CrossOrigin
@RestController
@RequestMapping("/creditCards")
public class CreditCardController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CreditCardService creditCardService;

	@Autowired
	private CustomerService customerService;

	/**
	 * Fetch a list of creditCards
	 * @return a list of creditCards
	 * @throws Exception 
	 */
	@RequestMapping(path="/allCreditCards", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Fetch all creditCards")
	public ResponseEntity<?> getAllCreditCards() throws Exception {
		List<CreditCards> creditCards = (List<CreditCards>) creditCardService.findAllCreditCards();

		return new ResponseEntity<>(creditCards, HttpStatus.OK);
	}

	/**
	 * Finds a creditCard by <code>creditCardNo</code>
	 * 
	 * @param creditCardNo creditCard's creditCardNo
	 * 
	 * @return the {@link CreditCards} object
	 * @throws Exception 
	 */
	@RequestMapping(path = "/getCreditCardByCreditCardNo/{creditCardNo}", 
			method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a creditCard")
	public ResponseEntity<?> getCreditCardByCreditCardNo(@PathVariable String creditCardNo) throws Exception {
		CreditCards creditCard = new CreditCards();
		creditCard = creditCardService.findByCreditCardNo(creditCardNo);
		
		return new ResponseEntity<>(creditCard, HttpStatus.OK);
	}

	/**
	 * Add a creditCard
	 * 
	 * @param creditCard
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(path = "/addCreditCard",
			method = RequestMethod.POST,
			consumes =  MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create a new creditCard")
	public ResponseEntity<CreditCards> addCreditCard(@RequestBody CreditCardView creditCard) throws Exception {

		CreditCards savedCreditCard = new CreditCards();
		CreditCards creditCardObj = new CreditCards();

		// validate the input
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<CreditCardView>> violations = validator.validate(creditCard);

		logger.error("size of violations : " + violations.size());

		for (ConstraintViolation<CreditCardView> constraintViolation : violations) {
			logger.error("constraintViolation: field \"" + constraintViolation.getPropertyPath() + "\"," + constraintViolation.getMessage());
			throw new WebserviceException(ErrorEnum.REQUIRED_ELEMENT_MISSING, constraintViolation.getMessage());
		}
		// validation - End

		if(violations.size() == 0) {
			logger.info("Validation Success!");
			
			creditCardObj.setCreditCardNo(creditCard.getCreditCardNo());
			creditCardObj.setCreditCardType(creditCard.getCreditCardType());
			creditCardObj.setExpiredMonth(creditCard.getExpiredMonth());
			creditCardObj.setExpiredYear(creditCard.getExpiredYear());
			creditCardObj.setCreditBalance(creditCard.getCreditBalance());

			try {
				logger.info("Saving CreditCard");
				savedCreditCard =  creditCardService.saveCreditCard(creditCardObj);
				logger.info("CreditCard creation completed");
			}catch (Exception e) {
				logger.error("Error:- Unable to create creditCard");
				throw new WebserviceException(ErrorEnum.SAVING_UNSUCCESSFUL, ErrorEnum.SAVING_UNSUCCESSFUL.getDescription());
			}
		}
		return new ResponseEntity<CreditCards>(savedCreditCard, HttpStatus.CREATED);
	}

	/**
	 * Updates the creditCard
	 * 
	 * @param creditCard
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(path = "/updateCreditCard",
			method = RequestMethod.PUT)
	@ApiOperation(value = "Update a creditCard")
	public ResponseEntity<CreditCards> updateCreditCard(@RequestBody CreditCards creditCard) throws Exception {

		CreditCards savedCreditCard = new CreditCards();
		CreditCards creditCardFindByCreditCardNo = creditCardService.findByCreditCardNo(creditCard.getCreditCardNo());
		// validate the input
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<CreditCards>> violations = validator.validate(creditCard);

		logger.error("size of violations : " + violations.size());

		for (ConstraintViolation<CreditCards> constraintViolation : violations) {
			logger.error("constraintViolation: field \"" + constraintViolation.getPropertyPath() + "\"," + constraintViolation.getMessage());
			throw new WebserviceException(ErrorEnum.REQUIRED_ELEMENT_MISSING, constraintViolation.getMessage());
		}
		// validation - End

		if(violations.size() == 0) {
			logger.info("Validation Success!");

			creditCardFindByCreditCardNo.setCreditCardNo(creditCard.getCreditCardNo());
			creditCardFindByCreditCardNo.setCreditCardType(creditCard.getCreditCardType());
			creditCardFindByCreditCardNo.setExpiredMonth(creditCard.getExpiredMonth());
			creditCardFindByCreditCardNo.setExpiredYear(creditCard.getExpiredYear());

			try {
				logger.info("Update CreditCard");
				savedCreditCard = creditCardService.updateCreditCard(creditCardFindByCreditCardNo);
				logger.info("CreditCard updated");
			}catch (Exception e) {
				logger.error("Error:- Unable to update creditCard");
				throw new WebserviceException(ErrorEnum.SAVING_UNSUCCESSFUL, ErrorEnum.SAVING_UNSUCCESSFUL.getDescription());
			}
		}
		
		return new ResponseEntity<CreditCards>(savedCreditCard, HttpStatus.OK);
	}


	/**
	 * Deletes creditCard identified with <code>creditCardNo</code>
	 * @param creditCardNo
	 * @throws Exception 
	 */
	@RequestMapping(path = "/deleteCreditCard/{creditCardNo}", 
			method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a creditCard")
	public ResponseEntity<?> deleteCreditCard(@PathVariable String creditCardNo) throws Exception {

		CreditCards creditCardFindByCreditCardNo = creditCardService.findByCreditCardNo(creditCardNo);
		try {
			logger.info("Delete CreditCard");
			creditCardService.deleteCreditCard(creditCardFindByCreditCardNo);
			logger.info("CreditCard Deleted");
		}catch (Exception e) {
			logger.error("Error:- Unable to delete creditCard");
			throw new WebserviceException(ErrorEnum.DELETION_UNSUCCESSFUL, ErrorEnum.DELETION_UNSUCCESSFUL.getDescription());
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * apply credit card
	 * 
	 * @param creditCardNo, customerId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(path = "/applyCreditCard/{creditCardNo}/{customerId}",
			method = RequestMethod.PUT)
	@ApiOperation(value = "Apply a creditCard")
	public ResponseEntity<CreditCards> applyCreditCard(@PathVariable String creditCardNo, @PathVariable String customerId) throws Exception {

		CreditCards applyCreditCard = new CreditCards();
		CreditCards creditCardFindByCreditCardNo = creditCardService.findByCreditCardNo(creditCardNo);

		Customers applicant = customerService.findByCustomerId(customerId);
		logger.info("Credit card applicant: "+applicant.getFirstName()+" "+applicant.getLastName());
		
		creditCardFindByCreditCardNo.setCustomer(applicant);
		
		try {
			logger.info("Update CreditCard");
			applyCreditCard = creditCardService.updateCreditCard(creditCardFindByCreditCardNo);
			logger.info("CreditCard updated");
		}catch (Exception e) {
			logger.error("Error:- Unable to update creditCard");
			throw new WebserviceException(ErrorEnum.SAVING_UNSUCCESSFUL, ErrorEnum.SAVING_UNSUCCESSFUL.getDescription());
		}
		return new ResponseEntity<CreditCards>(applyCreditCard, HttpStatus.OK);
	}
}
