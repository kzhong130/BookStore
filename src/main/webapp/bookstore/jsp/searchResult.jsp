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
	<title>Result</title>  
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
</head>
<body>
	<h1 style="color:red">Search Result</h1>
	<p><br>Click the buttons on datagrid toolbar to do crud actions.<br><br>
	Operate on the order data</p>

	<a href="BookPro" class="easyui-linkbutton"  plain="false" >Administrate book</a>
	<a href="UserPro" class="easyui-linkbutton"  plain="false" >Administrate user</a>
	<a href="OrderPro" class="easyui-linkbutton"  plain="false" >Administrate order</a>
	<a href="OrderitemPro" class="easyui-linkbutton"  plain="false" >Administrate orderitem</a>
	<a href="CartPro" class="easyui-linkbutton"  plain="false" >Administrate cart</a>

	
	<table id="dg2" title="My Orders" class="easyui-datagrid" style="width:700px;height:250px"
			toolbar="#toolbar2" pagination="true" 
			url="SearchPro!get"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="buyerId" width="50">Buyer ID</th>
				<th field="address" width="50">Address</th>
				<th field="orderitems" width="50">Orderitem list</th>
				<th field="time" width="50">Create Time</th>
				<th field="sum" width="50">Sum</th>
			</tr>
			     
		</thead>
	</table>
	<div id="toolbar2">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newOrder()">New Order</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editOrder()">Edit Order</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyOrder()">Remove Order</a>
	</div>
	<div id="dlg2" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons2">
		<div class="ftitle2">Order Information</div>
		<form id="fm2" method="post" action="test" novalidate>
					<div class="fitem2">
				<label>Buyer ID:</label>
				<select name="buyerId">
										<%
									    ArrayList<User> userList = new ArrayList<User>();
									    if(request.getAttribute("users") != null) {
									    	userList = (ArrayList<User>) request.getAttribute("users");
									    }
									    %>
									    <%
											for (int i = 0; i < userList.size(); i++) {
												User user = userList.get(i);
										%>
										<option value="<%=user.getId()%>"><%=user.getId()%></option>
										<%
											}
										%>
				</select>
			</div>
			<div class="fitem2">
				<label>Address:</label>
				<input name="address" id="i3" class="easyui-textbox" required = "true">
			</div>
			<div class="fitem2">
				<label>Create Time:</label>
				<input name="time" id="i2" class="easyui-textbox" required="true">
			</div>
			
			
		</form>
	</div>
	<div id="dlg-buttons2">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok"  style="width:90px"
		onclick="saveOrder()"
		>Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg2').dialog('close')" style="width:90px">Cancel</a>
	</div>
<br>
<div >
	<a href="AccountPro!logout" class="easyui-linkbutton" iconCls="icon-cancel">Log out</a>
	
</div>
</br>

	</script>
	<style type="text/css">
		#fm2{
			margin:0;
			padding:10px 30px;
		}
		.ftitle2{
			font-size:14px;
			font-weight:bold;
			padding:5px 0;
			margin-bottom:10px;
			border-bottom:1px solid #ccc;
		}
		.fitem2{
			margin-bottom:5px;
		}
		.fitem2 label{
			display:inline-block;
			width:80px;
		}
		.fitem2 input{
			width:160px;
		}
	</style>
	
	
	<script type="text/javascript">
		var url;
		function newOrder(){
			$('#dlg2').dialog('open').dialog('setTitle','New Order');
			$('#fm2').form('clear');
			url="OrderPro!add"
		}
		function editOrder(){
			var row = $('#dg2').datagrid('getSelected');
			if (row){
				$('#dlg2').dialog('open').dialog('setTitle','Edit Order');
				$('#fm2').form('load',row);
				url = "OrderPro!update?id="+row.id;
			}
		}
		function saveOrder(){
			$('#fm2').form('submit',{
				url: url,
				onSubmit: function(){
					return $(this).form('validate');
				},
				success: function(result){
				
				if (result.errorMsg){
					document.write(222);
					$.messager.show({
						title: 'Error',
						msg: result.errorMsg
					});
				} else {
					$('#dlg2').dialog('close');		// close the dialog
					$('#dg2').datagrid('reload');	// reload the user data
				}
			}
			});
		}
		function destroyOrder(){
			var row = $('#dg2').datagrid('getSelected');
			if (row){
				$.messager.confirm('Confirm','Are you sure you want to destroy this order?',function(r){
					
					if (r){		
						$.post("OrderPro!delete",{id:row.id},function(result){
							if (result.success){
								$('#dg2').datagrid('reload');	// reload the user data
							} else {
								$.messager.show({	// show error message
									title: 'Error',
									msg: result.errorMsg
								});
							}
						},'json');
					}
				});
			}
		}
	</script>

</body>
</html>