package com.fisher.blockchain.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description= "区块体数据")
public class BlockBody {

	@ApiModelProperty(value = "本区块包含的交易清单")
	private List<Transaction> transactions;

	@ApiModelProperty(value = "本区块传递的信息")
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
