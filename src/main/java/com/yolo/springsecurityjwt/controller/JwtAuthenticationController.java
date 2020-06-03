/*
This is rest controller class. here we have mapped three resources "/authenticate", "/register" and "/hello"
to respective methods.
*/

package com.yolo.springsecurityjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.yolo.springsecurityjwt.config.JwtTokenUtil;
import com.yolo.springsecurityjwt.dao.UserJpaRepository;
import com.yolo.springsecurityjwt.model.JwtRequest;
import com.yolo.springsecurityjwt.model.JwtResponse;
import com.yolo.springsecurityjwt.model.User;
import com.yolo.springsecurityjwt.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private UserJpaRepository userJpaRepository;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwtToken = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(jwtToken));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@GetMapping("/hello")
	public String sayHello(@RequestHeader String authorization)
	{
		String username = jwtTokenUtil.getUsernameFromToken(authorization.substring(7));
		return "Hey " + username;
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestBody User user)
	{
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		User savedUser = userJpaRepository.save(user);
		if(savedUser != null)
			return "User with username " + user.getUsername() + " is registered successfully.";
		else
			return "Unable to register user. Please contact administrator.";
	}
}


