package com.fisher.blockchain.model;

public class BlockMsg {

	private MsgType type;
	private String data;

	public MsgType getType() {
		return type;
	}

	public void setType(MsgType type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
