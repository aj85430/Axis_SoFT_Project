package com.example.demo.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.example.demo.model.User;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@Override
	public User getUser(Integer userId){
		return userRepository.findById(userId).orElseThrow();
	}
	
	@Override
	public User addUser(User user){		
		return userRepository.save(user);
	}
	
	@Override
	public void deleteUser(Integer userId) {
		userRepository.deleteById(userId);		
	}
	
	@Override
	public User updateUser(User user)
	{
		return userRepository.save(user);
	}
	
	@Override
	public List<User> getUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	

}
