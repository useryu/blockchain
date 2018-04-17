package com.fisher.blockchain.model;

import java.util.List;

public class BlockBody {

	private List<Transaction> transactions;

	private String msg;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "BlockBody [transactions=" + transactions + ", msg=" + msg + "]";
	}

}
