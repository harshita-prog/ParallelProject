package com.cg.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.project.bean.Transaction;
import com.cg.project.dao.TransactionDatabase;


@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	TransactionDatabase transactionDatabase;

	@Override
    public void addTransaction(String msg, int num) {
        int id = (int) Math.ceil((Math.random()*1000));
        Transaction t = new Transaction(id, num, msg);
        transactionDatabase.save(t);
    }

 

    @Override
    public List<String> getTransaction(int id) {
        List<Transaction> ls = transactionDatabase.findAll();
        List<String> listShow = new ArrayList<String>();
        for(Transaction t: ls) {
            if(t.getNum() == id) {
                listShow.add(t.getMsg());
            }
        }
        return listShow;
        
    }
}
