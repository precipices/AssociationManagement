<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>浏览社团</title>
<style type="text/css">
.activityDiv {
	margin: 0 auto;
	/* 	margin-top: 100px; */
	padding-top: 50px;
	text-aligh: center;
  	clear: both;  
}

.activityDiv img {
	width: 100%;
	height: 100%;
}
.pic {
	width: 120px;
	height: 100px;
	text-aligh: center;
	margin-left: 10px;
	overflow: hidden;
 	float: left; 
}

.content {
	width:800px; 
	height: 100px;
	/* 	text-aligh:center; */
	margin-right: 10px;
  	float: left;  
}

.title {
font:20px bold;
/* float: left; */
/* 	clear: both; */
}

.info {
	height: 100px;
	overflow: hidden;
/* 	float: left; */
/* 	clear: both; */
}

</style>
</head>
<body>
	<jsp:include page="head.jsp" />
	<c:forEach var="activity" items="${returnActivityList}">
		<div class="activityDiv">
			<div class="pic">
				<a href="getNewsActivity.do?id=${activity.id}" target="_blank"><img
					src="${activity.headpic}" /></a>
			</div>
			<div class="content">
				<div class="title">
					<a href="getNewsActivity.do?id=${activity.id}" target="_blank"><span
						class="activitytitleStyle">${activity.title}</span></a>
				</div>
				<div class="info">
					<c:choose>
					<c:when test="${fn:length(activity.content)>100}">
						${fn:substring(activity.content,0,100)}...
					</c:when>
					<c:otherwise>
						${activity.content}
					</c:otherwise>
				</c:choose></div>
			</div>

		</div>
	</c:forEach>
</body>
</html>