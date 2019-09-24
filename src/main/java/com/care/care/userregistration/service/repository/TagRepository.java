package com.care.care.userregistration.service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.care.care.userregistration.model.Tag;

@Repository("tagRepository")
public interface TagRepository extends CrudRepository<Tag, Long>{
	
	@Query("select t from Tag t where tagID = :id")
	Tag findByTagId(Long id);
}
