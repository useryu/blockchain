<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

<h1>可以在本地执行以下操作：</h1>
<p>查看所有区块：<a href="http://127.0.0.1:8080/blockchain/blocks" target="_blank">http://127.0.0.1:8080/blockchain/blocks</a></p>   
<p>挖矿:<a target="_blank" href="http://127.0.0.1:8080/blockchain/mineBlock?miningRewardAddress=fishersPublicAddress">http://127.0.0.1:8080/blockchain/mineBlock?miningRewardAddress=fishersPublicAddress</a></p>
<p>看余额:<a target="_blank" href="http://127.0.0.1:8080/blockchain/getBalance?address=fishersPublicAddress">http://127.0.0.1:8080/blockchain/getBalance?address=fishersPublicAddress</a></p>
<p>其他操作:<a target="_blank" href="http://127.0.0.1:8080/blockchain/swagger-ui.html">http://127.0.0.1:8080/blockchain/swagger-ui.html</a></p>
</body>
</html>
