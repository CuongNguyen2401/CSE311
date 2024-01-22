package com.fastfood.service;

import com.fastfood.dto.AccountDTO;

public interface IAccountService {
	AccountDTO save(AccountDTO dto);

	AccountDTO findByUsername(String userName);

	AccountDTO findById(long id);

	AccountDTO saveFBAccount(AccountDTO dto);

	AccountDTO update(AccountDTO dto);

}
