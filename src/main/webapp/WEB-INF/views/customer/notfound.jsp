<%--
  Created by IntelliJ IDEA.
  User: HARUNA
  Date: 2020/05/30
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta content="text/html" http-equiv="Content-Type"; charset="UTF-8">
    <title>指定された顧客は見つかりません</title>
</head>
<body>
<h1>指定された顧客は見つかりません</h1>
<c:url value="/customer" var="url"/>
<a href="${url}"/>
</body>
</html>
