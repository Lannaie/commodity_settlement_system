<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/12/28
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/style.css?v=<%= System.currentTimeMillis()%>">
</head>
<body>
<%!
String username = "";
String password = "";
%>
<%
username = request.getParameter("username");
password = request.getParameter("password");
%>
<main>
    <div class="login_register">
        <div class="modal">
            <div class="tab">个人信息</div>
            <div class="content">
                <form action="" onsubmit="checkPayword()">
                    <div class="input_field">
                        <input type="text" value="用户名："<%=username%> readonly="readonly">
                    </div>
                    <div class="input_field">
                        <input type="text" value="密码："<%=password%> readonly="readonly">
                    </div>
                    <div class="input_field">
                        <input type="password" required="required" id="payword1" placeholder="请点击输入支付密码">
                    </div>
                    <div class="input_field">
                        <input type="password" required="required" id="payword2" placeholder="请重新输入支付密码">
                    </div>
                    <div class="input_field">
                        <input type="button" value="保存" onclick="this.form.onsubmit()">
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>
</body>
<script type="text/javascript">
    function checkPayword() {
        if (document.getElementById("payword1").value == "" || document.getElementById("payword2").value == ""){
            alert("支付密码或确认支付密码为空。")
        }else if (document.getElementById("payword1").value == document.getElementById("payword2").value)
        {
            location.href='choose_goods.jsp'
        } else
        {
            alert("支付密码与确认支付密码不一致。")
        }
    }
</script>
</html>
