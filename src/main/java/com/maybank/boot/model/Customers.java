package com.maybank.boot.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Hafiz
 * @version 0.01
 */
@Entity
@Table(name = "t_customer")
public class Customers {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", nullable=false)
    @JsonIgnore
    private UUID id;

    @NotNull(message = "Customer Id cannot be null")
	@Column(name="customer_id")
	private String customerId;
	
    @NotNull(message = "Username cannot be null")
	@Column(name="username")
	private String username;
	
    @NotNull(message = "Firstname cannot be null")
	@Column(name="firstname")
	private String firstName;
	
    @NotNull(message = "Lastname cannot be null")
	@Column(name="lastname")
	private String lastName;

    @NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
    @Column(name = "email", unique = true)
    private String email;
    
    public Customers() {
    	
    }

    public Customers(String customerId,String username,String firstName,String lastName,String email) {
    	this.customerId=customerId;
    	this.username=username;
    	this.firstName=firstName;
    	this.lastName=lastName;
    	this.email=email;
    }
    
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
