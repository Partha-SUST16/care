package com.care.care.userregistration.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.care.care.userregistration.model.ConfirmationToken;


public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}

