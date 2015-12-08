<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<sf:form method="POST" modelAttribute="user" action="${contextPath}/users/update">
    <springForm:hidden path="id"/>
    <input type="hidden" name="lastRole" value="${user.role}">
    <table>
        <tr>
            <td><spring:message code="user.field.login"/></td>
            <td><sf:input path="login"/></td>
            <td><sf:errors path="login" cssClass="error"/></td>
        </tr>
        <sec:authorize access="hasRole('ROLE_USER')">
            <tr>
                <td><spring:message code="user.status.librarian"/></td>
                <td><label><input type="checkbox" name="librarian"></label></td>
            </tr>
        </sec:authorize>
        <tr>
            <spring:message code="action.save" var="change"/>
            <td colspan="3"><input type="submit" value="${change}"></td>
        </tr>
    </table>
</sf:form>