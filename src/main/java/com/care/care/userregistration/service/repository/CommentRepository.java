package com.care.care.userregistration.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.care.care.userregistration.model.Comment;

@Repository("commentRepository")
public interface CommentRepository extends CrudRepository<Comment, Long> {

}
