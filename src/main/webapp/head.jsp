<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导航栏</title>
<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
<link href="css/head.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/head.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<ul id="nav" class="nav clearfix">
		<li class="nLi" id="index">
			<h3>
				<a href="index.jsp">首页</a>
			</h3>
		</li>
		<li class="nLi" id="personalinfo">
			<h3>
				<a href="personalinfo.jsp">个人信息</a>
			</h3>
		</li>
		<li class="nLi">
			<!-- 假设当前频道为“预告片”，手动或后台程序添加titOnClassName类名（默认是'on'），相当于设置参数defaultIndex:1。若同时设置参数returnDefault:true，则鼠标移走后0.3秒返回当前频道 -->
			<h3>
				<a href="showAllAssociations.do">浏览社团</a>
			</h3>
		</li>
		<li class="nLi">
			<h3>
				<a href="showMyAssociations.do">我的社团</a>
			</h3>
		</li>
		<c:choose>
			<c:when test="${customer.usertype=='管理员'}">
				<li class="nLi">
				<h3>
					<a href="manageAssociations.do">管理社团</a>
				</h3>
				<ul class="sub">
					<li><a href="addAssociation.jsp">添加社团</a></li>
					<li><a href="manageAssociations.do">管理社团</a></li>
				</ul>
				</li>
			</c:when>
		</c:choose>
		
		<li class="nLi">
			<h3>
				<a href="exit.do">退出</a>
			</h3>
			<ul class="sub">
				<li><a href="login.html">重新登陆</a></li>
				<li><a href="exit.do">安全退出</a></li>
			</ul>
		</li>
	</ul>
</body>
</html>