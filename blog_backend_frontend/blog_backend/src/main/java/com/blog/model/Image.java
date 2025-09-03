package com.blog.model;

public class Image {
	private int id;
	private int postId;
	private String fileName;
	private String filePath;

	public Image() {
		super();
	}

	public Image(int id, int postId, String fileName, String filePath) {
		this.id = id;
		this.postId = postId;
		this.fileName = fileName;
		this.filePath = filePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
