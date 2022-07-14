package com.maybank.boot.view;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Hafiz
 * @version 0.01
 */
public class CustomerView {

    @JsonIgnore
    private UUID id;

    @NotNull(message = "Customer Id cannot be null")
	private String customerId;
	
    @NotNull(message = "Username cannot be null")
	private String username;
	
    @NotNull(message = "Firstname cannot be null")
	private String firstName;
	
    @NotNull(message = "Lastname cannot be null")
	private String lastName;

    @NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
    private String email;
    
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
