/*
This class is implementing UserDetailsService interface and it is responsible for provide user details
for given username.
*/

package com.yolo.springsecurityjwt.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yolo.springsecurityjwt.dao.UserJpaRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService{

	@Autowired
	private UserJpaRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 // this return password for the given username.(here you can query DB)
		 Optional<com.yolo.springsecurityjwt.model.User> tempUser = userRepository.findById(username);
		
		if(tempUser.isPresent())
		    return new User(tempUser.get().getUsername(), tempUser.get().getPassword(), new ArrayList<>());
		else
			throw new UsernameNotFoundException("User not found with username: " + username);
		
	}
	
}
