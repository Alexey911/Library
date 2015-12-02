<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <sf:form method="POST" modelAttribute="category" action="/categories/update">
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
                <tr>
                    <spring:message code="category.action.change" var="change"/>
                    <td colspan="3"><input type="submit" value="${change}"></td>
                </tr>
            </table>
            <input type="hidden" name="id" value="${category.id}">
        </sf:form>
    </tiles:putAttribute>
</tiles:insertDefinition>