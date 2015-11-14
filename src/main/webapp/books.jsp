<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<p>Books</p>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Page count</th>
        <th>Authors</th>
        <th>Annotation</th>
        <th>Publisher</th>
        <th>Publishing year</th>
        <th>Weight</th>
        <th>Categories</th>
        <th>ID</th>
    </tr>
    <%--@elvariable id="books" type="java.util.Set"--%>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><c:out value="${book.name}"/></td>
            <td><c:out value="${book.pageCount}"/></td>
            <td><c:out value="${book.authors}"/></td>
            <td><c:out value="${book.annotation}"/></td>
            <td><c:out value="${book.publisher.name}"/></td>
            <td><c:out value="${book.publishingYear}"/></td>
            <td><c:out value="${book.weight}"/></td>
            <td><c:out value="${book.categories}"/></td>
            <td><c:out value="${book.id}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>