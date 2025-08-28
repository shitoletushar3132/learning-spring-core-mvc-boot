package com.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.IPostDAO;
import com.blog.dao.IUserDAO;
import com.blog.exception.BadRequestException;
import com.blog.model.BlogPost;
import com.blog.model.UserModel;

@Service
public class PostService implements IPostService {

	@Autowired
	private IPostDAO postDAO;

	@Autowired
	private IUserDAO userDAO;

	@Override
	public boolean createPost(BlogPost post, int userId) {

		if (post.getTitle() == null || post.getContent() == null) {
			throw new BadRequestException("Title and Content Required");
		}

		UserModel author = userDAO.getUserById(userId);

		// 2️⃣ Set author and timestamp
		post.setAuthor(author);
		post.setPublishedAt(LocalDateTime.now());

		// 3️⃣ Save post
		postDAO.savePost(post);

		return true;
	}

	@Override
	public List<BlogPost> getAllPosts() {
		return postDAO.getAllPost();
	}

	@Override
	public BlogPost getPostById(int id) {
		return postDAO.getBlogPostById(id);
	}

	@Override
	public BlogPost updatePost(BlogPost post) {
		return postDAO.updateBlogPost(post);
	}

	@Override
	public boolean deletePost(int id, int userId) {
		return postDAO.deleteBlogPost(id, userId);
	}

	@Override
	public List<BlogPost> getPostsByUserId(int userId) {
		return postDAO.getPostsByUserId(userId);
	}
}
