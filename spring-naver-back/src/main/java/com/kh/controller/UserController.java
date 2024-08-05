package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.User;
import com.kh.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping//api 주소값 users
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@PostMapping
	public void insertUser(@RequestBody User user) {//@RequestBody:전체가져오기(이름,이메일)
		userService.insertUser(user);
	}
	
	
	//await axios.delete(`/users?id=${id}`);
	@DeleteMapping("/{id}")//삭제를 진행하기 위해 만나는 주소(api) : users/유저번호
	public void deleteUser(@PathVariable int id) {//@RequestParam:하나만가져오기(id)
		userService.deleteUser(id);
	}
	
	
	/*
	//await axios.delete(`/users`, {params: {id} } );
	@DeleteMapping()//a삭제를 진행하기 위해 만나는 주소(api) : users
	public void deleteUser(@RequestParam(name = "id") int id) {
		userService.deleteUser(id);
	}
	*/
	
	@PutMapping()//수정
	public void updateUser(@RequestBody User user) {
		userService.updateUser(user);
	}
	

}