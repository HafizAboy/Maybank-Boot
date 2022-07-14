package com.maybank.boot.model;

import java.math.BigInteger;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Hafiz
 * @version 0.01
 */
@Entity
@Table(name = "t_credit_card")
public class CreditCards {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", nullable=false)
    @JsonIgnore
    private UUID id;

    @NotNull(message = "Credit Card No cannot be null")
	@Column(name="credit_card_no")
	private String creditCardNo;
	
    @NotNull(message = "Credit Card Type cannot be null")
	@Column(name="credit_card_type")
	private String creditCardType;
	
	@Column(name="expired_month")
	private int expiredMonth;

	@Column(name="expired_year")
    private int expiredYear;

	@Column(name="credit_balance")
    private BigInteger creditBalance;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customers customer;

	public CreditCards() {
		
	}

	public CreditCards(String creditCardNo,String creditCardType,int expiredMonth,int expiredYear,BigInteger creditBalance) {
		this.creditCardNo=creditCardNo;
		this.creditCardType=creditCardType;
		this.expiredMonth=expiredMonth;
		this.expiredYear=expiredYear;
		this.creditBalance=creditBalance;
	}
	
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

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}
	
}
