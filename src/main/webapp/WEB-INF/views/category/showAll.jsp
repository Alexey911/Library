<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Categories</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<c:url value="/resources/js/jQuery.js"/>"></script>
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
            <td class="name"><c:out value="${category.name}"/></td>
            <td class="desc"><c:out value="${category.description}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>