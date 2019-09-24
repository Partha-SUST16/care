package com.care.care.userregistration.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.care.userregistration.model.Comment;
import com.care.care.userregistration.model.Post;
import com.care.care.userregistration.service.repository.CommentRepository;
import com.care.care.userregistration.service.repository.PostRepository;
import com.care.care.userregistration.service.repository.TagRepository;
import com.care.care.userregistration.service.repository.UserRepository;

@Controller
public class CommentController {
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentRepository commentRepository;
	
	@RequestMapping(value="/createComment")
	public String createComment(@RequestParam(value = "body") String content,@RequestParam(value="postID")Long postID,@RequestParam(value="userID") int userID) {
		Comment comment = new Comment();
		comment.setBody(content);
		comment.setPost(postRepository.getOne(postID));
		comment.setUser(userRepository.findByUserid(userID));
		
		return "";
	}
	@RequestMapping(value = "/commentPost/{id}", method = RequestMethod.GET)
	 public String commentPostWithId(@PathVariable Long id) {
		Optional<Post> post = postRepository.findById(id);
		if(post.isPresent()) {
			Comment comment = new Comment();
			comment.setPost(post.get());
		}
		return "";
	}
}
