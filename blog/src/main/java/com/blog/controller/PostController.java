package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.dao.IImageDAO;
import com.blog.model.BlogPost;
import com.blog.model.Image;
import com.blog.model.UserModel;
import com.blog.service.IPostService;

import com.blog.util.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PostController {

	@Autowired
	private IPostService postService;

	@Autowired
	private IImageDAO imageDAO;

	@GetMapping("/")
	public ResponseEntity<ApiResponse<List<BlogPost>>> getPosts() {
		List<BlogPost> posts = postService.getAllPosts();
		for (BlogPost post : posts) {
			List<Image> images = imageDAO.getImagesByPostId(post.getId());
			post.setImages(images);
		}
		return ResponseEntity.ok(new ApiResponse<>(posts, "fetch successfully", null));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<BlogPost>> getPost(@PathVariable("id") int id) {
		BlogPost post = postService.getPostById(id);
		if (post == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, "Post not found", null));
		}
		List<Image> images = imageDAO.getImagesByPostId(id);
		post.setImages(images);
		return ResponseEntity.ok(new ApiResponse<>(post, "Fetched successfully", null));
	}

	@GetMapping("/author/blogs/{id}")
	public ResponseEntity<ApiResponse<List<BlogPost>>> getPostByAuthor(@PathVariable("id") int id) {

		List<BlogPost> postsByAuthor = postService.getPostsByUserId(id);

		for (BlogPost post : postsByAuthor) {
			List<Image> images = imageDAO.getImagesByPostId(post.getId());
			post.setImages(images);
		}

		return ResponseEntity.ok(new ApiResponse<>(postsByAuthor, "Fetched successfully", null));

	}

	@PostMapping(value = "/add-post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponse<BlogPost>> addPost(@RequestPart("post") BlogPost blogPost,
			@RequestPart("images") MultipartFile[] images, HttpServletRequest request) {

		System.out.println(blogPost);
		System.out.println(images.length);
		Integer userId = (Integer) request.getAttribute("userId"); // set by interceptor
		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiResponse<>(null, "Unauthorized", "Missing or invalid token"));
		}

		postService.createPost(blogPost, userId, images);

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

	@PatchMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponse<BlogPost>> updatePost(@PathVariable("id") int id,
			@RequestPart("post") BlogPost blogPost,
			@RequestPart(value = "images", required = false) MultipartFile[] images, // optional
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
		List<Image> blogImages = imageDAO.getImagesByPostId(id);
		updatedBlog.setImages(blogImages);

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(updatedBlog, "update Successfully", null));

	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<BlogPost>>> searchBlogs(@RequestParam("q") String keyword) {
		List<BlogPost> posts = postService.getPostsByKeyWord(keyword);
		if (posts == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, "Post not found", null));
		}

		for (BlogPost post : posts) {
			List<Image> images = imageDAO.getImagesByPostId(post.getId());
			post.setImages(images);
		}
		return ResponseEntity.ok(new ApiResponse<>(posts, "Fetched successfully", null));
	}

}
