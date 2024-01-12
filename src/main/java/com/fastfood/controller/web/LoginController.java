package com.fastfood.controller.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.dto.MyUser;
import com.fastfood.oauth2.RestFB;
import com.fastfood.oauth2.RestGoogle;
import com.fastfood.utils.GooglePojo;
import com.fastfood.utils.SecurityUtils;

@Controller
public class LoginController {

	@Autowired
	private RestFB restFB;

	@Autowired
	private RestGoogle googleUtils;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/login?accessDenied");
	}

	@RequestMapping("/AccessFacebook/login-facebook")
	public String loginFacebook(HttpServletRequest request) {
		String code = request.getParameter("code");
		String accessToken = "";
		try {
			accessToken = restFB.getToken(code);
		} catch (IOException e) {

			return "login?facebook=error";
		}

		com.restfb.types.User user = restFB.getUserInfo(accessToken);

		MyUser userDetail = (MyUser) restFB.buildUser(user);

		SecurityUtils.setPrincipal(userDetail, request);

		return "redirect:/user/" + userDetail.getId();
	}

	@RequestMapping("/login-google")
	public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");

		if (code == null || code.isEmpty()) {
			return "redirect:/login?message=google_error";
		}
		String accessToken = googleUtils.getToken(code);

		GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
		UserDetails userDetail = googleUtils.buildUser(googlePojo);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/home";
	}

}
