<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1><spring:message code="publishers.name"/></h1>
<table border="1">
    <tr>
        <th><spring:message code="publisher.field.name"/></th>
        <th><spring:message code="publisher.field.address"/></th>
    </tr>
    <%--@elvariable id="publishers" type="java.util.Set"--%>
    <c:forEach items="${publishers}" var="category">
        <tr class="field">
            <td><a href="${contextPath}/publishers/${category.id}"><c:out
                    value="${category.name}"/></a></td>
            <td><c:out value="${category.address}"/></td>
        </tr>
    </c:forEach>
</table>
<sec:authorize access="hasAnyRole('ROLE_LIBRARIAN', 'ROLE_ADMIN')">
    <a href="${contextPath}/publishers/add"><spring:message code="publisher.action.add"/></a>
</sec:authorize>