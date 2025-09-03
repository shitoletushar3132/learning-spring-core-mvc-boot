package com.blog.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class Test {

	@PostMapping("/upload")
	@ResponseBody
	public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//		if (file.isEmpty()) {
//			return "⚠️ No file selected!";
//		}
//
//		// Save file to a directory (e.g. "uploads" folder in project root)
//		Path uploadPath = Paths.get("uploads/");
//		if (!Files.exists(uploadPath)) {
//			Files.createDirectories(uploadPath);
//		}
//
//		Path filePath = uploadPath.resolve(file.getOriginalFilename());
//		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//		return "✅ Uploaded: " + file.getOriginalFilename();
		System.out.println("File name = " + file.getOriginalFilename());
		return "OK";
	}
}
