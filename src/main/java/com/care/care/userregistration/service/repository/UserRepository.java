package com.care.care.userregistration.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.care.care.userregistration.model.User;


@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, String> {
	User findByEmailIdIgnoreCase(String emailId);
}
