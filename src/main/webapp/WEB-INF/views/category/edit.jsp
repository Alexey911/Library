<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<sf:form method="POST" modelAttribute="category" action="${pageContext.request.contextPath}/categories/update">
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
        <springForm:hidden path="id"/>
        <tr>
            <spring:message code="category.action.save" var="search"/>
            <td colspan="3"><input type="submit" value="${search}"></td>
        </tr>
    </table>
</sf:form>