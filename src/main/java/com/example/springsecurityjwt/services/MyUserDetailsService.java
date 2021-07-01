package com.example.springsecurityjwt.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecurityjwt.models.MyUserDetails;
import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User(username, username, new ArrayList<>());
	}*/
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String name)throws UsernameNotFoundException {
		Optional<User> user  = userRepo.findByUserName(name);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found" + name));
		return user.map(MyUserDetails::new).get();
	}
	
}
