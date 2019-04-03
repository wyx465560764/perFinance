<%@ page contentType="text/html" language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML >
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/images/shortcuticon.ico" />
        <title>注销页面</title>
    </head>
  <body>
    <%
    response.setHeader("refresh","3;URL=index.jsp");
    session.invalidate();
    %>
     <h3>你已经成功退出本系统,3秒后会跳转到首页</h3>
     <h3>如果没有跳转请点击<a href="index.jsp">这里</a></h3>
  </body>
</html>
