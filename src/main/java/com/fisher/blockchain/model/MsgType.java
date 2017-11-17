package com.fisher.blockchain.model;

public enum MsgType {

	QUERY_LATEST(0),
    QUERY_ALL(1),
    RESPONSE_BLOCKCHAIN(2);
	
	public int getValue() {
		return value;
	}

	private int value;

	private MsgType(int value) {
		this.value=value;
	};

}
