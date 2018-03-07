<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/6
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SSE Demo</title>
</head>
<body>
<div id="msgFromPush"></div>
<script src="static/jquery-3.3.1.min.js" type="text/javascript"></script>
<script  type="text/javascript">
    if(!!window.EventSource){   //  1   EventSource只有新式的浏览器才有，EventSource是SSE的客户端
        var source = new EventSource('push');   //  2   添加SSE客户端监听，在此处获得服务器推送的消息
        s='';
        source.addEventListener('message',function(e){
            s+=e.data+"<br/>"
            $("#msgFromPush").html(s);
        })

        source.addEventListener('open',function (e){
            console.log("连接打开.");
        } ,false);
        source.addEventListener('error',function (e){
            if(e.readyState==EventSource.CLOSED){
                console.log("连接打开");
            }else{
                console.log(e.readyState);
            }
        },false);
    }else{
        console.log("你的浏览器不支持SSE")
    }
</script>

</body>
</html>
