package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface IUserService {
	
	public List<User> getAllUsers();
	
	public User getUser(Integer userId);
	
	public User addUser(User user);
	
	public void deleteUser(Integer userId);
	
	public User updateUser(User user);
	
	public List<User> getUserByUserName(String userName);

}
