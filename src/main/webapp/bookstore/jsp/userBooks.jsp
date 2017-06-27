<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="utf-8"%> 
<html>  
<head>
<meta charset="UTF-8">
	<title>Book Administration System</title>  
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
</head>
<body>
	<h1 style="color:red">Book Datagrid</h1>
	<p><br>Click the buttons on datagrid toolbar to do crud actions.<br><br>
	Operate on the book data</p>

<a id="btn" href="userOrders.jsp" class="easyui-linkbutton" data-options="iconCls:'icon-search'">View My orders</a>
<a id="btn" href="userBooks.jsp" class="easyui-linkbutton" data-options="iconCls:'icon-search'">View the books</a>
<a id="btn" href="OrderitemPro!UserInit" class="easyui-linkbutton" data-options="iconCls:'icon-search'">View the orderitems</a>
<a id="btn" href="CartPro!UserInit" class="easyui-linkbutton" data-options="iconCls:'icon-search'">View the shopping carts</a>



	<table id="dg2" title="My Books" class="easyui-datagrid" style="width:700px;height:250px"
			toolbar="#toolbar2" pagination="true" 
			url="BookPro!get"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="bookName" width="50">Book Name</th>
				<th field="bookNumber" width="50">Remained Amount</th>
				<th field="catagory" width="50">catagory</th>
				<th field="price" width="50">price</th>
				<th field="author" width="50">author</th>
			</tr>
			     
		</thead>
	</table>
		<div id="toolbar2">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editOrder()">Add to your shopping cart</a>
	</div>
	<div id="dlg2" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons2">
		<div class="ftitle2">Purchase Information</div>
		<form id="fm2" method="post" action="BookPro!purchase" novalidate>
			<div class="fitem2">
				<label>purchase amount:</label>
				<input name="bookNumber" id="i2" class="easyui-textbox" required="true">
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
			$('#dlg2').dialog('open').dialog('setTitle','New Book');
			$('#fm2').form('clear');
			url="newBook"
		}
		function editOrder(){
			var row = $('#dg2').datagrid('getSelected');
			if (row){
				$('#dlg2').dialog('open').dialog('setTitle','Purchase Book');
				$('#fm2').form('load',row);
				url = "BookPro!purchase?id="+row.id;
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
						$.post("destroyBook",{id:row.id},function(result){
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