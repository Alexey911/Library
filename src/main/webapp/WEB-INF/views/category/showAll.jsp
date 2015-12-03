<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h1><spring:message code="categories.name"/></h1>
<table border="1">
    <tr>
        <th><spring:message code="category.field.name"/></th>
        <th><spring:message code="category.field.description"/></th>
    </tr>
    <%--@elvariable id="categories" type="java.util.Set"--%>
    <c:forEach items="${categories}" var="category">
        <tr class="field">
            <td><a href="${pageContext.request.contextPath}/categories/${category.id}"><c:out
                    value="${category.name}"/></a></td>
            <td><c:out value="${category.description}"/></td>
        </tr>
    </c:forEach>
</table>