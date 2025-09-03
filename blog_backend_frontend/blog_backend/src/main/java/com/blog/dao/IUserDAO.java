package com.blog.dao;

import com.blog.model.UserModel;

public interface IUserDAO {
	boolean saveUser(UserModel userModel);

	UserModel getUserById(int id);

	UserModel loginUser(String email, String password);

	UserModel updateUser(UserModel userModel);

	boolean deleteUser(int id);
}
