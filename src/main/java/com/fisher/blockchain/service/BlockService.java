package com.fisher.blockchain.service;

import java.util.LinkedList;

import com.fisher.blockchain.model.Block;

public interface BlockService {

	boolean isValidChain(LinkedList<Block> blockchainToValidate);
	
	LinkedList<Block> getBlockchain();

	String calculateHash(int index, String previousHash, long timestamp, String data);

	Block generateNextBlock(String blockData);

	Block getLatestBlock();

	boolean isValidNewBlock(Block newBlock, Block previousBlock);

	String calculateHashForBlock(Block block);

	void addBlock (Block newBlock);

	void replaceChain (LinkedList<Block> newBlocks);

	void broadcast(String responseLatestMsg);

}
