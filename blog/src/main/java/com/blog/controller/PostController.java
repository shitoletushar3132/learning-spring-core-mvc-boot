package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.blog.model.BlogPost;
import com.blog.model.UserModel;
import com.blog.service.IPostService;

import com.blog.util.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PostController {

	@Autowired
	private IPostService postService;

	@GetMapping("/")
	public ResponseEntity<ApiResponse<List<BlogPost>>> getPosts() {
		List<BlogPost> blogs = postService.getAllPosts();
		return ResponseEntity.ok(new ApiResponse<>(blogs, "fetch successfully", null));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<BlogPost>> getPost(@PathVariable("id") int id) {
		BlogPost post = postService.getPostById(id);
		if (post == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, "Post not found", null));
		}
		return ResponseEntity.ok(new ApiResponse<>(post, "Fetched successfully", null));
	}

	@GetMapping("/author/{id}")
	public ResponseEntity<ApiResponse<List<BlogPost>>> getPostByAuthor(@PathVariable("id") int id) {
		List<BlogPost> postsByAuthor = postService.getPostsByUserId(id);

		return ResponseEntity.ok(new ApiResponse<>(postsByAuthor, "Fetched successfully", null));

	}

	@PostMapping("/add-post")
	public ResponseEntity<ApiResponse<BlogPost>> addPost(@RequestBody BlogPost blogPost, HttpServletRequest request) {
		Integer userId = (Integer) request.getAttribute("userId"); // set by interceptor
		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiResponse<>(null, "Unauthorized", "Missing or invalid token"));
		}

		postService.createPost(blogPost, userId);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(null, "Post created successfully", null));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse<String>> deletePost(@PathVariable("id") int id, HttpServletRequest request) {
		Integer userId = (Integer) request.getAttribute("userId"); // set by interceptor
		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiResponse<>(null, "Unauthorized", "Missing or invalid token"));
		}
		postService.deletePost(id, userId);

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(null, "delete Successfully", null));
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<ApiResponse<BlogPost>> updatePost(@PathVariable("id") int id, @RequestBody BlogPost blogPost,
			HttpServletRequest request) {
		Integer userId = (Integer) request.getAttribute("userId"); // set by interceptor
		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiResponse<>(null, "Unauthorized", "Missing or invalid token"));
		}

		UserModel author = new UserModel(userId, null, null);

		blogPost.setId(id);
		blogPost.setAuthor(author);
		BlogPost updatedBlog = postService.updatePost(blogPost);

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(updatedBlog, "update Successfully", null));

	}

}
