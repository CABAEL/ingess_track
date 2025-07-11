package com.ingress_track.controller;

import com.ingress_track.dto.UserDto;
import com.ingress_track.service.UserService;
import com.ingress_track.util.TransactionLogs;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")

public class UserController{

	private UserService userService;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

		UserDto savedUser = userService.createUser(userDto);

		if(savedUser.getId() != null){
			TransactionLogs.log("User created successfully: [ID: "+savedUser.getId()+"]");
		}else{
			TransactionLogs.log("User save failed");
		}


		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	
}