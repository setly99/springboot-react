package com.kh.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.Post;
import com.kh.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostMapper postMapper;

	// import org.springframework.beans.factory.annotation.Value;
	@Value("${file.upload-dir}") // application.properties에서 설정이름가져옴
	private String uploadDir;// uploadDir = "C:/Users/user1/Desktop/saveImage"

	@Override
	public List<Post> findAll() {
		return postMapper.findAll();
	}

	@Override
	public void insertPost(Post post) {
		postMapper.insertPost(post);
	}

	@Override
	public void uploadImages(MultipartFile[] files, String title, String content) {
		// 1. 바탕화면에 이미지 저장하고 불러올 폴더가 존재하는지 확인하고 없으면 폴더생성
		File uploadDirFile = new File(uploadDir);
		// 만약에 폴더가 존재할 경우
		// uploadDirFile.exists() = 폴더가 존재한다.
		// !uploadDirFile.exists() = 폴더가 존재하지 않는다.
		if (!uploadDirFile.exists()) {
			System.out.println("폴더가 존재하지 않아 폴더를 생성합니다.");
			if(!uploadDirFile.mkdirs()){//mkdir폴더1개, mkdirs하위폴더모두생성
				throw new RuntimeException("폴더 생성 실패했습니다.");
			}
		}
		//UUID 이미지 이름 저장 중복없이 저장할 수 있도록 설정
		List<String> fileNames = null;
		//파일명이 1개일 수 있고 여러 이름이 들어올 수 있기 때문에
		//fileNames = 파일이름들 리스트로 글자목록이 작성
		
		try {
			//MultipartFile[] : array로 파일들 담기
			//List.of(files) : 파일들 배열을 list로 반환
			//.stream() : 리스트나 배열과 같은 데이터를 하나씩 처리하는 기능
			//.collect(Collectors.toList()); : stream으로 가져온 이미지 데이터를 리스트로 정렬
			
			//이미지들 이미지를 한개씩 담아오기 map -> 이미지를 하나씩 가져올 때 stream이용해서가져오기
			//스트림 이용해서 가져온 이미지를 collect이용해서 리스트로 모음
			//한번더 리스트로 목록 변환
			fileNames = List.of(files).stream().map(file -> {
				//파일을 폴더에저장 폴더에 파일을 저장할 때 이미지에 랜덤으로 이름을 부여해서 저장
				//UUID 이용해서 저장
				String fileName1 = UUID.randomUUID().toString();
				//랜덤으로 작성한 이름 뒤에 원본 이름을 붙이고 싶을 때
				String fileName2 = UUID.randomUUID().toString() + file.getOriginalFilename();
				//랜덤이름_원본이름 : _로 구분짓고싶을 때
				String fileName3 = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				
				//폴더 안에 이미지들 저장하기 완성
				//File.separator : window(/, \), mac(/) 모두 경로를 알아서 잡아줌 
				File df = new File(uploadDir + File.separator + fileName3);
				
				try {
					file.transferTo(df);
				} catch (IOException e) {
					throw new RuntimeException("파일 업로드 실패",e);
				}
				
				return fileName3;
				
			}).collect(Collectors.toList());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//이미지 이름 설정과 이미지 경로 생성한 것을 바탕으로 DB 넣어주기
		Post post = new Post();
		post.setTitle(title);
		post.setContent(content);;
		post.setImageUrl(String.join(",", fileNames));
		insertPost(post);

	}
	
	@Override
	public void updatePost(Post post) {
		//insertPost 그대로 쓰지만 기존이미지삭제필요
		//바탕화면에 이미지가 저장된 폴더에서 기존이미지 삭제
	}

}
