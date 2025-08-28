package com.blog.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserModel {
	private int id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String email;

	public UserModel() {
		super();
	}

	public UserModel(int id, String password, String email) {
		this.id = id;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", password=" + password + ", email=" + email + "]";
	}

}
