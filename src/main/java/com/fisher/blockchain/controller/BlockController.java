package com.fisher.blockchain.controller;
  
import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fisher.blockchain.model.Block;
import com.fisher.blockchain.model.BlockBody;
import com.fisher.blockchain.model.Transaction;
import com.fisher.blockchain.service.BlockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="区块服务")
@Controller
public class BlockController {
	
	@Autowired
	private BlockService blockService;

	@ApiOperation(value = "查看所有区块") 
	@RequestMapping(method = RequestMethod.GET,value = "/blocks", produces = "application/json")
	@ResponseBody
	public ArrayList<Block> listBlocks() {
		return this.blockService.getBlockchain();
	}


	@ApiOperation(value = "接受交易信息") 
	@RequestMapping(method = RequestMethod.POST,value = "/acceptTransaction", produces = "application/json")
	@ResponseBody
	public Transaction acceptTransaction(Transaction transaction) throws Exception {
		boolean success = this.blockService.acceptTransaction(transaction);
        if(success) {
        	this.blockService.broadcast(this.blockService.responseLatestMsg());
        	return transaction;
        }else {
        	throw new Exception("can't accept the new transaction, try latter");
        }
	}

	@ApiOperation(value = "查看某地址余额") 
	@RequestMapping(method = RequestMethod.GET,value = "/getBalance", produces = "application/json")
	@ResponseBody
	public BigDecimal getBalance(String address) throws Exception {
		BigDecimal balance = this.blockService.getBalanceOfAddress(address);
        return balance;
	}

	@ApiOperation(value = "查看奖励") 
	@RequestMapping(method = RequestMethod.GET,value = "/showRewards")
	public String showRewards() {
		return "showRewards";
	}
	
	@ApiOperation(value = "挖矿，传入挖矿人地址接受奖励") 
	@RequestMapping(method = RequestMethod.POST,value = "/mineBlock", produces = "application/json")
	@ResponseBody
	public Block mineBlock(String miningRewardAddress) throws Exception {
        Block newBlock = this.blockService.generateNextBlock(miningRewardAddress);
        boolean success = this.blockService.addBlock(newBlock);
        if(success) {
        	this.blockService.broadcast(this.blockService.responseLatestMsg());
        	return newBlock;
        }else {
        	throw new Exception("can't mine a new block, try latter");
        }
	}
	
	/**
	 *  
    app.get('/peers', (req, res) => {  
        res.send(sockets.map(s => s._socket.remoteAddress + ':' + s._socket.remotePort));  
    });  
    app.post('/addPeer', (req, res) => {  
        connectToPeers([req.body.peer]);  
        res.send();  
    });  
    
	var initP2PServer = () => {  
	    var server = new WebSocket.Server({port: p2p_port});  
	    server.on('connection', ws => initConnection(ws));  
	    console.log('listening websocket p2p port on: ' + p2p_port);  
	  
	};  
  
	var initConnection = (ws) => {  
	    sockets.push(ws);  
	    initMessageHandler(ws);  
	    initErrorHandler(ws);  
	    write(ws, queryChainLengthMsg());  
	};  
	  
	var initMessageHandler = (ws) => {  
	    ws.on('message', (data) => {  
	        var message = JSON.parse(data);  
	        console.log('Received message' + JSON.stringify(message));  
	        switch (message.type) {  
	            case MessageType.QUERY_LATEST:  
	                write(ws, responseLatestMsg());  
	                break;  
	            case MessageType.QUERY_ALL:  
	                write(ws, responseChainMsg());  
	                break;  
	            case MessageType.RESPONSE_BLOCKCHAIN:  
	                handleBlockchainResponse(message);  
	                break;  
	        }  
	    });  
	};  
	  
	var initErrorHandler = (ws) => {  
	    var closeConnection = (ws) => {  
	        console.log('connection failed to peer: ' + ws.url);  
	        sockets.splice(sockets.indexOf(ws), 1);  
	    };  
	    ws.on('close', () => closeConnection(ws));  
	    ws.on('error', () => closeConnection(ws));  
	};  
    
    
var connectToPeers = (newPeers) => {  
    newPeers.forEach((peer) => {  
        var ws = new WebSocket(peer);  
        ws.on('open', () => initConnection(ws));  
        ws.on('error', () => {  
            console.log('connection failed')  
        });  
    });  
};  
  
var handleBlockchainResponse = (message) => {  
    var receivedBlocks = JSON.parse(message.data).sort((b1, b2) => (b1.index - b2.index));  
    var latestBlockReceived = receivedBlocks[receivedBlocks.length - 1];  
    var latestBlockHeld = getLatestBlock();  
    if (latestBlockReceived.index > latestBlockHeld.index) {  
        console.log('blockchain possibly behind. We got: ' + latestBlockHeld.index + ' Peer got: ' + latestBlockReceived.index);  
        if (latestBlockHeld.hash === latestBlockReceived.previousHash) {  
            console.log("We can append the received block to our chain");  
            blockchain.push(latestBlockReceived);  
            broadcast(responseLatestMsg());  
        } else if (receivedBlocks.length === 1) {  
            console.log("We have to query the chain from our peer");  
            broadcast(queryAllMsg());  
        } else {  
            console.log("Received blockchain is longer than current blockchain");  
            replaceChain(receivedBlocks);  
        }  
    } else {  
        console.log('received blockchain is not longer than received blockchain. Do nothing');  
    }  
};



var write = (ws, message) => ws.send(JSON.stringify(message));  
var broadcast = (message) => sockets.forEach(socket => write(socket, message));

connectToPeers(initialPeers);  
initHttpServer();  
initP2PServer();

	 */
}
