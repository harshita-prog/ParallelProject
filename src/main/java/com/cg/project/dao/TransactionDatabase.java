package com.cg.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.project.bean.Transaction;

@Repository
public interface TransactionDatabase extends JpaRepository<Transaction, Integer>{

}