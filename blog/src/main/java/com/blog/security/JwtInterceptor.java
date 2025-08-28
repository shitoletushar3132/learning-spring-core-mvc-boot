package com.blog.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.blog.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String token = null;

		// 1️⃣ Get token from Authorization header (optional, fallback)
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
		}

		// 2️⃣ Get token from cookies if header not present
		if (token == null && request.getCookies() != null) {
			for (jakarta.servlet.http.Cookie cookie : request.getCookies()) {
				if ("token".equals(cookie.getName())) {
					token = cookie.getValue();
					break;
				}
			}
		}

		// 3️⃣ Validate token
		if (token != null) {
			System.out.println(token);
			try {
				int userId = JwtUtil.getUserId(token); // your JWT util method
				request.setAttribute("userId", userId);
				return true; // allow request
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Invalid or expired token");
				return false;
			}
		}

		// 4️⃣ No token found
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write("Missing token");
		return false;
	}
}
