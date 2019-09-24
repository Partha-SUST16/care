package com.care.care.userregistration.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.care.care.userregistration.model.ConfirmationToken;
import com.care.care.userregistration.model.PostHelper;
import com.care.care.userregistration.model.User;
import com.care.care.userregistration.model.UserHelper;
import com.care.care.userregistration.service.EmailSenderService;
import com.care.care.userregistration.service.repository.ConfirmationTokenRepository;
import com.care.care.userregistration.service.repository.UserRepository;


@Controller
public class UserAccountController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;

//	@RequestMapping(value="/register", method=RequestMethod.GET)
//	public ModelAndView displayRegistration(ModelAndView modelAndView, User user)
//	{
//		modelAndView.addObject("user", user);
//		modelAndView.setViewName("register");
//		return modelAndView;
//	}
	
	@RequestMapping(value="/emni",method = RequestMethod.POST)
	@ResponseBody
	public String registerUser(@RequestBody User user)
	{
		
		User existingUser = userRepository.findByEmailIdIgnoreCase(user.getEmailId());
		if(existingUser != null)
		{
			return "Another user exists";
		}
		else 
		{
			userRepository.save(user);
			
			ConfirmationToken confirmationToken = new ConfirmationToken(user);
			
			confirmationTokenRepository.save(confirmationToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmailId());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("nonlovesme@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
			+"http://localhost:8081/confirm-account?token="+confirmationToken.getConfirmationToken());
			
			emailSenderService.sendEmail(mailMessage);
			return "Success";
			
		}
		//System.out.println(payload);
		
	}
	@PostMapping(value="/login")
	@ResponseBody
	public String login(@RequestBody UserHelper user) {
		User details= userRepository.findByNameandPassword(user.getName(), user.getPassword());
		return details.getEmailId()+" "+details.getName()+" "+details.getUserid()+" "+details.getDueDate();
		
	}
	
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public String confirmUserAccount( @RequestParam("token")String confirmationToken)
	{
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		
		if(token != null)
		{
			User user = userRepository.findByEmailIdIgnoreCase(token.getUser().getEmailId());
			user.setEnabled(true);
			userRepository.save(user);
			return "accountVerified";		
		}
		else
		{
			return "error";
		}
		
	}
	@RequestMapping(value="/registration",method = RequestMethod.POST)
	@ResponseBody
	public String emni(@RequestBody UserHelper user) {
		User existingUser = userRepository.findByEmailIdIgnoreCase(user.getEmail());
		if(existingUser != null)
		{
			return "Another user exists";
		}else {
			User temp = new User();
			temp.setDueDate(user.getDueDate());
			temp.setName(user.getName());
			temp.setPassword(user.getPassword());
			temp.setEmailId(user.getEmail());
			ConfirmationToken confirmationToken = new ConfirmationToken(temp);
			
			confirmationTokenRepository.save(confirmationToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(temp.getEmailId());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("nonlovesme@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
			+"http://localhost:8081/confirm-account?token="+confirmationToken.getConfirmationToken());
			
			emailSenderService.sendEmail(mailMessage);
			return "Success";
		}
	}

}
