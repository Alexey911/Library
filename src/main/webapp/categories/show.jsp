<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Categories</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/jQuery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/categories/script.js"></script>
</head>
<body>
<h1>Categories</h1>
<form method="post" id="editForm" action="edit">
    <input type="hidden" name="name" id="name" value="?">
    <input type="hidden" name="description" id="desc" value="?">
    <input type="hidden" name="id" id="editId" value="?">
</form>
<form method="post" id="deleteForm" action="delete">
    <input type="hidden" name="id" id="deleteId" value="?">
</form>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th colspan="2">Actions</th>
    </tr>
    <%--@elvariable id="categories" type="java.util.Set"--%>
    <c:forEach items="${categories}" var="category">
        <tr class="field">
            <td class="name"><c:out value="${category.name}"/></td>
            <td class="desc"><c:out value="${category.description}"/></td>
            <td hidden class="id"><c:out value="${category.id}"/></td>
            <td>
                <button type="submit" form="editForm" value="edit" name="submit" class="action">Edit</button>
            </td>
            <td>
                <button type="submit" form="deleteForm" value="delete" name="submit" class="action">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
<form method="post" action="add" id="addForm">
</form>
<button form="addForm" value="add" name="submit">Add new</button>
</body>
</html>