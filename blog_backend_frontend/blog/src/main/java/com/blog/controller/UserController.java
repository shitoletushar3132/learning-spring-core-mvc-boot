package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.model.UserModel;
import com.blog.service.IUserService;
import com.blog.util.ApiResponse;
import com.blog.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/profile")
	public ResponseEntity<ApiResponse<UserModel>> getProfile(HttpServletRequest request) {
		Integer userId = (Integer) request.getAttribute("userId"); // set by interceptor
		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiResponse<>(null, "Unauthorized", "Missing or invalid token"));
		}

		UserModel user = userService.getUserById(userId);
		user.setPassword(null);
		
		return ResponseEntity.ok(new ApiResponse<>(user, "Fetch successfully", null));
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserModel>> register(@RequestBody UserModel user) {
		userService.registerUser(user);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(user, "User registered successfully", null));
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Object>> login(@RequestBody UserModel user, HttpServletResponse res) {
		UserModel loginUser = userService.loginUser(user.getEmail(), user.getPassword());
		if (loginUser == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiResponse<>(null, "Login failed", "Invalid email or password"));
		}

		loginUser.setPassword(null);
		String token = JwtUtil.generateToken(loginUser.getId());

		Cookie cookie = new Cookie("token", token);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge(24 * 60 * 60);

		res.addCookie(cookie);

		return ResponseEntity.ok(new ApiResponse<>(new Object() {
			public final UserModel user = loginUser;
//			public final String jwtToken = token;
		}, "User login successful", null));
	}
}
