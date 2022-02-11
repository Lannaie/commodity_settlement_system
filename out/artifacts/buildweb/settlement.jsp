<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/12/28
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品结算</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/style.css?v=<%= System.currentTimeMillis()%>">
</head>
<body>
<main>
    <form onsubmit="checkPayword()">
        <div class="pay">
            <div class="content" id="content">
                <div class="tab">请输入支付密码：</div>
                <input type="password" maxlength="1" name="pay">
                <input type="password" maxlength="1" name="pay">
                <input type="password" maxlength="1" name="pay">
                <input type="password" maxlength="1" name="pay">
                <input type="password" maxlength="1" name="pay">
                <input type="password" maxlength="1" name="pay">
            </div>
            <div class="point">请填写六位密码</div>
            <input type="button" value="支付" onclick="this.form.onsubmit()">
        </div>
    </form>
</main>
</body>
<script type="text/javascript">
    function checkPayword() {
        var password = document.getElementsByName("pay")
        // console.log(password[0].value)
        var f = 0
        var passwordstr = ""

        for (var i = 0; i < password.length; i++) {
            if (password[i].value == '')
            {
                alert('支付密码须为六位！')
                f = 1
                break
            }
            passwordstr += password[i].value
        }
        if (!f)
        {
            var params = location.search.substring(1).split("&")
            var username = params[0].split("=")[1]
            var imgid = params[1].split("=")[1]
            // console.log(username)
            var exp = new Date()
            exp.setTime(exp.getTime() + 1000)
            document.cookie = "username=" + username + ";expires=" + exp.toUTCString()
            document.cookie = "imgid=" + imgid + ";expires=" + exp.toUTCString()
            document.cookie = "password=" + passwordstr + ";expires=" + exp.toUTCString()
            location.href='settlementServlet';
        }
    }
</script>
</html>
