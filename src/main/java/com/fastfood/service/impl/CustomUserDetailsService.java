package com.fastfood.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.MyUser;
import com.fastfood.entity.AccountEntity;
import com.fastfood.entity.RoleEntity;
import com.fastfood.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
		

		if (userEntity == null) {
			return null;
			
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity role : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		MyUser myUser = new MyUser(userEntity.getId(), userEntity.getFullName(),username, userEntity.getPassword(), true, true, true, true,
				authorities);
		
	
		return myUser;
	}

}