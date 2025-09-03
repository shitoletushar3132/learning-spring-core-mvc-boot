package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.IUserDAO;
import com.blog.exception.BadRequestException;
import com.blog.model.UserModel;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	@Override
	public boolean registerUser(UserModel user) {
		// Validate new user
		if (user == null) {
			throw new IllegalArgumentException("Email and Password Required");
		}
		if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
			throw new BadRequestException("Email is required");
		}
		if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
			throw new BadRequestException("Password is required");
		}
		return userDAO.saveUser(user);
	}

	@Override
	public UserModel loginUser(String email, String password) {
		// Validate login credentials
		if (email == null || email.trim().isEmpty()) {
			throw new BadRequestException("Email is required");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new BadRequestException("Password is required");
		}
		return userDAO.loginUser(email, password);
	}

	@Override
	public UserModel getUserById(int id) {
		if (id <= 0) {
			throw new BadRequestException("Invalid user id");
		}
		return userDAO.getUserById(id);
	}

	@Override
	public UserModel updateUser(UserModel user) {
		// Validate user before update
		if (user == null) {
			throw new BadRequestException("Email and Password Required");
		}
		if (user.getId() <= 0) {
			throw new BadRequestException("User id is required for update");
		}
		if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
			throw new BadRequestException("Email is required");
		}
		if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
			throw new BadRequestException("Password is required");
		}
		return userDAO.updateUser(user);
	}

	@Override
	public boolean deleteUser(int id) {
		if (id <= 0) {
			throw new BadRequestException("Invalid user id");
		}
		return userDAO.deleteUser(id);
	}
}
