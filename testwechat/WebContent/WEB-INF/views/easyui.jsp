<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="jquery,ui,easy,easyui,web">
<meta name="description"
	content="easyui help you build your web page easily!">
<title>jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"
	href="http://www.w3cschool.cc/try/jeasyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="http://www.w3cschool.cc/try/jeasyui/themes/icon.css">
<style type="text/css">
#ff label {
	display: block;
	width: 100px;
}
</style>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
<script type="text/javascript"
	src="http://www.w3cschool.cc/try/jeasyui/jquery.easyui.min.js"></script>
<script type="text/javascript">

$('#ff').form({
	url:'form3_proc.php',
	onSubmit:function(){
		return $(this).form('validate');
	},
	success:function(data){
		$.messager.alert('Info', data, 'info');
	}
});

$.extend($.fn.validatebox.defaults.rules, {
	username: {
        validator: function(value){
        	var haha = $.ajax({
				type : 'get',
				url : 'http://localhost:8080/testwechat/hello/check',
				data : {
					"username" : username
				}
			}).responseText;
            return haha != 'true';
        },
        message: '用户名被注册了'
    }
});
</script>
</head>
<body>
	<div style="width: 230px; background: #fafafa; padding: 10px;">
		<div style="padding: 3px 2px; border-bottom: 1px solid #ccc">Form
			Validation</div>
		<form id="ff" method="get" action="http://localhost:8080/testwechat/hello/world.html">
			<div>
				<label for="name">Name:</label> <input class="easyui-validatebox"
					type="text" name="username" required="true" id="username"
					data-options="validType:'username'"></input>
			</div>
			<div>
				<label for="email">Email:</label> <input class="easyui-validatebox"
					type="text" name="email" required="true"></input>
			</div>

			<div>
				<input type="submit" value="Submit">
			</div>
		</form>
	</div>
</body>
</html>