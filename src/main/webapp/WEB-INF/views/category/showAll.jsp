<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="categories.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1><spring:message code="categories.title"/></h1>
<table border="1">
    <tr>
        <th><spring:message code="category.name"/></th>
        <th><spring:message code="category.description"/></th>
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
<a href="${pageContext.request.contextPath}/categories/add"><spring:message code="add"/></a>
</body>
</html>