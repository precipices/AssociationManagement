<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${returnAssociation.name}</title>
<style type="text/css">
.centerDiv {
	margin: 0 auto;
	text-align: center;
}
.newstitleStyle{
	width:120px;
	display:block;
}
.headpic {
	/* 	width:900px; */
/* 	height: 300px; */
	margin: 0 auto;
	margin-top: 10px;
	position: relative;
	overflow: hidden;
}
/* 本例子css */
.picScroll-left {
	width: 450px;
	height: 180px;
	overflow: hidden;
	position: relative;
	border: 1px solid #ccc;
	margin: 0 auto;
	margin-top:20px;
}

.picScroll-left .hd {
	overflow: hidden;
	height: 30px;
	background: #f4f4f4;
	padding: 0 10px;
}

.picScroll-left .hd .prev, .picScroll-left .hd .next {
	display: block;
	width: 5px;
	height: 9px;
	float: right;
	margin-right: 5px;
	margin-top: 10px;
	overflow: hidden;
	cursor: pointer;
	background: url("images/arrow.png") no-repeat;
}

.picScroll-left .hd .next {
	background-position: 0 -50px;
}

.picScroll-left .hd .prevStop {
	background-position: -60px 0;
}

.picScroll-left .hd .nextStop {
	background-position: -60px -50px;
}

.picScroll-left .hd ul {
	float: right;
	overflow: hidden;
	zoom: 1;
	margin-top: 10px;
	zoom: 1;
}

.picScroll-left .hd ul li {
	float: left;
	width: 9px;
	height: 9px;
	overflow: hidden;
	margin-right: 5px;
	text-indent: -999px;
	cursor: pointer;
	background: url("images/icoCircle.gif") 0 -9px no-repeat;
}

.picScroll-left .hd ul li.on {
	background-position: 0 0;
}

.picScroll-left .bd {
	padding: 10px;
}

.picScroll-left .bd ul {
	overflow: hidden;
	zoom: 1;
}

.picScroll-left .bd ul li {
	margin: 0 8px;
	float: left;
	_display: inline;
	overflow: hidden;
	text-align: center;
}

.picScroll-left .bd ul li .pic {
	text-align: center;
}

.picScroll-left .bd ul li .pic img {
	width: 120px;
	height: 90px;
	display: block;
	padding: 2px;
	border: 1px solid #ccc;
}

.picScroll-left .bd ul li .pic a:hover img {
	border-color: #999;
}

.picScroll-left .bd ul li .title {
	line-height: 24px;
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
	<h1>${returnAssociation.name}</h1>
	
	<div class="picScroll-left">
			<div class="hd">
				<a class="next"></a>
				<ul></ul>
				<a class="prev"></a> <span class="pageState"></span>社团新闻
				<a href="showAllNews.do?id=${returnAssociation.id}" style="float:right">　　查看全部</a>
				<c:choose>
					<c:when test="${returnRelationship.position=='社长'}">
						<a href="addNews.do?id=${returnAssociation.id}" style="float:right">　　添加新闻</a>
					</c:when>
				</c:choose>
			</div>
			<div class="bd">
				<ul class="picList">
					<c:forEach var="news" items="${returnNewsList}">
						<li>
						<div class="pic">
							<a href="getNewsActivity.do?id=${news.id}" target="_blank"><img
								src="${news.headpic}" /></a>
						</div>
						<div class="title">
							<a href="getNewsActivity.do?id=${news.id}" target="_blank"><span class="newstitleStyle">${news.title}</span></a>
						</div>
						</li>
					</c:forEach>
					
				</ul>
			</div>
	</div>
	<div class="picScroll-left">
			<div class="hd">
				<a class="next"></a>
				<ul></ul>
				<a class="prev"></a> <span class="pageState"></span>社团活动
				<a href="showAllActivitys.do?id=${returnAssociation.id}" style="float:right">　　查看全部</a>
				<c:choose>
					<c:when test="${returnRelationship.position=='社长'}">
						<a href="addActivity.do?id=${returnAssociation.id}" style="float:right">　　添加活动</a>
					</c:when>
				</c:choose>
			</div>
			<div class="bd">
				<ul class="picList">
					<c:forEach var="news" items="${returnActivityList}">
						<li>
						<div class="pic">
							<a href="getNewsActivity.do?id=${news.id}" target="_blank"><img
								src="${news.headpic}" /></a>
						</div>
						<div class="title">
							<a href="getNewsActivity.do?id=${news.id}" target="_blank"><span class="newstitleStyle">${news.title}</span></a>
						</div>
						</li>
					</c:forEach>
				</ul>
			</div>
	</div>
	
	<p>
		社团成员：<br />
		<c:forEach var="relationship"
			items="${returnAssociation.relationships}">
		${relationship.username} &nbsp
		</c:forEach>
	</p>

	<c:choose>
		<c:when test="${returnRelationship.position=='社长'}">
			<p>
			<input type="button" class="buttonstyle" onclick="location.href='manageMember.do?id=${returnAssociation.id}'" value="成员审核"/>
			</p>
		</c:when>
	</c:choose>
	
	</div>
		<script type="text/javascript">
		jQuery(".picScroll-left").slide({
			titCell : ".hd ul",
			mainCell : ".bd ul",
			autoPage : true,
			effect : "left",
			autoPlay : true,
			vis : 3,
			trigger : "click"
		});
	</script>
</body>
</html>