package com.cg.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.project.bean.Account;
import com.cg.project.bean.Transaction;
import com.cg.project.dao.AccountDatabase;
import com.cg.project.exception.AccountException;




@Service
public class AccountServiceImpl implements AccountService{

	
	@Autowired
	AccountDatabase accountDatabase;
	
	
	@Override
	public Account getAccountDetailsByAccountNumber(int accnumber) throws AccountException {
		try {
			return accountDatabase.findById(accnumber).get();
		} catch (Exception e) {
			throw new AccountException(e.getMessage());
		}	
		
	}


	@Override
	public List<Account> addAccount(Account account) throws AccountException {
		try {
			accountDatabase.save(account);
			return accountDatabase.findAll();
		} catch (Exception e) {
			throw new AccountException(e.getMessage());
		}
	}


	@Override
	public Account depositMoney(int accnumber, int amount) throws AccountException {
		try {
			Optional<Account> optional = accountDatabase.findById(accnumber);
			if(optional.isPresent()) {
				Account acc = optional.get();
				acc.setBalance(optional.get().getBalance() + amount);
				accountDatabase.save(acc);
				return getAccountDetailsByAccountNumber(accnumber);
			}
			else
				throw new AccountException("Account Number (" + accnumber + ") does not Exist");
		} catch (Exception e) {
			throw new AccountException(e.getMessage());
		}
	}


	@Override
	public Account withdrawMoney(int accnumber, int amount) throws AccountException {
		try {
			Optional<Account> optional = accountDatabase.findById(accnumber);
			if(optional.isPresent()) {
				Account acc = optional.get();
				acc.setBalance(optional.get().getBalance() - amount);
				accountDatabase.save(acc);
				return getAccountDetailsByAccountNumber(accnumber);
			}
			else
				throw new AccountException("Account Number (" + accnumber + ") does not Exist");
		} catch (Exception e) {
			throw new AccountException(e.getMessage());
		}
	}
	
	
	@Override
    public boolean fundTransferUpdate(int accountNo1, int accountNo2, int amount)
            throws AccountException {
       
        try {
			Account temp1 = accountDatabase.findById(accountNo1).get();
			temp1.setBalance(temp1.getBalance()-amount);
			Account temp2 = accountDatabase.findById(accountNo2).get();
			temp2.setBalance(temp2.getBalance()+amount);
			accountDatabase.save(temp1);
			accountDatabase.save(temp2);
			return true;
		} catch (Exception e) {
			throw new AccountException(e.getMessage());
		}
       
       
    }


	@Override
	public List<Account> updateDetials(Account account) throws AccountException {
		try {
			
				accountDatabase.save(account);
				return accountDatabase.findAll();
			
			
		} catch (Exception e) {
			throw new AccountException(e.getMessage());
		}
	}


	@Override
	public Account getAccountDetailsByUsername(String username, String password) throws AccountException {
		try {
			Account temp = accountDatabase.getAccountDetailsByUsername(username);
			if(temp.getPassword().equals(password)) {
				return accountDatabase.getAccountDetailsByUsername(username);
			}
			else {
				throw new AccountException("Details Invalid");
			}
		} catch (Exception e) {
			throw new AccountException(e.getMessage());
		}
	}
	
	
	

}
