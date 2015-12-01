<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="users.page.show.header"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1><spring:message code="users.name"/></h1>
<table border="1">
    <tr>
        <th><spring:message code="user.field.login"/></th>
        <th><spring:message code="user.field.role"/></th>
        <th><spring:message code="user.field.enabled"/></th>
    </tr>
    <%--@elvariable id="users" type="java.util.Set"--%>
    <%--@elvariable id="user" type="com.zhytnik.library.domain.User"--%>
    <c:forEach items="${users}" var="user">
        <tr class="field">
            <td><a href="${pageContext.request.contextPath}/users/${user.id}"><c:out
                    value="${user.login}"/></a></td>
            <td><c:out value="${user.role}"/></td>
            <td><c:out value="${user.isEnable()}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>