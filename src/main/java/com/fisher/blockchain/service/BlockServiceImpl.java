package com.fisher.blockchain.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fisher.blockchain.model.Block;

public class BlockServiceImpl implements BlockService {

	private Logger logger = Logger.getLogger(BlockServiceImpl.class);
	
	private ObjectMapper jsonMapper = new ObjectMapper();

	private LinkedList<Block> blockchain;

	public LinkedList<Block> getBlockchain() {
		return blockchain;
	}

	public void BlockService() {
		this.blockchain = new LinkedList<Block>();
		this.blockchain.add(this.getGenesisBlock());
	}

	public String calculateHash(int index, String previousHash, long timestamp, String data) {
		StringBuilder sb = new StringBuilder();
		sb.append(index).append(previousHash).append(timestamp).append(data);
		MessageDigest messageDigest;
		String encodeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(sb.toString().getBytes("UTF-8"));
			encodeStr = this.byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeStr;
	}

	public Block generateNextBlock(String blockData) {
		Block previousBlock = getLatestBlock();
		int nextIndex = previousBlock.getIndex() + 1;
		long nextTimestamp = (new Date()).getTime() / 1000;
		String nextHash = calculateHash(nextIndex, previousBlock.getHash(), nextTimestamp, blockData);
		return new Block(nextIndex, previousBlock.getHash(), nextTimestamp, blockData, nextHash);
	};

	private Block getGenesisBlock() {
		return new Block(0, "0", 1465154705, "my genesis block!!",
				"816534932c2b7154836da6afc367695e6337db8a921823784c14378abed4f7d7");
	};

	public Block getLatestBlock() {
		return this.blockchain.getLast();
	}

	public boolean isValidNewBlock(Block newBlock, Block previousBlock) {
		if (previousBlock.getIndex() + 1 != newBlock.getIndex()) {
			logger.info("invalid index");
			return false;
		} else if (!previousBlock.getHash().equals(newBlock.getPreviousHash())) {
			logger.info("invalid previoushash");
			return false;
		} else if (!(calculateHashForBlock(newBlock).equals(newBlock.getHash()))) {
			logger.info(newBlock.getHash() + "" + calculateHashForBlock(newBlock));
			logger.info("invalid hash: " + calculateHashForBlock(newBlock) + " " + newBlock.getHash());
			return false;
		}
		return true;
	};

	public String calculateHashForBlock(Block block) {
		return calculateHash(block.getIndex(), block.getPreviousHash(), block.getTimestamp(), block.getData());  
	}
	
	public void addBlock (Block newBlock) {  
	    if (isValidNewBlock(newBlock, getLatestBlock())) {  
	        blockchain.addLast(newBlock);  
	    }  
	};

	public void replaceChain (LinkedList<Block> newBlocks) {  
	    if (isValidChain(newBlocks) && newBlocks.size() > blockchain.size()) {  
	        logger.info("Received blockchain is valid. Replacing current blockchain with received blockchain");  
	        blockchain = newBlocks;  
	        broadcast(responseLatestMsg());
	    } else {  
	    	logger.info("Received blockchain invalid");  
	    }  
	};
	
	public void broadcast(Object responseLatestMsg) {
		// TODO Auto-generated method stub
		
	}

	private Object responseLatestMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isValidChain(LinkedList<Block> blockchainToValidate) {
		if (!blockchainToValidate.getFirst()
				.equals(getGenesisBlock())) {  
	        return false;  
	    }
	    LinkedList<Block> tempBlocks = new LinkedList<Block>();
	    tempBlocks.add(blockchainToValidate.getFirst());
	    for (int i = 1; i < blockchainToValidate.size(); i++) {  
	        if (isValidNewBlock(blockchainToValidate.get(i), tempBlocks.get(i-1))) {  
	            tempBlocks.push(blockchainToValidate.get(i));  
	        } else {  
	            return false;  
	        }  
	    }  
	    return true;
	}

	/**
	 * 将byte转为16进制
	 * 
	 * @param bytes
	 * @return
	 */
	private String byte2Hex(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for (int i = 0; i < bytes.length; i++) {
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length() == 1) {
				stringBuffer.append("0");
			}
			stringBuffer.append(temp);
		}
		return stringBuffer.toString();
	}
}
