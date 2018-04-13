package com.fisher.blockchain.service;

import java.util.ArrayList;

import com.fisher.blockchain.model.Block;


public interface BlockService {

	boolean isValidChain(ArrayList<Block> blockchainToValidate);
	
	ArrayList<Block> getBlockchain();

	String calculateHash(int index, String previousHash, long timestamp, String data, int nonce, int difficulty);

	Block generateNextBlock(String blockData);

	Block getLatestBlock();

	boolean isValidNewBlock(Block newBlock, Block previousBlock);

	String calculateHashForBlock(Block block);

	boolean addBlock (Block newBlock);

	void replaceChain (ArrayList<Block> newBlocks);

	void broadcast(String responseLatestMsg);

	String responseLatestMsg();

}
