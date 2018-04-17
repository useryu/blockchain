package com.fisher.blockchain.model;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description= "区块")
public class Block {

	@ApiModelProperty(value = "本区块索引号")
	private int index;
	@ApiModelProperty(value = "上一个区块HASH值")
	private String previousHash;
	@ApiModelProperty(value = "区块产生时间毫秒值")
	private long timestamp;
	@ApiModelProperty(value = "区块体")
	private BlockBody data;
	@ApiModelProperty(value = "区块HASH值")
	private String hash;
	@ApiModelProperty(value = "区块随机数")
	private int nonce;
	@ApiModelProperty(value = "区块生产难度值")
	private int difficulty;		

	public Block(int index, String previousHash, long timestamp, BlockBody data, String hash, int nonce, int difficulty) {
		super();
		this.index = index;
		this.previousHash = previousHash;
		this.timestamp = timestamp;
		this.data = data;
		this.hash = hash;
		this.nonce = nonce;
		this.difficulty = difficulty;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
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


	public BlockBody getData() {
		return data;
	}

	public void setData(BlockBody data) {
		this.data = data;
	}

	public String getHash() {
		return hash;
	}

	@Override
	public String toString() {
		return "Block [index=" + index + ", previousHash=" + previousHash + ", timestamp=" + timestamp + ", data="
				+ data + ", hash=" + hash + ", nonce=" + nonce + ", difficulty=" + difficulty + "]";
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
