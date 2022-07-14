package com.maybank.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maybank.boot.model.CreditCards;
import com.maybank.boot.repository.CreditCardRepository;

/**
 * @author Hafiz
 * @version 0.01
 */
@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
	private CreditCardRepository creditCardRepo;
	
	@Override
	public CreditCards saveCreditCard(CreditCards creditCard) throws Exception {
		return creditCardRepo.save(creditCard);
	}

	@Override
	public List<CreditCards> findAllCreditCards() {
		return creditCardRepo.findAll();
	}

	@Override
	public CreditCards findByCreditCardNo(String creditCardNo) {
		return creditCardRepo.findByCreditCardNo(creditCardNo);
	}

	@Override
	public CreditCards updateCreditCard(CreditCards creditCard) throws Exception {
		return saveCreditCard(creditCard);
	}

	@Override
	public CreditCards deleteByCreditCardNo(String creditCardNo) throws Exception {
		return creditCardRepo.deleteByCreditCardNo(creditCardNo);
	}

	@Override
	public void deleteCreditCard(CreditCards creditCard) throws Exception {
		creditCardRepo.delete(creditCard);
	}
}
