package com.jwyoon.www.service;

import com.jwyoon.www.model.UserAuth;
import com.jwyoon.www.model.UserList;
import com.jwyoon.www.repository.UserAuthRepository;
import com.jwyoon.www.repository.UserListRepository;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Resource(name = "userListRepository")
	private UserListRepository userListRepository;

	@Resource(name="userAuthRepository")
	private UserAuthRepository userAuthRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserList> user = userListRepository.findById(username);
		
		if(user != null) {
			List<UserAuth> auth = userAuthRepository.findByIdx(user.get().getUserId());
			List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
			
			for(UserAuth a:auth) {
				GrantedAuthority authority = new SimpleGrantedAuthority(a.getAuth());
				grantList.add(authority);				
			}
             
             UserDetails userDetails = new User(user.get().getUserId(),user.get().getUserPassword(),grantList);
             return userDetails;
		}else {
			return null;
		}
	}

}
