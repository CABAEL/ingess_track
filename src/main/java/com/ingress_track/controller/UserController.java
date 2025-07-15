package com.ingress_track.controller;

import com.ingress_track.dto.UserDto;
import com.ingress_track.service.UserService;
import com.ingress_track.util.ApiUtil;
import com.ingress_track.util.ResponseMessages;
import jakarta.servlet.http.HttpServletRequest;
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

	@PostMapping("/create-user")
	public  ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto,HttpServletRequest request){

		UserDto savedUser = userService.createUser(userDto);
		HttpStatus status = HttpStatus.CREATED;

		return ResponseEntity
		.status(status)
		.body(ApiUtil.ResponseHandler(request,status,ResponseMessages.SUCCESS_MSG, savedUser));

	}

	
}