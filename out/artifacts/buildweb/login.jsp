<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/12/25
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/style.css?v=<%= System.currentTimeMillis()%>">
</head>
<body>
<main>
    <div class="login_register">
        <div class="modal">
            <div class="tab">
                登录
            </div>
            <div class="content">
                <form action="loginServlet" method="post">
                      <div class="input_field">
                          <input name="username" type="text" required="required" placeholder="用户名">
                      </div>
                      <div class="input_field">
                          <input name="password" type="password" required="required" placeholder="密码">
                      </div>
                      <div class="input_field">
                          <input type="submit" value="登录">
                      </div>
                      <div class="input_field">
                          <a href="register.jsp">没有账号？去注册</a>
                      </div>
                </form>
            </div>
        </div>
    </div>
</main>
</body>
</html>
