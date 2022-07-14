package com.maybank.boot.view;

import java.math.BigInteger;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Hafiz
 * @version 0.01
 */
public class CreditCardView {

    @JsonIgnore
    private UUID id;

    @NotNull(message = "Credit Card No cannot be null")
	private String creditCardNo;
	
    @NotNull(message = "Credit Card Type cannot be null")
	private String creditCardType;
	
	private int expiredMonth;

    private int expiredYear;

    private BigInteger creditBalance;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public int getExpiredMonth() {
		return expiredMonth;
	}

	public void setExpiredMonth(int expiredMonth) {
		this.expiredMonth = expiredMonth;
	}

	public int getExpiredYear() {
		return expiredYear;
	}

	public void setExpiredYear(int expiredYear) {
		this.expiredYear = expiredYear;
	}

	public BigInteger getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(BigInteger creditBalance) {
		this.creditBalance = creditBalance;
	}
}
