package com.jac.assignment2.service;

import com.jac.assignment2.*;
import com.jac.assignment2.model.User;
import com.jac.assignment2.repository.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}


}
