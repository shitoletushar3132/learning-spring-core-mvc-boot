package com.blog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.blog.exception.DataAccessException;
import com.blog.model.UserModel;

@Repository
public class UserDAO implements IUserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean saveUser(UserModel userModel) {
		try {
			String sql = "INSERT INTO users(email, password) VALUES(?,?)";
			jdbcTemplate.update(sql, userModel.getEmail(), userModel.getPassword());
			return true;
		} catch (DuplicateKeyException e) {
			throw new DataAccessException("Email already exists", e);
		} catch (Exception e) {
			throw new DataAccessException("Error Saving user", e);
		}
	}

	@Override
	public UserModel loginUser(String email, String password) {
		try {
			String sql = "SELECT * FROM users WHERE email=? AND password=?";

			UserModel user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserModel.class), email,
					password);

			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DataAccessException("Error getting User for login", e);

		}

	}

	@Override
	public UserModel getUserById(int id) {
		try {
			String sql = "SELECT * FROM users WHERE id=?";

			UserModel user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserModel.class), id);

			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DataAccessException("Error getting User", e);

		}
	}

	@Override
	public UserModel updateUser(UserModel userModel) {
		try {
			String sql = "UPDATE users SET email=?, password=? WHERE id=?";
			int rows = jdbcTemplate.update(sql, userModel.getEmail(), userModel.getPassword(), userModel.getId());

			if (rows > 0) {
				// Optionally, return the updated user by fetching from DB
				String fetchSql = "SELECT * FROM users WHERE id=?";
				return jdbcTemplate.queryForObject(fetchSql, new BeanPropertyRowMapper<>(UserModel.class),
						userModel.getId());
			} else {
				return null; // no user updated
			}

		} catch (DuplicateKeyException e) {
			throw new DataAccessException("Email already exists", e);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DataAccessException("Error updating user", e);
		}
	}

	@Override
	public boolean deleteUser(int id) {
		try {
			String sql = "DELETE FROM users WHERE id=?";
			int rows = jdbcTemplate.update(sql, id);
			return rows > 0;
		} catch (Exception e) {
			throw new DataAccessException("Error deleting user", e);
		}
	}

}
