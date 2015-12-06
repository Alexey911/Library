<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<sf:form method="POST" modelAttribute="user" action="/users/update">
    <table>
        <tr>
            <td><spring:message code="user.field.login"/></td>
            <td><sf:input path="login"/></td>
            <td><sf:errors path="login" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="user.field.password"/></td>
            <td><sf:input path="password"/></td>
            <td><sf:errors path="password" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="user.status.librarian"/></td>
            <td><label><input type="checkbox"></label></td>
        </tr>
        <springForm:hidden path="id"/>
        <tr>
            <spring:message code="action.save" var="save"/>
            <td colspan="3"><input type="submit" value="${save}"></td>
        </tr>
    </table>
</sf:form>