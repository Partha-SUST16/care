package com.care.care.userregistration.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.care.care.userregistration.model.Post;
import com.care.care.userregistration.model.User;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findById(Long id);
	
	Page<Post> findByUserOrderByCreateDateDesc(User user, Pageable pageable);

	Page<Post> findAllByOrderByCreateDateDesc(Pageable pageable);
	
	@Query("select p from Post p where user_id = :userid")
	List<Post> findAllByUserID(Long userid);
}
