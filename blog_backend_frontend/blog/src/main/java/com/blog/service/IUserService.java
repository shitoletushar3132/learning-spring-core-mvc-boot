package com.blog.service;

import com.blog.model.UserModel;

public interface IUserService {
	boolean registerUser(UserModel user);

	UserModel loginUser(String email, String password);

	UserModel getUserById(int id);

	UserModel updateUser(UserModel user);

	boolean deleteUser(int id);
}
