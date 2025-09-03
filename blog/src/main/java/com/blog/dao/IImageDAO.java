package com.blog.dao;

import java.util.List;

import com.blog.model.Image;

public interface IImageDAO {
	boolean saveImage(Image image);

	List<Image> getImagesByPostId(int postId);

	boolean deleteImage(int imageId);
}
