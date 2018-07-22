<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理社团</title>
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
	<c:forEach var="association" items="${associationList}">
		<div class="associationDiv">
			<div class="pic">
				<a href="getAssociation.do?id=${association.id}" target="_blank"><img
					src="${association.headpic}" /></a>
			</div>
			<div class="content">
				<div class="title">
					<a href="getAssociation.do?id=${association.id}" target="_blank"><span
						class="newstitleStyle">${association.name}</span></a>
					<input type="submit" class="buttonstyle" onclick="location.href='manageAssociation.do?id=${association.id}'" value="管理社团"/>
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
	<div>
	</div>
</body>
</html>