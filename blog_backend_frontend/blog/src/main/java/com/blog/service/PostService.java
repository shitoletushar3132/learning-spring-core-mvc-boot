package com.blog.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.dao.IImageDAO;
import com.blog.dao.IPostDAO;
import com.blog.dao.IUserDAO;
import com.blog.exception.BadRequestException;
import com.blog.model.BlogPost;
import com.blog.model.Image;
import com.blog.model.UserModel;

@Service
public class PostService implements IPostService {

	@Autowired
	private IPostDAO postDAO;

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IImageDAO imageDAO;

//	private static final String UPLOAD_DIR = "D:/Spring/blog/uploads/"; // Windows example
	private static final String UPLOAD_DIR = "/app/uploads/"; // Windows example


	@Override
	public boolean createPost(BlogPost post, int userId, MultipartFile[] files) {
		
		System.out.println(post);

		if (post.getTitle() == null || post.getContent() == null) {
			throw new BadRequestException("Title and Content Required");
		}

		UserModel author = userDAO.getUserById(userId);

		// 2️⃣ Set author and timestamp
		post.setAuthor(author);
		post.setPublishedAt(LocalDateTime.now());

		// 3️⃣ Save post
		int postId = postDAO.savePostAndReturnId(post);

		if (files != null && files.length != 0) {
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					try {
						File uploadDir = new File(UPLOAD_DIR);
						System.out.println(uploadDir);
						if (!uploadDir.exists())
							uploadDir.mkdirs();

						String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
						String filePath = UPLOAD_DIR + fileName;
						file.transferTo(new File(filePath));

						Image image = new Image();
						image.setPostId(postId);
						image.setFileName(fileName);
						image.setFilePath(filePath);

						imageDAO.saveImage(image);
					} catch (IOException e) {
						throw new BadRequestException("Error uploading file: " + file.getOriginalFilename());

					}
				}
			}
		}

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

	@Override
	public List<BlogPost> getPostsByKeyWord(String keyWord) {

		return postDAO.searchBlogs(keyWord);
	}

}
