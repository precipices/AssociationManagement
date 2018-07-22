<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<script type="text/javascript">
</script>
</head>
<body>
<jsp:include page="head.jsp"/>
<p><img src="${customer.headpic}"/></p>
<p>用户名：${customer.username}</p>
<p>真实姓名：${customer.realname }</p>
<p>班级：${customer.classname }</p>
<p>联系方式：${customer.contact }</p>
</body>
</html>