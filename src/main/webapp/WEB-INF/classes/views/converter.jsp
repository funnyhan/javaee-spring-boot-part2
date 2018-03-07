<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/6
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>HttpMessageConverter Demo</title>
</head>
<body>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="static/jquery-3.3.1.min.js" type="text/javascript"></script>

<script type="text/javascript">
    function requestConverter(){
        $.ajax({
            url: "convert",
            data: "wang-yunfei",//注意此处的格式
            type:"POST",
            contentType:"application/x-wisely",
            success: function(data){
                $("#resp").html(data);
            }
        });
    }
</script>
    <div id="resp"></div><input type="button" onclick="requestConverter();" value="请求">

</body>
</html>
