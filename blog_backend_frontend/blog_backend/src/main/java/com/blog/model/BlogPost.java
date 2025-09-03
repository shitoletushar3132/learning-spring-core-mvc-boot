package com.blog.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BlogPost {

	private int id;
	private String title;
	private String content;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime publishedAt;
	private UserModel author;
	private List<Image> images;

	public BlogPost() {
		super();
	}

	public BlogPost(int id, String title, String content, LocalDateTime publishedAt, UserModel userModel,
			List<Image> images) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.publishedAt = publishedAt;
		this.author = userModel;
		this.images = images;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(LocalDateTime publishedAt) {
		this.publishedAt = publishedAt;
	}

	public UserModel getAuthor() {
		return author;
	}

	public void setAuthor(UserModel author) {
		this.author = author;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "BlogPost [id=" + id + ", title=" + title + ", content=" + content + ", publishedAt=" + publishedAt
				+ ", author=" + author + "]";
	}

}
