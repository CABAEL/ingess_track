package com.ingress_track.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO UserController structure and responses

@RestController
public class UserController{
	
	
	@GetMapping("/authenticate")
	public Boolean AuthenticateUser() {
		return true;
	}
	
	@PostMapping("index")
	public String index() {
		return "user";
	}
	
	
	@PostMapping("/add-user")
	public String AddUser() {
		return "sample";
	}
	
	
	@GetMapping("/get-users")
	public String GetUsers(){
		return "Get Users!";
	}
	
}