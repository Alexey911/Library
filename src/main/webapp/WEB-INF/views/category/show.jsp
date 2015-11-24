<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="category.page.show.header"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1><spring:message code="category.name"/></h1>
<%--@elvariable id="category" type="com.zhytnik.library.model.Category"--%>
<table>
    <tr>
        <td><spring:message code="category.field.name"/></td>
        <td>${category.name}</td>
    </tr>
    <tr>
        <td><spring:message code="category.field.description"/></td>
        <td>${category.description}</td>
    </tr>
</table>
<sf:form method="DELETE" modelAttribute="category" action="/categories/${category.id}">
    <input type="hidden" name="id" value="${category.id}">
    <input type="submit" value="Delete">
</sf:form>
<a href="/categories/${category.id}?action=edit"><spring:message code="category.action.edit"/></a>
<br>
<a href="${pageContext.request.contextPath}/categories"><spring:message code="categories.action.show"/></a>
</body>
</html>