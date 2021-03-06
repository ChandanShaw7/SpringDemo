package com.example.demo.services;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import sun.management.jmxremote.ConnectorBootstrap;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;
	
	
//	public List<User> getAllUser(){
//		List <User> allUsers = new ArrayList<>(); 
//		try {
//			userRepository.findAll()
//			.forEach(allUsers::add);
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		return allUsers;
//	}
	
	public Iterable<User> getAllUser(){
		Iterable<User> allUser = null;
		try {
			allUser = userRepository.findAll();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return allUser;
	}
	
	public Iterable<User> getUserDetails(String city, int zip){
		Iterable<User> users = null;
		try {
			users = userRepository.findByCityAndZipOrderByUserNameAsc(city, zip);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	
	public String insertUser(User userDetails) {
		try 
		{
			if(userRepository.findByUserName(userDetails.getUserName()) == null)
			{
				userRepository.save(userDetails);
				return "User Registered";
			}
			return "User Already present";
		}
		catch(NullPointerException e)
		{
			return "Username shouldnot be null";
		}
	}
	
	
	public String loginUser(User loginDetails) {
		try {
			User userDetails = userRepository.findByUserName(loginDetails.getUserName());
			if (userDetails != null && userDetails.getPassword().equals(loginDetails.getPassword())) {
				return "user loggedIn";
			}
			
			return "Username or password is incorrect";
		}
		catch(NullPointerException e){
			return "Username or password is missing";
		}
	}
	
	@Transactional
	public void deleteUser(String userName) {
	   if (userRepository.findByUserName(userName) == null)
            throw new ResourceNotFoundException("userName",userName);
       userRepository.removeByUserName(userName);
	}
	
}
