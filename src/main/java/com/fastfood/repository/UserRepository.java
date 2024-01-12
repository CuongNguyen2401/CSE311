package com.fastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fastfood.entity.AccountEntity;

public interface UserRepository extends JpaRepository<AccountEntity, Long> {
	AccountEntity findOneByUserNameAndStatus(String name, int status);
	
	
}
