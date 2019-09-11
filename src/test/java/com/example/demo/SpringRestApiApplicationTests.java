package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserServices;
import com.example.demo.usertest.Usertest;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRestApiApplicationTests {
	
//	private static final int Optional = 0;

	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private UserServices userServices;
	
	Stream<User> users = Stream.of(new User(1,"chandanS7","chandan231",678435634,"Bangalore",647383), 
								new User(2,"chandanS","chandan23",67838634,"Bangalore",645383),
								new User(1,"TestUser","test123",892635634,"Bangalore",647383));
	
	@Test
	public void GetUsersTest() {
		when(userRepository.findAll()).thenReturn(users.collect(Collectors.toList()));
		System.out.println(((List<User>) userServices.getAllUser()).size());
		assertEquals(3,((List<User>) userServices.getAllUser()).size());
	}
	
	@Test
	public void getUserDetailsTest() {
		when(userRepository.findByCityAndZipOrderByUserNameAsc("Bangalore",647383)).thenReturn((Iterable<User>) users.collect(Collectors.toList()));
		assertEquals(2,((List<User>)userServices.getUserDetails("Bangalore",647383)));
	}
	
	@Test
	public void userAddTest() {
		User user = new User(5,"TestInsert","testuser4",745273945,"Mysore",760098);
		when(userRepository.findAll()).thenReturn(users.collect(Collectors.toList()));
		userServices.insertUser(user);
		verify(userRepository,times(1)).save(user);
	}
	
	@Test
	public void userLoginTest() {
		User user = new User("chandanS7","chandan231");
		when(userRepository.findAll()).thenReturn(users.collect(Collectors.toList()));
		assertEquals("user loggedIn",userServices.loginUser(user));
	}
	
	@Test
	public void userDeleteTest() {
		User user = new User(5,"TestInsert","testuser4",745273945,"Mysore",760098);
		userServices.deleteUser(user.getUserName());
		verify(userRepository,times(1)).removeByUserName(user.getUserName());
	}
	
	
}
