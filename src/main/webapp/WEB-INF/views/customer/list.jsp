<%--
  Created by IntelliJ IDEA.
  User: HARUNA
  Date: 2020/05/30
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta content="text/html" http-equiv="Content-Type" ; charset="UTF-8">
    <title>顧客一覧画面</title>
</head>
<body>
<h1>顧客一覧画面</h1>
<c:if test="${editedCustomer != null}">
    以下の顧客が更新されました。
    <dl>
        <dt>名前</dt>
        <dd><c:out value="${editedCustomer.name}"/></dd>
        <dt>Eメールアドレス</dt>
        <dd><c:out value="${editedCustomer.emailAddress}"/></dd>
        <dt>誕生日</dt>
        <fmt:parseDate value="${editedCustomer.birthday}" pattern="yyyy-MM-dd" var="parsedToday" type="date" />
        <dd><fmt:formatDate pattern="yyyy/MM/dd" value="${parsedToday}"/></dd>
        <dt>好きな数字</dt>
        <dd><c:out value="${editedCustomer.favoriteNumber}"/></dd>
    </dl>
</c:if>
<table border="1">
    <tr>
        <th>ID</th>
        <th>名前</th>
        <th>Eメールアドレス</th>
        <th></th>
    </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td><c:out value="${customer.id}"></c:out></td>
            <td><c:out value="${customer.name}"></c:out></td>
            <td><c:out value="${customer.emailAddress}"></c:out></td>
            <td>
                <c:url value="/customer/${customer.id}" var="url"/>
                <a href="${url}">詳細</a>
                <c:url value="/customer/${customer.id}/edit" var="url"/>
                <a href="${url}">編集</a>
                <c:url value="/api/customer/${customer.id}" var="url"/>
                <a href="${url}">REST</a>
                <c:url value="/api/cus/${customer.id}" var="url"/>
                <a href="${url}">ERR</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
