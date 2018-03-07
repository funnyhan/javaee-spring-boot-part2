<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/7
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>servlet async support</title>
</head>
<body>
<script src="static/jquery-3.3.1.min.js" type="text/javascript"></script>

<script type="text/javascript">
    <%--此处的代码使用的是jq的ajax请求，所以没有浏览器兼容性的问题--%>
    deferred(); //页面打开就向后台发送请求

    function deferred(){
        $.get('defer',function(data){
            console.log(data); //在控制台输出服务端推送的数据
            deferred(); //一次请求完成后再向后台发送请求
        });
    }
</script>
</body>
</html>
