package com.fastfood.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.dto.AccountDTO;
import com.fastfood.service.IAccountService;
import com.fastfood.utils.SecurityUtils;

@RestController(value = "userAPI")
public class UserAPI {
	@Autowired
	private IAccountService accountService;

	@PostMapping("/api/user")
	public AccountDTO createUser(@RequestBody AccountDTO accountDTO) {
		return accountService.save(accountDTO);
	}

	@PutMapping("/api/user")
	public ResponseEntity<AccountDTO> modifyUser(@RequestBody AccountDTO accountDTO) {
		if (!SecurityUtils.checkCurrentID(accountDTO.getId())) {
			new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(accountService.update(accountDTO), HttpStatus.OK);
	}

}
