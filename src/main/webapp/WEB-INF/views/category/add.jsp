<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<springForm:form method="POST" modelAttribute="category" action="${pageContext.request.contextPath}/categories">
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
            <spring:message code="category.action.add" var="change"/>
            <td colspan="3"><input type="submit" value="${change}"></td>
        </tr>
    </table>
</springForm:form>