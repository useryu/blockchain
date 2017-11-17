package com.fisher.blockchain.service;

import java.util.Date;

public interface BlockService {

	public String calculateHash(int index, String previousHash, long timestamp, String data);

}
