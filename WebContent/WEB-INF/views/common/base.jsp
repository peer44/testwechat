<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!-- 项目路径 -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<c:set var="base" value="<%=basePath%>" scope="application"></c:set>
<!-- validform -->
<link rel="stylesheet" href="${base }/assets/js/validform/css/style.css" type="text/css" />
<script type="text/javascript" src="${base }/assets/js/validform/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${base }/assets/js/validform/js/Validform_v5.3.2_ncr_min.js"></script>
<!-- validform -->
<!-- easyui -->
<link rel="stylesheet" type="text/css" href="${base }/assets/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${base }/assets/js/easyui/themes/icon.css">
<script type="text/javascript" src="${base }/assets/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${base }/assets/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base }/assets/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<!-- easyui -->
<!-- jqueryform -->
<script type="text/javascript" src="${base }/assets/js/easyui/src/jquery.form.js"></script>
<!-- jqueryform -->
<%
String uri = request.getRequestURI();//http://localhost:8080/project/index.jsp
String a[] = uri.split("/");
request.setAttribute("currentPage", a[a.length-1]);
%>
