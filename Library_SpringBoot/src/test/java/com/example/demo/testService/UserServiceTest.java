package com.example.demo.testService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;

@SpringBootTest
class UserServiceTest {

	@Autowired
	UserServiceImpl userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	void getAllUsersTest() {
		
		List<User> userMockList = new ArrayList<>();		
		userMockList.add(new User(50001,"Aman Jaiswal","abc@gmail.com"));
		userMockList.add(new User(50002,"Gourav Sharma","xyz@gmail.com"));		
		when(userRepository.findAll()).thenReturn(userMockList);		
		assertEquals(2, userService.getAllUsers().size());
		
	}
	
	@Test
	void addUserTest() {
		
		User user = new User(50001,"Aman Jaiswal","abc@gmail.com");
		when(userRepository.save(user)).thenReturn(user);		
		assertEquals(user, userService.addUser(user));
		
	}
	
	@Test
	void getUserByUserNameTest() {
		
		List<User> userMockList = new ArrayList<>();		
		userMockList.add(new User(50001,"Aman Jaiswal","abc@gmail.com"));
		userMockList.add(new User(50002,"Gourav Sharma","xyz@gmail.com"));	
		String name= "Aman Jaiswal";		
		when(userRepository.findByUserName(name)).thenReturn(userMockList);		
		assertEquals(name, userService.getUserByUserName(name).get(0).getUserName());
		
	}
	
	
	
	@Test
	void getUserTest() {
		
		User user = new User(50001,"Aman Jaiswal","abc@gmail.com");
		int id= 50001;
		when(userRepository.findById(id)).thenReturn(Optional.of(user));
		assertEquals(user, userService.getUser(id));
		
	}
	
	@Test
	void updateUserTest() {
		User user = new User(50001,"Aman Jaiswal","abc@gmail.com");
		userService.updateUser(user);
		verify(userRepository, times(1)).save(user);
		
	}
	
	@Test
	void deleteUserTest() {
		User user = new User(50001,"Aman Jaiswal","abc@gmail.com");
		userService.deleteUser(user.getUserId());
		verify(userRepository, times(1)).deleteById(user.getUserId());
	}

}
