package com.example.demo.usertest;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserServices;

public class Usertest {
	
//	UserRepository userRepository = mock(UserRepository.class);
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private UserServices userServices;
	
	@Test
	public void GetUsersTest() {
		when(userRepository.findAll()).thenReturn(Stream.of(new User(1,"chandanS7","chandan231",678435634,"Bangalore",647383), 
				new User(2,"chandanS","chandan23",67838634,"Bangalore",645383)).collect(Collectors.toList()));
		System.out.println(((List<User>) userServices.getAllUser()).size());
		assertEquals(2,((List<User>) userServices.getAllUser()).size());
	
	}
	
}
