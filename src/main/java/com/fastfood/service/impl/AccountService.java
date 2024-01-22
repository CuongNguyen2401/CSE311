package com.fastfood.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.AccountDTO;
import com.fastfood.entity.AccountEntity;
import com.fastfood.entity.RoleEntity;
import com.fastfood.mapper.AccountMapper;
import com.fastfood.repository.RoleRepository;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IAccountService;
import com.fastfood.utils.MessageUtil;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder BCryptPasswordEncoder;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional
	public AccountDTO save(AccountDTO dto) {
		AccountEntity accountEntity = userRepository.findOneByUserNameAndStatus(dto.getUsername(),
				SystemConstant.ACTIVE_STATUS);
		if (accountEntity == null) {
			accountEntity = accountMapper.mapToEntity(dto);
			List<RoleEntity> roles = new ArrayList<RoleEntity>();
			roles.add(roleRepository.findByName("USER"));
			accountEntity.setRoles(roles);
			accountEntity.setPassword(BCryptPasswordEncoder.encode(dto.getPassword()));
			return accountMapper.mapToDTO(userRepository.save(accountEntity));
		}
		return null;
	}

	@Override
	public AccountDTO findByUsername(String userName) {
		AccountEntity entity = userRepository.findOneByUserNameAndStatus(userName, SystemConstant.ACTIVE_STATUS);
		AccountDTO dto = accountMapper.mapToDTO(entity);
		return dto;
	}

	@Override
	@Transactional
	public AccountDTO saveFBAccount(AccountDTO dto) {
		AccountEntity accountEntity = userRepository.findOneByUserNameAndStatus(dto.getUsername(),
				SystemConstant.ACTIVE_STATUS);
		if (accountEntity == null) {
			accountEntity = accountMapper.mapToEntity(dto);
			List<RoleEntity> roles = new ArrayList<RoleEntity>();
			roles.add(roleRepository.findByName("USER"));
			accountEntity.setRoles(roles);
			accountEntity.setPassword(BCryptPasswordEncoder.encode("hdhsiuh2"));
			return accountMapper.mapToDTO(userRepository.save(accountEntity));
		}
		return null;
	}

	@Override
	public AccountDTO findById(long id) {
		AccountEntity accountEntity = userRepository.findOne(id);
		
		return accountMapper.mapToDTO(userRepository.save(accountEntity));
	}

	@Override
	@Transactional
	public AccountDTO update(AccountDTO dto) {
		AccountEntity accountEntity = userRepository.findOne(dto.getId());
		if (accountEntity == null) {
			throw new UsernameNotFoundException(MessageUtil.USER_ID_NOT_FOUND + dto.getId());
		}
		accountEntity.setAddress(dto.getAddress());
		accountEntity.setEmail(dto.getEmail());
		accountEntity.setFullName(dto.getFullName());
		accountEntity.setPhoneNumber(dto.getPhoneNumber());

		return mapper.map(userRepository.save(accountEntity), AccountDTO.class);

	}

}
