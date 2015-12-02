<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
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
            <sf:form method="DELETE" action="/categories/${category.id}">
                <input type="submit" value="Delete">
            </sf:form>
            <a href="/categories/${category.id}?action=edit"><spring:message code="category.action.edit"/></a>
            <br>
        </sec:authorize>
    </tiles:putAttribute>
</tiles:insertDefinition>