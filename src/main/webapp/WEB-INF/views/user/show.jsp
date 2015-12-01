<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title><spring:message code="user.page.show.header"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1><spring:message code="user.name"/></h1>
<%--@elvariable id="user" type="com.zhytnik.library.domain.User"--%>
<table>
    <tr>
        <td><spring:message code="user.field.login"/></td>
        <td>${user.login}</td>
    </tr>
    <tr>
        <td><spring:message code="user.field.role"/></td>
        <td>${user.role}</td>
    </tr>
</table>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <sf:form method="DELETE" action="/users/${user.id}">
        <input type="submit" value="Delete">
    </sf:form>
    <sf:form method="POST" action="/users/${user.id}?action=disable">
        <input type="submit" value="Disable">
    </sf:form>
    <sf:form method="POST" action="/users/${user.id}?action=activate">
        <input type="submit" value="Activate">
    </sf:form>
</sec:authorize>
</body>
</html>