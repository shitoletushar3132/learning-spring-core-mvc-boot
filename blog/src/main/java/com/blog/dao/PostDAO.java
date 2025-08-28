package com.blog.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.blog.model.BlogPost;
import com.blog.model.UserModel;

import com.blog.exception.DataAccessException;

@Repository
public class PostDAO implements IPostDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// RowMapper for BlogPost
	private RowMapper<BlogPost> blogPostRowMapper = (ResultSet rs, int rowNum) -> {
		BlogPost post = new BlogPost();
		post.setId(rs.getInt("id"));
		post.setTitle(rs.getString("title"));
		post.setContent(rs.getString("content"));
		post.setPublishedAt(rs.getTimestamp("publish_date").toLocalDateTime());

		// Set author
		UserModel author = new UserModel();
		author.setId(rs.getInt("userId")); // assuming author info is minimal here
		author.setEmail(rs.getString("email"));
		post.setAuthor(author);

		return post;
	};

	@Override
	public boolean savePost(BlogPost blogPost) {
		try {
			String sql = "INSERT INTO posts(userId, title, content, publish_date) VALUES(?,?,?,?)";
			int rows = jdbcTemplate.update(sql, blogPost.getAuthor().getId(), blogPost.getTitle(),
					blogPost.getContent(), blogPost.getPublishedAt());
			return rows > 0;
		} catch (DuplicateKeyException e) {
			throw new DataAccessException("Post already exists", e);
		} catch (Exception e) {
			throw new DataAccessException("Error saving post", e);
		}
	}

	@Override
	public List<BlogPost> getAllPost() {
		try {
			String sql = "SELECT p.id, p.title, p.content, p.publish_date, u.id as userId, u.email " + "FROM posts p "
					+ "JOIN users u ON p.userId = u.id";
			return jdbcTemplate.query(sql, blogPostRowMapper);
		} catch (Exception e) {
			throw new DataAccessException("Error fetching posts", e);
		}
	}

	@Override
	public BlogPost getBlogPostById(int postId) {
		try {
			String sql = "SELECT * FROM posts p JOIN users u ON p.userId = u.id WHERE p.id=? ";
			return jdbcTemplate.queryForObject(sql, blogPostRowMapper, postId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			System.out.println(e);
			throw new DataAccessException("Error fetching post by ID", e);
		}
	}

	@Override
	public BlogPost updateBlogPost(BlogPost blogPost) {
		try {
			// Build dynamic SQL
			StringBuilder sql = new StringBuilder("UPDATE posts SET ");
			List<Object> params = new ArrayList<>();

			if (blogPost.getTitle() != null) {
				sql.append("title=?, ");
				params.add(blogPost.getTitle());
			}
			if (blogPost.getContent() != null) {
				sql.append("content=?, ");
				params.add(blogPost.getContent());
			}

			// remove last comma & space
			if (params.isEmpty()) {
				throw new DataAccessException("No fields provided to update", null);
			}
			sql.setLength(sql.length() - 2);

			sql.append(" WHERE id=? AND userId=?");
			params.add(blogPost.getId());
			params.add(blogPost.getAuthor().getId());

			int rows = jdbcTemplate.update(sql.toString(), params.toArray());

			if (rows == 0) {
				throw new DataAccessException("Post not found with id: " + blogPost.getId(), null);
			}

			String fetchSql = "SELECT * FROM posts p JOIN users u ON u.id = p.userId WHERE p.id=?";
			return jdbcTemplate.queryForObject(fetchSql, blogPostRowMapper, blogPost.getId());

		} catch (Exception e) {
			throw new DataAccessException("Error patching post", e);
		}
	}

	@Override
	public boolean deleteBlogPost(int postId, int userId) {
		try {
			String sql = "DELETE FROM posts WHERE id=? AND userId=?";
			int rows = jdbcTemplate.update(sql, postId, userId);
			if (rows == 0) {
				throw new DataAccessException("Post not found with id: " + postId, null);
			}
			return true;
		} catch (Exception e) {
			throw new DataAccessException("Error deleting post", e);
		}
	}

	@Override
	public List<BlogPost> getPostsByUserId(int userId) {
		try {
			String sql = "SELECT * FROM posts p JOIN users u ON u.id = p.userId WHERE p.userId=?";
			return jdbcTemplate.query(sql, blogPostRowMapper, userId);
		} catch (Exception e) {
			throw new DataAccessException("Error fetching posts by user", e);
		}
	}
}
