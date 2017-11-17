package com.fisher.blockchain.controller;
  
import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fisher.blockchain.model.Block;
import com.fisher.blockchain.service.BlockServiceImpl;  

@Controller
public class BlockController {
	
	private BlockServiceImpl blockService;

	@RequestMapping(value = "/blocks", produces = "application/json")
	@ResponseBody
	public LinkedList<Block> listBlocks() {
		return this.blockService.getBlockchain();
	}


	@RequestMapping(value = "/mineBlock", produces = "application/json")
	@ResponseBody
	public void mineBlock() {
//        var newBlock = generateNextBlock(req.body.data);  
//        addBlock(newBlock);  
//        broadcast(responseLatestMsg());  
//        console.log('block added: ' + JSON.stringify(newBlock));  
//        res.send();  
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

var responseLatestMsg = () => ({  
    'type': MessageType.RESPONSE_BLOCKCHAIN,  
    'data': JSON.stringify([getLatestBlock()])  
});  

var write = (ws, message) => ws.send(JSON.stringify(message));  
var broadcast = (message) => sockets.forEach(socket => write(socket, message));

connectToPeers(initialPeers);  
initHttpServer();  
initP2PServer();

	 */
}
