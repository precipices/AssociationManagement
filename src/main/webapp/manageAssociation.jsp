<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加社团</title>
<style type="text/css">
.centerDiv {
	margin: 0 auto;
	text-align: center;
}

.newstitleStyle {
	width: 120px;
	display: block;
}

.headpic {
	/* 	width:900px; */
	/* 	height: 300px; */
	margin: 0 auto;
	margin-top: 10px;
	position: relative;
	overflow: hidden;
}

.buttonstyle {
	display: inline-block;
	height: 41px;
	border-radius: 4px;
	background: #2795dc;
	border: none;
	cursor: pointer;
	border-bottom: 3px solid #0078b3;
	*border-bottom: none;
	color: #fff;
	font-size: 16px;
	padding: 0 10px;
	*width: 140px;
	text-align: center;
	outline: none;
	font-family: "Microsoft Yahei", Arial, Helvetica, sans-serif;
	margin: 10px 10px;
}

.buttonstyle:hover {
	background: #0081c1;
	border-bottom: 3px solid #006698;
	color: #fff;
	text-decoration: none;
}

input.inputstyle {
	width: 200px;
	height: 38px;
	padding-left: 5px;
	line-height: 38px;
	border: 1px solid #D7D7D7;
	background: #fff;
	color: #333;
	border-radius: 2px;
	font-family: Verdana, Tahoma, Arial;
	font-size: 16px;
	ime-mode: disabled;
}

input.inputstyle:focus {
	border: 1px solid #198BD4;
	box-shadow: 0 0 2px #198BD4;
}

.formstyle {
	width: 800px;
	margin: 0 auto;
	margin-top: 50px;
	font-size: 16px;
	/* text-align: center; */
}
</style>
</head>
<body>
	<jsp:include page="head.jsp" />
	<div class="centerDiv">
		<c:choose>
		<c:when test="${requestScope.returnAssociation.headpic!= null}">
			<div class="headpic">
				<a href="#"><img src="${returnAssociation.headpic}" /></a>
			</div>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	<div class="centerDiv"></div>
	<h1>${returnAssociation.name}</h1>
	<p>类型：${returnAssociation.type}</p>
	<p>${returnAssociation.information}</p>
		<div class="formstyle">
			<form action="doChangeAssociation.do" method="get">
				名称：<br /> <input type="text" class="inputstyle" name="name" value="${returnAssociation.name}"/><br />
				类型：<br /> <input type="text" class="inputstyle" name="type" value="${returnAssociation.type}"/><br />
				简介：<br />
				<textarea rows="10" cols="50" name="information">${returnAssociation.information}</textarea><br /> 
				<input type="hidden" name="id" value="${returnAssociation.id}"/>
				<input type="submit" class="buttonstyle" value="提交"/><br /> 
			</form>
		</div>

	</div>

</body>
</html>