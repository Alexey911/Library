<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Categories</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>Categories</h1>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Description</th>
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
<a href="${pageContext.request.contextPath}/categories/add">Add new category</a>
</body>
</html>