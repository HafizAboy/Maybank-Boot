package com.maybank.boot.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maybank.boot.model.CreditCards;

/**
 * @author Hafiz
 * @version 0.01
 */
public interface CreditCardRepository extends JpaRepository<CreditCards, UUID> {

	CreditCards findByCreditCardNo(String creditCardNo);
	CreditCards deleteByCreditCardNo(String creditCardNo) throws Exception;
}
