package com.care.care.userregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.care.userregistration.model.Tag;
import com.care.care.userregistration.service.repository.TagRepository;

@Controller
public class TagController {
	
	@Autowired
	private TagRepository tagRepository;
	
	 @RequestMapping(value = "/Tagcreate", method = RequestMethod.POST)
	 @ResponseBody
	    public String create(@RequestBody Tag t) {
	        Tag tag = new Tag();
	       // tag.setName(t.get);
	        tagRepository.save(t);
	        return "Success";
	    }

	    @RequestMapping(value = "/Tagupdate", method = RequestMethod.POST)
	    public String update(@RequestParam(name = "tagID") Long id,
	                         @RequestParam(name = "name") String name) {
	        Tag tag = tagRepository.findByTagId(id);
	        tag.setName(name);
	        tagRepository.save(tag);
	        return "Updated";
	    }

	    @RequestMapping(value = "/Tagdelete", method = RequestMethod.POST)
	    public String delete(@RequestParam(name = "tagID") Long id) {
	        tagRepository.deleteById(id);
	        return "Success";
	    }
}
