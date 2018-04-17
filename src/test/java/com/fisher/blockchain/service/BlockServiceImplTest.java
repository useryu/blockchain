package com.fisher.blockchain.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fisher.blockchain.model.Block;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:spring-mvc-servlet.xml"})
public class BlockServiceImplTest extends TestCase {
	
	private static final int NUMS = 10;
	@Autowired
	private BlockService blockService;

	public void testGetBlockchain() {
		fail("Not yet implemented");
	}

//	public void testGenerateNextBlock() {
//		for(int i=0;i<NUMS;i++) {
//			Block newBlock = this.blockService.generateNextBlock("block"+i);
//			this.blockService.addBlock(newBlock);
//			System.out.println("current chain is:"+this.blockService.getBlockchain());
//		}
//	}

}
