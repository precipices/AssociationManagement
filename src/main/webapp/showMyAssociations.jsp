<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的社团</title>
<style type="text/css">
.associationDiv {
	margin: 0 auto;
	/* 	margin-top: 100px; */
	padding-top: 50px;
	text-aligh: center;
  	clear: both;  
}

.associationDiv img {
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
	<c:forEach var="association" items="${myAssociationList}">
		<div class="associationDiv">
			<div class="pic">
				<a href="getMyAssociation.do?id=${association.id}" target="_blank"><img
					src="${association.headpic}" /></a>
			</div>
			<div class="content">
				<div class="title">
					<a href="getMyAssociation.do?id=${association.id}" target="_blank"><span
						class="newstitleStyle">${association.name}</span></a>
				</div>
				<div class="info">
					<c:choose>
					<c:when test="${fn:length(association.information)>100}">
						${fn:substring(association.information,0,100)}...
					</c:when>
					<c:otherwise>
						${association.information}
					</c:otherwise>
				</c:choose></div>
			</div>
		</div>
	</c:forEach>
</body>
</html>