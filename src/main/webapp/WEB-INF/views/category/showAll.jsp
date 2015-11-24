<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="categories.page.show.header"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1><spring:message code="categories.name"/></h1>
<table border="1">
    <tr>
        <th><spring:message code="category.field.name"/></th>
        <th><spring:message code="category.field.description"/></th>
    </tr>
    <%--@elvariable id="categories" type="java.util.Set"--%>
    <c:forEach items="${categories}" var="publisher">
        <tr class="field">
            <td><a href="${pageContext.request.contextPath}/categories/${publisher.id}"><c:out
                    value="${publisher.name}"/></a></td>
            <td><c:out value="${publisher.description}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/categories/add"><spring:message code="category.action.add"/></a>
</body>
</html>