package com.douzone.mysite.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	System.out.println("CustomUserDetailService");
         return userRepository.findByEmail(username);
//        return (userRepository.findByEmail(username)).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));       

    }
	
}
