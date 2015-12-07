<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h1><spring:message code="category.name"/></h1>
<%--@elvariable id="category" type="com.zhytnik.library.domain.Category"--%>
<table>
    <tr>
        <td><spring:message code="category.field.name"/></td>
        <td>${category.name}</td>
    </tr>
    <tr>
        <td><spring:message code="category.field.description"/></td>
        <td>${category.description}</td>
    </tr>
</table>
<sec:authorize access="hasAnyRole('ROLE_LIBRARIAN', 'ROLE_ADMIN')">
    <sf:form method="post" action="/categories/${category.id}/delete">
        <input type="submit" value=<spring:message code="category.action.delete"/>>
    </sf:form>
    <a href="/categories/${category.id}?action=edit"><spring:message code="category.action.edit"/></a>
    <br>
</sec:authorize>