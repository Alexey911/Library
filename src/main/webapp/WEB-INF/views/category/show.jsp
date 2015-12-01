<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title><spring:message code="publisher.page.show.header"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1><spring:message code="publisher.name"/></h1>
<%--@elvariable id="category" type="com.zhytnik.library.domain.Category"--%>
<table>
    <tr>
        <td><spring:message code="publisher.field.name"/></td>
        <td>${category.name}</td>
    </tr>
    <tr>
        <td><spring:message code="publisher.field.address"/></td>
        <td>${category.description}</td>
    </tr>
</table>
<sec:authorize access="hasAnyRole('ROLE_LIBRARIAN', 'ROLE_ADMIN')">
    <sf:form method="DELETE" action="/publishers/${publisher.id}">
        <input type="submit" value="Delete">
    </sf:form>
    <a href="/publishers/${publisher.id}?action=edit"><spring:message code="publisher.action.edit"/></a>
    <br>
</sec:authorize>
<a href="${pageContext.request.contextPath}/publishers"><spring:message code="publishers.action.show"/></a>
</body>
</html>