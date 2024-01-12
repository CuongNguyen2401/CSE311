package com.fastfood.api.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.dto.AccountDTO;

@RestController(value = "userAPIofAdmin")
public class UserAPI {
	@PostMapping("admin/api/user")
	public AccountDTO createUser(@RequestBody AccountDTO accountDTO) {
		return accountDTO;
	}

}
