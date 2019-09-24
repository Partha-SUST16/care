package com.care.care.userregistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.care.userregistration.model.Post;
import com.care.care.userregistration.model.PostHelper;
import com.care.care.userregistration.model.Tag;
import com.care.care.userregistration.model.TagList;
import com.care.care.userregistration.model.User;
import com.care.care.userregistration.service.repository.PostRepository;
import com.care.care.userregistration.service.repository.TagRepository;
import com.care.care.userregistration.service.repository.UserRepository;


@Controller
public class PostController {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
	public List<Post> getAllPostByUserID(@PathVariable Long id){
		return postRepository.findAllByUserID(id);
	}
	@GetMapping(value = "/blog/{blogId}")
	public Post postDetails(@PathVariable Long articleId) {
		return postRepository.getOne(articleId);
	}
	
	@PostMapping(value = "/blog/create",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createPost(@RequestBody PostHelper post,@RequestParam(value = "userID") int userID ) {
		Post postToSubmit = new Post();
		postToSubmit.setTitle(post.getTitle());
		postToSubmit.setBody(post.getBody());
		postToSubmit.setAgeLimit(post.getAgeLimit());
		postToSubmit.setcontentHtml(post.getContentHTML());
		User user = userRepository.findByUserid(userID);
		postToSubmit.setUser(user);
		postToSubmit.setTags(post.getTaging());
		List<Tag>existingList  = post.getTaging();
//		for(Tag temp : post.getTaging()) {
//			if(existingList.contains(temp))
//				continue;
//			else tagRepository.save(temp);
//		}
		postRepository.save(postToSubmit);
		
		return "success";
	}
	@RequestMapping(value = "/blog/delete/{blogID}",method = RequestMethod.GET)
	public String deletePost(@PathVariable Long blogID) {
		postRepository.delete(postRepository.getOne(blogID));
		return "success";
	}
}
