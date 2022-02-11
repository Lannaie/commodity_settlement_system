<%@ page import="entity.Good" %>
<%@ page import="entity.Commodity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/12/27
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gb2312" language="java" %>
<html>
<head>
    <title>选择商品</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/style.css?v=<%= System.currentTimeMillis()%>">
</head>
<body>
<main>
    <div class="goods">
        <%
            String username = request.getParameter("username");
            List<Good> goods = Commodity.getGoods();
            if( goods == null )
            {
                System.out.println("没有数据");
            }else
            {
                for( Good good : goods )
                {
        %>
        <div class="goodpage">
                <img src="image.jsp?imgid=<%=good.getImgid()%>">
                <div class="goodname">
                    <%=good.getName()%>
                </div>
                <div class="goodprice">
                    ￥<%=good.getCost()%>
                </div>
                <input value="购买" type="button" onclick="location.href = 'settlement.jsp?username=<%=username%>&imgid=<%=good.getImgid()%>'">
        </div>
        <%
                }
            }
        %>
    </div>
</main>
</body>
</html>
