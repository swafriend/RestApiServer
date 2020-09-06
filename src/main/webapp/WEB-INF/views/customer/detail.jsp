<%--
  Created by IntelliJ IDEA.
  User: HARUNA
  Date: 2020/05/30
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta content="text/html" http-equiv="Content-Type" ; charset="UTF-8">
    <title>顧客詳細画面</title>
</head>
<body>
<h1>顧客詳細画面</h1>
<dl>
    <dt>名前</dt>
    <dd><c:out value="${customer.name}"/></dd>
    <dt>Eメールアドレス</dt>
    <dd><c:out value="${customer.emailAddress}"/></dd>
    <dt>誕生日</dt>
    <fmt:parseDate value="${customer.birthday}" pattern="yyyy-MM-dd" var="parsedToday" type="date" />
    <dd><fmt:formatDate pattern="yyyy/MM/dd" value="${parsedToday}"/></dd>
    <dt>好きな数字</dt>
    <dd><c:out value="${customer.favoriteNumber}"/></dd>
</dl>
<c:url value="/customer" var="url"/>
<a href="${url}">一覧</a>
</body>
</html>
