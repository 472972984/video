<%@ page language="java" pageEncoding="UTF-8"%><%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Java后端WebSocket的Tomcat实现</title>
    　　　　　　<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />
    　　　　　　<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
Welcome<br/><input id="text" type="text"/>
<button onclick="send()">发送消息</button>
<hr/>
<button onclick="closeWebSocket()">关闭WebSocket连接</button>
<hr/>
<div id="message"></div>
</body>

<script type="text/javascript">
    var websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        // websocket = new WebSocket("ws://localhost:8080/websocketDemo/myWebSocket");
        websocket = new WebSocket("ws://localhost:8080/video/websocket");
        alert(websocket)
    }
    else {
        alert('当前浏览器 Not support websocket')
    }
</script>


</body>
</html>
