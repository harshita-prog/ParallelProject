package com.cg.project.service;

import java.util.List;

public interface TransactionService {

	public void addTransaction(String msg, int num);
	
	public List<String> getTransaction(int id);
}
