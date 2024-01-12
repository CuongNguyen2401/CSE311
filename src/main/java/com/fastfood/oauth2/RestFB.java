package com.fastfood.oauth2;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.AccountDTO;
import com.fastfood.service.IAccountService;
import com.fastfood.service.impl.CustomUserDetailsService;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

@Component
public class RestFB {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private IAccountService accountService;

	public static String FACEBOOK_APP_ID = "3549214752035027";
	public static String FACEBOOK_APP_SECRET = "27af78d2ad059aa4458d6b2494ac16ec";
	public static String FACEBOOK_REDIRECT_URL = "https://localhost:8443/AccessFacebook/login-facebook";
	public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";

	public String getToken(final String code) throws ClientProtocolException, IOException {
		String link = String.format(FACEBOOK_LINK_GET_TOKEN, FACEBOOK_APP_ID, FACEBOOK_APP_SECRET,
				FACEBOOK_REDIRECT_URL, code);
		String response = Request.Get(link).execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}

	public com.restfb.types.User getUserInfo(final String accessToken) {
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FACEBOOK_APP_SECRET, Version.LATEST);
		return facebookClient.fetchObject("me", com.restfb.types.User.class);
	}

	public UserDetails buildUser(com.restfb.types.User user) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		String username = user.getUsername() == null ? removeAccents(SystemConstant.FACEBOOK+createAccount(user.getName())) : user.getUsername();

		UserDetails fbUser = customUserDetailsService.loadUserByUsername(username);

		if (fbUser == null) {
			AccountDTO accDTO = new AccountDTO();
			accDTO.setUsername(username);
			accDTO = accountService.saveFBAccount(accDTO);

		}

		return customUserDetailsService.loadUserByUsername(username);
	}

	public static String createAccount(String fullName) {

		String[] names = fullName.split(" ");

		if (names.length < 2) {
			return "Invalid full name";
		}

		String username = names[0] + names[names.length - 1];

		Random random = new Random();
		int randomNumber = random.nextInt(900) + 100;

		username += randomNumber;

		return username;
	}
	
	public static String removeAccents(String input) {
		String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").replaceAll(" ", "");
    }
}
