package com.blog.service;

import java.util.List;
import com.blog.model.BlogPost;

public interface IPostService {
	boolean createPost(BlogPost post, int userId);

	List<BlogPost> getAllPosts();

	BlogPost getPostById(int id);

	BlogPost updatePost(BlogPost post);

	boolean deletePost(int id, int userId);

	List<BlogPost> getPostsByUserId(int userId);
}
