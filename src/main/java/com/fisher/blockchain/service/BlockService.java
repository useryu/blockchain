package com.fisher.blockchain.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.fisher.blockchain.model.Block;
import com.fisher.blockchain.model.BlockBody;
import com.fisher.blockchain.model.Transaction;


public interface BlockService {

	boolean isValidChain(ArrayList<Block> blockchainToValidate);
	
	ArrayList<Block> getBlockchain();

	String calculateHash(int index, String previousHash, long timestamp, BlockBody data, int nonce, int difficulty);

	Block generateNextBlock(String blockData);

	Block getLatestBlock();

	boolean isValidNewBlock(Block newBlock, Block previousBlock);

	String calculateHashForBlock(Block block);

	boolean addBlock (Block newBlock);

	void replaceChain (ArrayList<Block> newBlocks);

	void broadcast(String responseLatestMsg);

	BigDecimal getBalanceOfAddress(String address);
	
	String responseLatestMsg();

	boolean acceptTransaction(Transaction transaction);

}
