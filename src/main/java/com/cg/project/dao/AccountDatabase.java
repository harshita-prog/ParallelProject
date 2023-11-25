package com.cg.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.project.bean.Account;

@Repository
public interface AccountDatabase extends JpaRepository<Account, Integer>{

	
	@Query("from Account where username=:u")
	Account getAccountDetailsByUsername(@Param("u") String username);
}