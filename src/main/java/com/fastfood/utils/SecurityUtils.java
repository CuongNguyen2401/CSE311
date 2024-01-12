package com.fastfood.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.fastfood.dto.MyUser;

public class SecurityUtils {

	public static MyUser getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication instanceof AnonymousAuthenticationToken)
			return null;

		MyUser myUser = (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
		return myUser;

	}
	
	public static void setPrincipal(UserDetails userDetail ,HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		

	}

	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities() {
		List<String> results = new ArrayList<String>();

		List<GrantedAuthority> authorities = (List<GrantedAuthority>) (SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities());
		for (GrantedAuthority authority : authorities) {
			results.add(authority.getAuthority());

		}
		return results;
	}

	public static boolean checkCurrentID(long id) {
		if (getPrincipal() != null)
			if (getPrincipal().getId() != id)
				return false;
		return true;

	}
}
