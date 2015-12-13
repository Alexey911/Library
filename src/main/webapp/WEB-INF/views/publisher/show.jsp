<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1><spring:message code="publisher.name"/></h1>
<%--@elvariable id="publisher" type="com.zhytnik.library.domain.Publisher"--%>
<table>
    <tr>
        <td><spring:message code="publisher.field.name"/></td>
        <td>${publisher.name}</td>
    </tr>
    <tr>
        <td><spring:message code="publisher.field.address"/></td>
        <td>${publisher.address}</td>
    </tr>
</table>
<sec:authorize access="hasAnyRole('ROLE_LIBRARIAN', 'ROLE_ADMIN')">
    <sf:form method="post" action="${contextPath}/publishers/${publisher.id}/delete">
        <input type="submit" value=<spring:message code="publisher.action.delete"/>>
    </sf:form>
    <a href="${contextPath}/publishers/${publisher.id}?page=edit"><spring:message code="publisher.action.edit"/></a>
    <br>
</sec:authorize>
<br>