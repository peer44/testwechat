<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Validate Form on Submit - jQuery EasyUI Demo</title>
<!-- 引入css,javascript资源 -->
<c:catch var="importError0">
	<c:import url="common/base.jsp" charEncoding="utf-8"></c:import>
</c:catch>
<c:out value="${importError0}"></c:out>
</head>
<body>
	<h2>Validate Form on Submit</h2>
	<p>The form does not perform validation before being submitted.</p>
	<div style="margin: 20px 0;"></div>
	<div class="easyui-panel" title="New Topic" style="width: 400px">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff" class="easyui-form" method="get" action="world.html">
				<table cellpadding="5">
					<tr>
						<td>Name:</td>
						<td><input id="username" class="easyui-textbox" type="text" name="username" data-options="required:true,validType:'myvalidate'"></input></td>
					</tr>
					
					<tr>
		    			<td>Email:</td>
		    			<td><input class="easyui-textbox" type="text" name="email" data-options="required:true,validType:'email'"></input></td>
	    			</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitForm()">Submit</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="clearForm()">Clear</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$.extend($.fn.validatebox.defaults.rules, {
			myvalidate : {
				validator : function(value, param) {
					var username = $("#username").val().trim();
					console.log(username);
					var haha = " ";
					$.ajax({
						type : 'post',
						async : false,
						url : 'http://localhost:8080/testwechat/hello/check',
						data : {
							"username" : username
						},
						success : function(data) {
							haha = data;
						}
					});
					console.log(haha);
					return haha.indexOf("true");
				},
				message : '该用户名已经被占用'
			}
		});

		function submitForm() {
			$('#ff').form('submit', {
				//url : 'world.html',
				onSubmit : function() {
					return $(this).form('enableValidation').form('validate');
				},
				success:function(data){      
			     if (data.success == 'false') {   
			         $.messager.alert('提示',data.msg,'info');   
			     }else{   
			    	location.href = 'world.html';   
			    }     
			  }  
			});
		}
		function clearForm() {
			$('#ff').form('clear');
		}
	</script>
</body>
</html>