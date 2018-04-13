package com.fisher.blockchain.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fisher.blockchain.model.Block;
import com.fisher.blockchain.model.BlockMsg;
import com.fisher.blockchain.model.MsgType;

@Component
public class BlockServiceImpl implements BlockService {

	private Logger logger = Logger.getLogger(BlockServiceImpl.class);

	private ArrayList<Block> blockchain;

	public ArrayList<Block> getBlockchain() {
		if(this.blockchain==null) {
			this.blockchain = new ArrayList<Block>();
			this.blockchain.add(this.getGenesisBlock());
		}
		return blockchain;
	}

	public void BlockService() {
	}

	public String calculateHash(int index, String previousHash, long timestamp, String data, int nonce) {
		StringBuilder sb = new StringBuilder();
		sb.append(index).append(previousHash).append(timestamp).append(data).append(nonce);
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
		int difficulty = previousBlock.getNonce()+1;
		String nextHash = calculateHash(nextIndex, previousBlock.getHash(), nextTimestamp, blockData, difficulty);
		String difficultyStr = getDifficultyStr(difficulty);
		while(!nextHash.substring(0, difficulty).equals(difficultyStr)) {
			nextHash = calculateHash(nextIndex, previousBlock.getHash(), nextTimestamp, blockData, difficulty);
		}
		return new Block(nextIndex, previousBlock.getHash(), nextTimestamp, blockData, nextHash);
	};

	private String getDifficultyStr(int difficulty) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<difficulty;i++) {
			sb.append("0");
		}
		return sb.toString();
	}

	private Block getGenesisBlock() {
		return new Block(0, "0", 1465154705, "my genesis block!!",
				this.calculateHash(0, "0", 1465154705, "my genesis block!!", 0));
	};

	public Block getLatestBlock() {
		return this.getBlockchain().get(this.blockchain.size()-1);
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
		return calculateHash(block.getIndex(), block.getPreviousHash(), block.getTimestamp(), block.getData(), block.getNonce());
	}

	public boolean addBlock(Block newBlock) {
		if (isValidNewBlock(newBlock, getLatestBlock())) {
			blockchain.add(newBlock);
			return true;
		}
		return false;
	};

	public void replaceChain(ArrayList<Block> newBlockChainFromPeers) {
		if (isValidChain(newBlockChainFromPeers) && newBlockChainFromPeers.size() > blockchain.size()) {
			logger.info("Received blockchain is valid. Replacing current blockchain with received blockchain");
			blockchain = newBlockChainFromPeers;
			broadcast(responseLatestMsg());
		} else {
			logger.info("Received blockchain invalid");
		}
	};

	public void broadcast(String responseLatestMsg) {
		// TODO Auto-generated method stub

	}

	public String responseLatestMsg() {
		BlockMsg msg = new BlockMsg();
		msg.setData(this.getLatestBlock().toString());
		msg.setType(MsgType.RESPONSE_BLOCKCHAIN);
		return msg.toString();
	}

	public boolean isValidChain(ArrayList<Block> blockchainToValidate) {
		if (!blockchainToValidate.get(0).equals(getGenesisBlock())) {
			return false;
		}
		ArrayList<Block> tempBlocks = new ArrayList<Block>();
		tempBlocks.add(blockchainToValidate.get(0));
		for (int i = 1; i < blockchainToValidate.size(); i++) {
			if (isValidNewBlock(blockchainToValidate.get(i), tempBlocks.get(i - 1))) {
				tempBlocks.add(blockchainToValidate.get(i));
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
