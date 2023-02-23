<%@page contentType="text/html; ISO-8859-1" pageEncoding="utf-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>模拟司机客户端</title>
</head>

<body>
<h1>模拟司机客户端</h1>
<button onclick="setMessageContext('鼠标点击了')">测试message展示</button>
<div id="message">展示服务端推送过来消息的地方</div>

</body>
<script type="text/javascript">
    function setMessageContext(content){
        document.getElementById("message").innerHTML += (content + '<br />')
    }
</script>
</html>