<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="publisher.page.show.header"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1><spring:message code="publisher.name"/></h1>
<%--@elvariable id="publisher" type="com.zhytnik.library.domain.Publisher"--%>
<table>
    <tr>
        <td><spring:message code="publisher.field.name"/></td>
        <td>${publisher.name}</td>
    </tr>
    <tr>
        <td><spring:message code="publisher.field.address"/></td>
        <td>${publisher.address}</td>
    </tr>
</table>
<sf:form method="DELETE" modelAttribute="publisher" action="/publishers/${publisher.id}">
    <input type="hidden" name="id" value="${publisher.id}">
    <input type="submit" value="Delete">
</sf:form>
<a href="/publishers/${publisher.id}?action=edit"><spring:message code="publisher.action.edit"/></a>
<br>
<a href="${pageContext.request.contextPath}/publishers"><spring:message code="publishers.action.show"/></a>
</body>
</html>