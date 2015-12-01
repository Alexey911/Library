<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
<springForm:form method="POST" modelAttribute="category" action="/categories">
    <table>
        <tr>
            <td><spring:message code="category.field.name"/></td>
            <td><springForm:input path="name"/></td>
            <td><springForm:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="category.field.description"/></td>
            <td><springForm:input path="description"/></td>
            <td><springForm:errors path="description" cssClass="error"/></td>
        </tr>
        <tr>
            <spring:message code="category.action.save" var="save"/>
            <td colspan="3"><input type="submit" value="${save}"></td>
        </tr>
    </table>
</springForm:form>
</body>
</html>