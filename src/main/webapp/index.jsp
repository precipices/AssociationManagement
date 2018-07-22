<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>校园社团管理系统</title>
<link href="css/index.css" type="text/css" rel="stylesheet" />
<style type="text/css">

/* 本例子css */
.picScroll-left {
	width: 450px;
	height: 180px;
	overflow: hidden;
	position: relative;
	border: 1px solid #ccc;
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
/* 本例子css */
.txtScroll-top {
	width: 450px;
	height: 180px;
	overflow: hidden;
	position: relative;
	border: 1px solid #ccc;
}

.txtScroll-top .hd {
	overflow: hidden;
	height: 30px;
	background: #f4f4f4;
	padding: 0 10px;
}

.txtScroll-top .hd .prev, .txtScroll-top .hd .next {
	display: block;
	width: 9px;
	height: 5px;
	float: right;
	margin-right: 5px;
	margin-top: 10px;
	overflow: hidden;
	cursor: pointer;
	background: url("images/icoUp.gif") no-repeat;
}

.txtScroll-top .hd .next {
	background: url("images/icoDown.gif") no-repeat;
}

.txtScroll-top .hd ul {
	float: right;
	overflow: hidden;
	zoom: 1;
	margin-top: 10px;
}

.txtScroll-top .hd ul li {
	float: left;
	width: 9px;
	height: 9px;
	overflow: hidden;
	margin-right: 5px;
	text-indent: -999px;
	cursor: pointer;
	background: url("images/icoCircle.gif") 0 -9px no-repeat;
}

.txtScroll-top .hd ul li.on {
	background-position: 0 0;
}

.txtScroll-top .bd {
	padding: 15px;
}

.txtScroll-top .infoList li {
	height: 24px;
	line-height: 24px;
}

.txtScroll-top .infoList li .date {
	float: right;
	color: #999;
}
.newstitleStyle{
	width:120px;
	display:block;
}
</style>
</head>
<body>
	<jsp:include page="head.jsp" />
	<div class="headpic">
		<a href="index.jsp"><img src="images/head1.jpg" /></a>
	</div>
	<div class="news">
		<div class="picScroll-left divstyle">
			<div class="hd">
				<a class="next"></a>
				<ul></ul>
				<a class="prev"></a> <span class="pageState"></span>主站新闻
				<a href="showAllNews.do?id=0" style="float:right">查看全部</a>
			</div>
			<div class="bd">
				<ul class="picList">
					<c:forEach var="news" items="${newsList}">
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


		<div class="txtScroll-top divstyle">
			<div class="hd">
				<a class="next"></a>
				<ul></ul>
				<a class="prev"></a> <span class="pageState"></span> 主站公告
				<a href="showAllActivitys.do?id=0" style="float:right">查看全部</a>
			</div>
			<div class="bd">
				<ul class="infoList">
					<li><span class="date">2011-11-11</span><a
						href="#">公告1</a></li>
					<li><span class="date">2011-11-11</span><a
						href="#">公告2</a></li>
					<li><span class="date">2011-11-11</span><a
						href="#">公告3</a></li>
					<li><span class="date">2011-11-11</span><a
						href="#">公告4</a></li>
					<li><span class="date">2011-11-11</span><a
						href="#">公告5</a></li>
					<li><span class="date">2011-11-11</span><a
						href="#">公告6</a></li>
					<li><span class="date">2011-11-11</span><a
						href="#">公告7</a></li>
				</ul>
			</div>
		</div>
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
		jQuery(".txtScroll-top").slide({
			titCell : ".hd ul",
			mainCell : ".bd ul",
			autoPage : true,
			effect : "top",
			autoPlay : true,
			vis : 5
		});
	</script>
</body>
</html>