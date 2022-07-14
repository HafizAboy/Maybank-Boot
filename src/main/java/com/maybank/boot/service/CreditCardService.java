package com.maybank.boot.service;

import java.util.List;

import com.maybank.boot.model.CreditCards;

/**
 * @author Hafiz
 * @version 0.01
 */
public interface CreditCardService {

    List<CreditCards> findAllCreditCards();
    CreditCards findByCreditCardNo(String creditCardNo);
	CreditCards saveCreditCard(CreditCards creditCard) throws Exception;
	CreditCards updateCreditCard(CreditCards creditCard) throws Exception;
	CreditCards deleteByCreditCardNo(String creditCardNo) throws Exception;
	void deleteCreditCard(CreditCards creditCard) throws Exception;
}
