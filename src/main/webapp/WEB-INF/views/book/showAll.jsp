<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="books.page.show.header"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1><spring:message code="books.name"/></h1>
<%--@elvariable id="books" type="java.util.Set"--%>
<%--@elvariable id="book" type="com.zhytnik.library.domain.Book"--%>
<table border="1">
    <tr>
        <th><spring:message code="book.field.name"/></th>
        <td><spring:message code="book.field.authors"/></td>
        <td><spring:message code="book.field.page.count"/></td>
        <td><spring:message code="book.field.publisher"/></td>
    </tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><a href="/books/${book.id}">${book.name}</a></td>
            <td>${book.authors}</td>
            <td>${book.pageCount}</td>
            <td><a href="/publishers/${book.publisher.id}">${book.publisher.name}</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>