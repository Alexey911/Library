<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sf:form method="POST" modelAttribute="wrapper" action="${pageContext.request.contextPath}/users/updatePassword">
    <springForm:hidden path="ownerId"/>
    <table>
        <tr>
            <td><spring:message code="user.last.password"/></td>
            <td><sf:password path="lastPassword"/></td>
            <td><sf:errors path="lastPassword" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="user.new.password"/></td>
            <td><sf:password path="newPassword"/></td>
            <td><sf:errors path="newPassword" cssClass="error"/></td>
        </tr>
        <tr>
            <spring:message code="action.change" var="search"/>
            <td colspan="3"><input type="submit" value="${search}"></td>
        </tr>
    </table>
</sf:form>