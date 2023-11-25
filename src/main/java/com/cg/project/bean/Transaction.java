package com.cg.project.bean;

import javax.persistence.*;

@Table(name="transaction")
@Entity
public class Transaction {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name="num")
	int num;
	@Column(name="msg")
	String msg;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Transaction(int id, int num, String msg) {
		super();
		this.id = id;
		this.num = num;
		this.msg = msg;
	}
	
	public Transaction() {
		
	}
	
}
