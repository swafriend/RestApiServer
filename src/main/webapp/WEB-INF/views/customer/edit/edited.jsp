<%--
  Created by IntelliJ IDEA.
  User: HARUNA
  Date: 2020/05/30
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>更新完了</title>
</head>
<body>
<h1>更新完了</h1>
<c:if test="${editedCustomer != null}">
<dl>
    <dt>名前</dt>
    <dd><c:out value="${editedCustomer.name}"/></dd>
    <dt>Eメールアドレス</dt>
    <dd><c:out value="${editedCustomer.emailAddress}"/></dd>
    <dt>誕生日</dt>
    <%--    <dd><fmt:formatDate pattern="yyyy/MM/dd" value="${editCustomer.birthday}"/></dd>--%>
    <fmt:parseDate value="${editedCustomer.birthday}" pattern="yyyy-MM-dd" var="parsedToday" type="date" />
    <dd><fmt:formatDate pattern="yyyy/MM/dd" value="${parsedToday}"/></dd>
    <dt>好きな数字</dt>
    <dd><c:out value="${editedCustomer.favoriteNumber}"/></dd>
</dl>
</c:if>
<c:url var="url" value="/customer"/>
<a href="${url}">戻る</a>
</body>
</html>