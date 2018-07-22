<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看新闻</title>
<!-- <link href="css/index.css" type="text/css" rel="stylesheet" /> -->
<style type="text/css">
.centerDiv {
	margin: 0 auto;
	text-align: center;
}

.headpic {
	/* 	width:900px; */
	height: 300px;
	margin: 0 auto;
	margin-top: 10px;
	position: relative;
	overflow: hidden;
}
</style>
</head>
<body>
	<jsp:include page="head.jsp" />
	<c:choose>
		<c:when test="${requestScope.returnNews.headpic!= null}">
			<div class="headpic">
				<a href="#"><img src="${returnNews.headpic}" /></a>
			</div>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	<%-- 	${returnNews.headpic} --%>
	<div class="centerDiv"></div>
	<h1>${returnNews.title}</h1>
	<p>时间：${returnNews.releaseDate}</p>
	<p>${returnNews.content}</p>
</body>
</html>