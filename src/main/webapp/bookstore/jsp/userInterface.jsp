<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="UTF-8">
	<title>Order Administration System</title>  
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
<title>Welcome!</title>
</head>
<body>
<%@ page import="java.io.IOException"%>
<%@ page import ="java.io.PrintWriter"%>
<%@ page import ="java.sql.Connection"%>
<%@ page import ="java.sql.DriverManager"%>
<%@ page import ="java.sql.PreparedStatement"%>
<%@ page import ="java.sql.ResultSet"%>
<%@ page import ="java.sql.SQLException"%>
<%@ page import ="java.util.Random"%>

<%@ page import ="javax.annotation.Resource"%>
<%@ page import="javax.servlet.ServletException"%>
<%@ page import= "javax.servlet.annotation.WebServlet"%>
<%@ page import ="javax.servlet.http.HttpServlet"%>
<%@ page import ="javax.servlet.http.HttpServletRequest"%>
<%@ page import ="javax.servlet.http.HttpServletResponse"%>
<%@ page import ="javax.sql.DataSource"%>
<%@ page import ="java.sql.*"%>
<%@ page import ="javax.servlet.http.HttpSession" %>
<h2>Hello,<%=request.getSession().getAttribute("name") %></h2>
<p>Here you can browse your orders or books</p>
<a id="btn" href="userOrders.jsp" class="easyui-linkbutton" data-options="iconCls:'icon-search'">View My orders</a>
<a id="btn" href="userBooks.jsp" class="easyui-linkbutton" data-options="iconCls:'icon-search'">View the books</a>
<a id="btn" href="OrderitemPro!UserInit" class="easyui-linkbutton" data-options="iconCls:'icon-search'">View the orderitems</a>
<a id="btn" href="CartPro!UserInit" class="easyui-linkbutton" data-options="iconCls:'icon-search'">View the shopping carts</a>

<br><br>
<div >
	<a href="AccountPro!logout" class="easyui-linkbutton" iconCls="icon-cancel">Log out</a>
	
</div>
</br></br>
</body>
</html>