<%--
  Created by IntelliJ IDEA.
  User: HARUNA
  Date: 2020/05/30
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>確認画面</title>
</head>
<body>
<h1>確認画面</h1>
<form method="post">
    <dl>
        <dt>名前</dt>
        <dd><c:out value="${editCustomer.name}"/></dd>
        <dt>Eメールアドレス</dt>
        <dd><c:out value="${editCustomer.emailAddress}"/></dd>
        <dt>誕生日</dt>
        <%--        <dd><fmt:formatDate pattern="yyyy/MM/dd" value="${editCustomer.birthday}"/></dd>--%>
        <fmt:parseDate value="${editCustomer.birthday}" pattern="yyyy-MM-dd" var="parsedToday" type="date" />
        <dd><fmt:formatDate pattern="yyyy/MM/dd" value="${parsedToday}"/></dd>
        <dt>好きな数字</dt>
        <dd><c:out value="${editCustomer.favoriteNumber}"/></dd>
    </dl>
    <button type="submit" name="_event_confirmed">更新</button>
    <button type="submit" name="_event_revise">再入力</button>
</form>
</body>
</html>
