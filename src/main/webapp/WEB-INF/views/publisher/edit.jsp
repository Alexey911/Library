<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<sf:form method="POST" modelAttribute="publisher"
         action="${pageContext.request.contextPath}/publishers/update">
    <springForm:hidden path="id"/>
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
            <spring:message code="publisher.action.save" var="search"/>
            <td colspan="3"><input type="submit" value="${search}"></td>
        </tr>
    </table>
</sf:form>