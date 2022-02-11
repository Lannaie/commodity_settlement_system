<%@ page import="java.sql.Connection" %>
<%@ page import="static dao.DBUtil.getConnection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Blob" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="jdk.internal.util.xml.impl.Input" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.ByteArrayInputStream" %>
<%@ page import="static dao.DBUtil.executeQuery" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/1/14
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取图片</title>
</head>
<body>
<%
    String imgid = request.getParameter("imgid");
    String sql = "SELECT picture FROM COMMODITY WHERE imgid = ?";
    try( ResultSet rs = executeQuery(getConnection().prepareStatement(sql), new Object[]{imgid}) )
    {
        if( rs.next() )
        {
            // 创建输入输出流
            InputStream inputStream = rs.getBinaryStream("picture");
            OutputStream outputStream = response.getOutputStream(); // 发送给jsp页面
            int num = -1;
            while ((num = inputStream.read()) != -1)
            {
                outputStream.write(num);
            }
            outputStream.flush();

            // 关闭输入输出流
            inputStream.close();
            outputStream.close();
        }
    }
%>
</body>
</html>
