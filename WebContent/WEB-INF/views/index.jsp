<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 引入css,javascript资源 -->
<c:catch var="importError0">
	<c:import url="common/base.jsp" charEncoding="utf-8"></c:import>
</c:catch>
<c:out value="${importError0}"></c:out>
</head>
<body>
	<form class="registerform" method="post" action="demo/ajax_post.php">
		<table width="100%" style="table-layout: fixed;">
			<tr>
				<td class="need" style="width: 10px;">*</td>
				<td style="width: 70px;">昵称：</td>
				<td style="width: 205px;"><input type="text" value=""
					name="name" class="inputxt" datatype="s6-18"
					errormsg="昵称至少6个字符,最多18个字符！" /></td>
				<td><div class="Validform_checktip"></div></td>
			</tr>
			<tr>
				<td class="need">*</td>
				<td>移动电话：</td>
				<td><input type="text" value="" name="tel" class="inputxt"
					datatype="m" errormsg="请输入您的手机号码！" /></td>
				<td><div class="Validform_checktip"></div></td>
			</tr>
			<tr>
				<td class="need">*</td>
				<td>所在城市：</td>
				<td><select name="province" datatype="*" nullmsg="请选择所在城市！"
					errormsg="请选择所在城市！"><option value="">请选择城市</option>
						<option value="1">瑞金市</option></select></td>
				<td><div class="Validform_checktip"></div></td>
			</tr>
			<tr>
				<td class="need">*</td>
				<td>性别：</td>
				<td><input type="radio" value="1" name="gender" id="male"
					class="pr1" datatype="*" errormsg="请选择性别！" /><label for="male">男</label>
					<input type="radio" value="2" name="gender" id="female" class="pr1" /><label
					for="female">女</label></td>
				<td><div class="Validform_checktip"></div></td>
			</tr>
			<tr>
				<td class="need">*</td>
				<td>购物网：</td>
				<td><input name="shoppingsite2" class="rt2" id="shoppingsite21"
					type="checkbox" value="1" datatype="*" errormsg="请选择您常去的购物网站！" /><label
					for="shoppingsite21">新蛋</label> <input name="shoppingsite2"
					class="rt2" id="shoppingsite22" type="checkbox" value="2" /><label
					for="shoppingsite22">淘宝</label> <input name="shoppingsite2"
					class="rt2" id="shoppingsite23" type="checkbox" value="3" /><label
					for="shoppingsite23">京东</label></td>
				<td><div class="Validform_checktip"></div></td>
			</tr>
			<tr>
				<td class="need"></td>
				<td>备注：</td>
				<td colspan="2"><textarea tip="请在这里输入您的意见。" altercss="gray"
						class="gray" name="msg" value="">请在这里输入您的意见。</textarea></td>
			</tr>
			<tr>
				<td class="need"></td>
				<td></td>
				<td colspan="2" style="padding: 10px 0 18px 0;"><input
					type="submit" value="提 交" /> <input type="reset" value="重 置" /></td>
			</tr>
		</table>
	</form>

</body>
<script type="text/javascript">
	$(function() {
		//$(".registerform").Validform();  //就这一行代码！;
		$(".registerform").Validform({
			tiptype:4,
			ajaxPost:true,
			callback:function(data){
				if(data.status=="y"){
					setTimeout(function(){
						$.Hidemsg(); //公用方法关闭信息提示框;显示方法是$.Showmsg("message goes here.");
					},2000);
				}
			}
		});
	})
</script>
</html>