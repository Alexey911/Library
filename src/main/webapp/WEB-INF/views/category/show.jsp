<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>View Category</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>Category</h1>
<%--@elvariable id="category" type="com.zhytnik.library.model.Category"--%>
<table>
    <tr>
        <td>Name</td>
        <td>${category.name}</td>
    </tr>
    <tr>
        <td>Age</td>
        <td>${category.description}</td>
    </tr>
</table>
<sf:form method="DELETE" modelAttribute="category" action="/categories/${category.id}">
    <input type="hidden" name="id" value="${category.id}">
    <input type="submit" value="Delete">
</sf:form>
<a href="/categories/${category.id}?action=edit">Edit</a>
<br>
<a href="${pageContext.request.contextPath}/categories">View categories</a>
</body>
</html>