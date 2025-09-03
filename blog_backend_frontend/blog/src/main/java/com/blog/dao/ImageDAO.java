package com.blog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.blog.model.Image;

@Repository
public class ImageDAO implements IImageDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean saveImage(Image image) {
		String sql = "INSERT INTO images(post_id, file_name, file_path) VALUES (?, ?, ?)";
		int rows = jdbcTemplate.update(sql, image.getPostId(), image.getFileName(), image.getFilePath());
		return rows > 0;
	}

	@Override
	public List<Image> getImagesByPostId(int postId) {
		String sql = "SELECT * FROM images WHERE post_id = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Image.class), postId);
	}

	@Override
	public boolean deleteImage(int imageId) {
		String sql = "DELETE FROM images WHERE id = ?";
		return jdbcTemplate.update(sql, imageId) > 0;
	}
}
