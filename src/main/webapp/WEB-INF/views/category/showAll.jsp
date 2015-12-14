<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1 class="h1 text-center"><spring:message code="categories.name"/></h1>
<table class="table table-striped">
    <thead>
    <tr>
        <th><spring:message code="category.field.name"/></th>
        <th><spring:message code="category.field.description"/></th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="categories" type="java.util.List"--%>
    <c:forEach items="${categories}" var="publisher">
        <tr class="field">
            <td><a href="${contextPath}/categories/${publisher.id}"><c:out
                    value="${publisher.name}"/></a></td>
            <td><c:out value="${publisher.description}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>