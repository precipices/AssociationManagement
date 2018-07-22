<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${returnAssociation.name}-查看活动</title>
<style type="text/css">
.centerDiv {
	margin: 0 auto;
	text-align: center;
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
</style>
</head>
<body>
	<jsp:include page="head.jsp" />
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
	<p>
		社团成员：<br />
		<c:forEach var="relationship"
			items="${returnAssociation.relationships}">
		${relationship.username} &nbsp
		</c:forEach>
	</p>
	<p>
	<input type="button" class="buttonstyle" onclick="location.href='joinus.do?id=${returnAssociation.id}'" value="加入我们"/>
	</p>
</body>
</html>