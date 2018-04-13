package com.fisher.blockchain.model;

import java.util.Date;

public class Block {

	private int index;
	private String previousHash;
	private long timestamp;
	private String data;
	private String hash;
	private int nonce;
		

	public Block(int index, String previousHash, long timestamp, String data, String hash) {
		super();
		this.index = index;
		this.previousHash = previousHash;
		this.timestamp = timestamp;
		this.data = data;
		this.hash = hash;
		this.nonce = 0;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public boolean equals(Object other) {
		Block o = (Block)other;
		return this.getIndex()==o.getIndex() 
				&& this.getTimestamp() == o.getTimestamp()
				&& this.getHash().equals(o.getHash());
	}

	
}
