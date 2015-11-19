<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>View Category</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>Category</h2>
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
<a href="${pageContext.request.contextPath}/categories">View categories</a>
</body>
</html>