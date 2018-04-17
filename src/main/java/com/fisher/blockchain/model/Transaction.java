package com.fisher.blockchain.model;

import java.math.BigDecimal;

public class Transaction {

	private String fromAddress;
	private String toAddress;
	private BigDecimal amount;

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [fromAddress=" + fromAddress + ", toAddress=" + toAddress + ", amount=" + amount + "]";
	}

}
