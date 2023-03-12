package com.basic.template.jwtexample.user.controller;


import com.basic.template.jwtexample.user.domain.User;
import com.basic.template.security.config.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor 
public class UserApiController {
	
	@GetMapping("user")
	public ResponseEntity<User> user(Authentication authentication) {
		PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
		return ResponseEntity.ok(principal.getUser());
	}
}











