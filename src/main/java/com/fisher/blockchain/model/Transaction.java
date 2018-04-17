package com.fisher.blockchain.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description= "交易数据")
public class Transaction {

	@ApiModelProperty(value = "转出地址", example="fisher's address")
	private String fromAddress;
	@ApiModelProperty(value = "转入地址", example="bob's address")
	private String toAddress;
	@ApiModelProperty(value = "交易金额", example="100")
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
