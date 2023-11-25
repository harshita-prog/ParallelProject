package com.cg.project.service;

import java.util.List;

import com.cg.project.bean.Account;
import com.cg.project.exception.AccountException;

public interface AccountService {
	
	public List<Account> addAccount(Account account) throws AccountException;
	
	public Account getAccountDetailsByAccountNumber(int accnumber) throws AccountException;
	
	public Account depositMoney(int accnumber, int amount) throws AccountException;
	
	public Account withdrawMoney(int accnumber, int amount) throws AccountException;
	
    public boolean fundTransferUpdate(int accountNo1, int accountNo2, int amount) throws AccountException;
    
    public List<Account> updateDetials(Account account) throws AccountException;
    
    public Account getAccountDetailsByUsername(String username, String password) throws AccountException;
    
 
}
