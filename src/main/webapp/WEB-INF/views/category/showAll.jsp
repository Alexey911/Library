<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1><spring:message code="categories.name"/></h1>
<table border="1">
    <tr>
        <th><spring:message code="category.field.name"/></th>
        <th><spring:message code="category.field.description"/></th>
    </tr>
    <%--@elvariable id="categories" type="java.util.Set"--%>
    <c:forEach items="${categories}" var="publisher">
        <tr class="field">
            <td><a href="${contextPath}/categories/${publisher.id}"><c:out
                    value="${publisher.name}"/></a></td>
            <td><c:out value="${publisher.description}"/></td>
        </tr>
    </c:forEach>
</table>
<sec:authorize access="hasAnyRole('ROLE_LIBRARIAN', 'ROLE_ADMIN')">
    <a href="${contextPath}/categories/add"><spring:message code="category.action.add"/></a>
</sec:authorize>