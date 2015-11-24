<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="category.page.edit.header"/></title>
    <style type="text/css">
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
    </style>
</head>
<body>
<sf:form method="POST" modelAttribute="category" action="/category/update">
    <table>
        <tr>
            <td><spring:message code="category.field.name"/></td>
            <td><sf:input path="name"/></td>
            <td><sf:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="category.field.description"/></td>
            <td><sf:input path="description"/></td>
            <td><sf:errors path="description" cssClass="error"/></td>
        </tr>
        <tr>
            <spring:message code="category.action.change" var="change"/>
            <td colspan="3"><input type="submit" value="${change}"></td>
        </tr>
    </table>
    <input type="hidden" name="id" value="${category.id}">
</sf:form>
</body>
</html>