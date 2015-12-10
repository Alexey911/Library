<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<springForm:form method="POST" modelAttribute="publisher"
                 action="${pageContext.request.contextPath}/publishers">
    <table>
        <tr>
            <td><spring:message code="publisher.field.name"/></td>
            <td><springForm:input path="name"/></td>
            <td><springForm:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="publisher.field.address"/></td>
            <td><springForm:input path="address"/></td>
            <td><springForm:errors path="address" cssClass="error"/></td>
        </tr>
        <tr>
            <spring:message code="publisher.action.add" var="search"/>
            <td colspan="3"><input type="submit" value="${search}"></td>
        </tr>
    </table>
</springForm:form>