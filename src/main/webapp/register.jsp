<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/12/26
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/style.css?v=<%= System.currentTimeMillis()%>">
</head>
<body>
<main>
    <div class="login_register">
        <div class="modal">
            <div class="tab"> 注册 </div>
            <div class="content">
                <form action="" method="post" onsubmit="checkPassword()">
                    <div class="input_field">
                        <input id="username" type="text" required="required" placeholder="用户名">
                    </div>
                    <div class="input_field">
                        <input id="password1" required="required" type="password" placeholder="密码">
                    </div>
                    <div class="input_field">
                        <input id="password2" required="required" type="password" placeholder="确认密码">
                    </div>
                    <div class="input_field">
                        <input type="password" required="required" id="payword1" placeholder="请点击输入支付密码">
                    </div>
                    <div class="input_field">
                        <input type="password" required="required" id="payword2" placeholder="请重新输入支付密码">
                    </div>
                    <div class="input_field">
<%--                        <input type="submit" value="注册">--%>
                        <input type="button" value="注册" onclick="this.form.onsubmit()">
                    </div>
                    <div class="input_field">
                        <a href="login.jsp">已有账号？去登录</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>
</body>
<script type="text/javascript">
    function checkPassword() {
        if (document.getElementById("username").value == "") {
            alert("用户名为空。")
        }else if (document.getElementById("password1").value == "" || document.getElementById("password2").value == ""){
            alert("密码或确认密码为空。")
        }else if (document.getElementById("payword1").value == "" || document.getElementById("payword2").value == ""){
            alert("支付密码或确认支付密码为空。")
        }else if(document.getElementById("payword1").value != document.getElementById("payword2").value){
            alert("支付密码与确认支付密码不一致")
        }else if (document.getElementById("password1").value != document.getElementById("password2").value) {
            alert("密码与确认密码不一致")
        }else if (document.getElementById("password1").value.length > 10){
            alert("密码不得超过10位")
        }else if(document.getElementById("payword1").value.length != 6){
            alert("支付密码必须是6位")
        } else {
            var username = document.getElementById("username").value
            var password = document.getElementById("password1").value
            var payword = document.getElementById("payword1").value

            var exp = new Date()
            exp.setTime(exp.getTime() + 1000)
            document.cookie = "username=" + username + ";expires=" + exp.toUTCString()
            document.cookie = "password=" + password + ";expires=" + exp.toUTCString()
            document.cookie = "payword=" + payword + ";expires=" + exp.toUTCString()

            // console.log(username + " " + password + " " + payword)
            location.href = 'registerServlet'
        }
    }
</script>
</html>
