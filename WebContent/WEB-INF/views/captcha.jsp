<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证码</title>
<c:catch var="importError0">
	<c:import url="common/base.jsp" charEncoding="utf-8"></c:import>
</c:catch>
<c:out value="${importError0}"></c:out>
</head>
<body>
	<img id="imgObj" alt="验证码"
		src="${base }/captcha/getCaptchaImage.jpg"
		onclick="changeImg()">
	<form action="" id="loginform">
		请输入验证码:<input id="imageContent" name="imageContent" type="text"><br>
		<input id="timestamp" name="timestamp" value="${timestamp }" type="hidden" >
		<button>提交</button>
	</form>
</body>
<script type="text/javascript">
	var base = "${base}";
	function checkImageCode(code) {
		var timestamp = $("#timestamp").val().trim();
		console.log(code + " " + timestamp);
		var haha = "";
		 
		$.ajax({
			type : 'post',
			async : false,
			url : base+'/captcha/checkCaptcha',
			data : {
				"code" : code
			},
			success : function(data) {
				haha = data;
			}
		});
		console.log(haha);
		return haha;
	}
	$("form").submit(function check(){
		var code = $("#imageContent").val().trim();
		if(code.length != 0 ){
			var status = checkImageCode(code).trim();
			//alert(status);
			if(status.indexOf("true")>=0){
				alert("成功");
			}else{
				changeImg();
				alert("失败");
			}
		}else{
			alert("请输入验证码");
		}
		
		return false;
		});
	function changeImg() {
		var imgSrc = $("#imgObj");
		var src = imgSrc.attr("src");
		imgSrc.attr("src", chgUrl(src));
	};
	//时间戳   
	//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
	function chgUrl(url) {
		var timestamp = (new Date()).valueOf();
		var stamp = $("#timestamp");
		/* alert(url);
		url = url.substring(0, 60);
		if ((url.indexOf("&") >= 0)) {
			url = url + "×tamp=" + timestamp;
		} else {
			url = url + "?timestamp=" + timestamp;
			stamp.val(timestamp);
		} */
		return url;
	};
</script>
</html>