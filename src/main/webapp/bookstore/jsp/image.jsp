<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="utf-8"%> 
<html>  
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Book"%>



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
	
	<a href="BookPro" class="easyui-linkbutton"  plain="false" >Administrate book</a>
	<a href="UserPro" class="easyui-linkbutton"  plain="false" >Administrate user</a>
	<a href="OrderPro" class="easyui-linkbutton"  plain="false" >Administrate order</a>
	<a href="OrderitemPro" class="easyui-linkbutton"  plain="false" >Administrate orderitem</a>
	<a href="CartPro" class="easyui-linkbutton"  plain="false" >Administrate cart</a>
	
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
				<th	field="imageFullUrl" data-options="formatter:formatImg" width="50">image</th>
				<th field="author" width="50">author</th>
			</tr>
			     
		</thead>
	</table>
	<div id="toolbar2">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newOrder()">New Book</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editOrder()">Edit Book</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyOrder()">Remove Book</a>
	</div>
	<div id="dlg2" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons2" >
		<div class="ftitle2">Order Information</div>
		<form id="fm2" method="post" action="test" novalidate>
			<div class="fitem2">
				<label>Book Name:</label>
				<input name="bookName" id="i1" class="easyui-textbox" required="true">
			</div>
			<div class="fitem2">
				<label>Remained Number:</label>
				<input name="bookNumber" id="i2" class="easyui-textbox" required="true">
			</div>
			<div class="fitem2">
				<label>catagory:</label>
				<input name="catagory" id="i3" class="easyui-textbox" required = "true">
			</div>
			<div class="fitem2">
				<label>price:</label>
				<input name="price" id="i4" class="easyui-textbox" required = "true">
			</div>
			<div class="fitem2">
				<label>Author:</label>
				<input name="author" id="i5" class="easyui-textbox" required="true">
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

<div id="info1"><h2>The detailed information is provided here:</h2></div>
<div id="info">No book selected</div>
</br>

<%
		ArrayList<Book> bookList = new ArrayList<Book>();
			if (request.getAttribute("books") != null) {
		bookList = (ArrayList<Book>) request.getAttribute("books");
			}
	%>

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
	
	function formatImg(val,row){
		return '<img src=default.jpg styly width="100">';
	}
	
	
		var url;
		function newOrder(){
			$('#dlg2').dialog('open').dialog('setTitle','New Book');
			$('#fm2').form('clear');
			url="BookPro!add"
		}
		function editOrder(){
			var row = $('#dg2').datagrid('getSelected');
			if (row){
				$('#dlg2').dialog('open').dialog('setTitle','Edit Book');
				$('#fm2').form('load',row);
				url = "BookPro!update?id="+row.id;
			}
		}
		function saveOrder(){
			//document.write($('#fm2').attr('i4'));
			
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
						$.post("BookPro!delete",{id:row.id},function(result){
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
		

        $("#dg2").datagrid({  
            onClickRow: function () {  //easyui封装好的时间（被单机行的索引，被单击行的值）
            	var row = $('#dg2').datagrid('getSelected');
            	//document.write(row.id);
            	/*
            	$.post("BookInfoPro!showInfo",{id:row.id},function(result){
            		doucment.write(2);
            		document.getElementById("info").innerHTML=result.author;
				},'json');*/
            	$.ajax({  
                    type:"POST",  
                    url:"BookInfoPro!showInfo",  
                    async: false,
                    data:{id:row.id},  
                    success:function(msg){  
                    	//document.getElementById("info").innerHTML=author;
                    	msg=eval('('+msg+')');
                    	document.getElementById("info").innerHTML=msg.author;
                    }  
                });  
            }
        });
		
			
	</script>

</body>
</html>