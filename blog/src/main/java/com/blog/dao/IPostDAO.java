package com.blog.dao;

import java.util.List;

import com.blog.model.BlogPost;

public interface IPostDAO {

	int savePostAndReturnId(BlogPost blogPost);

	List<BlogPost> getAllPost();

	BlogPost getBlogPostById(int postId);

	BlogPost updateBlogPost(BlogPost blogPost);

	boolean deleteBlogPost(int postId, int userId);

	List<BlogPost> getPostsByUserId(int userId);
	
	List<BlogPost> searchBlogs(String keyword);
}
