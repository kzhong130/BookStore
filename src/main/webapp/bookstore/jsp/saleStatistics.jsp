<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

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

<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Book"%>
<%@ page import="model.Cart"%>
<%@ page import="model.User"%>

<head>
	<meta charset="UTF-8">
	<title>Statistics</title>  
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
</head>
<body>
	<h1 style="color:red">Statistics</h1>

<form action="SearchPro" method="POST"><br />
Book ID:<input type="text" name="bookId"/><br />
User ID:<input type="text" name="userId"/><br />
Book catagory:<input type="text" name="catagory"/><br />
start date:<input type="text" name="startTime"/><br />
end date:<input type="text" name="endTime"/><br />

<br/>
<input type="submit" value="search" /></br>
</form>

	<a href="BookPro" class="easyui-linkbutton"  plain="false" >Administrate book</a>
	<a href="UserPro" class="easyui-linkbutton"  plain="false" >Administrate user</a>
	<a href="OrderPro" class="easyui-linkbutton"  plain="false" >Administrate order</a>
	<a href="OrderitemPro" class="easyui-linkbutton"  plain="false" >Administrate orderitem</a>
	<a href="CartPro" class="easyui-linkbutton"  plain="false" >Administrate cart</a>
</body>
</html>