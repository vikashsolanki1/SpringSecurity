package com.example.demo;

import org.springframework.security.access.prepost.PreAuthorize;    
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewController {
	@PreAuthorize("hasRole('USER1')")
	@GetMapping("/hello")
public String sayhello() {
	return "hello";
}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/hello2")
	public String sayhello2() {
		return "hello2";
	}
	
}
 