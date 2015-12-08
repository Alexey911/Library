<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1><spring:message code="book.name"/></h1>
<%--@elvariable id="book" type="com.zhytnik.library.domain.Book"--%>
<table>
    <tr>
        <td><spring:message code="book.field.name"/></td>
        <td>${book.name}</td>
    </tr>
    <tr>
        <td><spring:message code="book.field.authors"/></td>
        <td>${book.authors}</td>
    </tr>
    <tr>
        <td><spring:message code="book.field.page.count"/></td>
        <td>${book.pageCount}</td>
    </tr>
    <tr>
        <td><spring:message code="book.field.publisher"/></td>
        <td><a href="/publishers/${book.publisher.id}">${book.publisher.name}</a></td>
    </tr>
    <tr>
        <td><spring:message code="book.field.annotation"/></td>
        <c:choose>
            <c:when test="${not empty book.annotation}">
                <td>${book.annotation}</td>
            </c:when>
            <c:otherwise>
                <td></td>
            </c:otherwise>
        </c:choose>
    </tr>
    <tr>
        <td><spring:message code="book.field.year"/></td>
        <c:choose>
            <c:when test="${not empty book.publishingYear}">
                <td>${book.publishingYear}</td>
            </c:when>
            <c:otherwise>
                <td></td>
            </c:otherwise>
        </c:choose>
    </tr>
    <tr>
        <td><spring:message code="book.field.categories"/></td>
        <c:choose>
            <c:when test="${not empty book.categories}">
                <td>
                    <c:forEach items="${book.categories}" var="category">
                        <a href="/categories/${category.id}"><c:out value="${category.name} "/></a>
                    </c:forEach>
                </td>
            </c:when>
            <c:otherwise>
                <td></td>
            </c:otherwise>
        </c:choose>
    </tr>
    <tr>
        <td><spring:message code="book.field.weight"/></td>
        <c:choose>
            <c:when test="${not empty book.weight}">
                <td>${book.weight}</td>
            </c:when>
            <c:otherwise>
                <td></td>
            </c:otherwise>
        </c:choose>
    </tr>
</table>
<sec:authorize access="hasAnyRole('ROLE_LIBRARIAN','ROLE_ADMIN')">
    <sf:form method="post" action="/books/${book.id}/delete">
        <input type="submit" value=<spring:message code="book.action.delete"/>>
    </sf:form>
    <a href="/books/${book.id}?action=edit"><spring:message code="book.action.edit"/></a>
</sec:authorize>