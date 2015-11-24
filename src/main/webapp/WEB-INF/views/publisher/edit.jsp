<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="publisher.page.edit.header"/></title>
    <style type="text/css">
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
    </style>
</head>
<body>
<sf:form method="POST" modelAttribute="publisher" action="/publishers/update">
    <table>
        <tr>
            <td><spring:message code="publisher.field.name"/></td>
            <td><sf:input path="name"/></td>
            <td><sf:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="publisher.field.address"/></td>
            <td><sf:input path="address"/></td>
            <td><sf:errors path="address" cssClass="error"/></td>
        </tr>
        <tr>
            <spring:message code="publisher.action.change" var="change"/>
            <td colspan="3"><input type="submit" value="${change}"></td>
        </tr>
    </table>
    <input type="hidden" name="id" value="${publisher.id}">
</sf:form>
</body>
</html>