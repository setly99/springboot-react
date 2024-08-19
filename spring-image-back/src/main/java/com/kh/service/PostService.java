package com.kh.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.Post;

public interface PostService {
	
	List<Post> findAll();
	void insertPost(Post post);
	
	//이미지업로드를도와주는 기능목록 설정
	void uploadImages(MultipartFile[] files, String title, String content);
	
	void updatePost(Post post);

}
