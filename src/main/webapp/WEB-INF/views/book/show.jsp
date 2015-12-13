<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1 class="h1 text-center">${book.name}</h1>
<sec:authorize access="hasAnyRole('ROLE_LIBRARIAN','ROLE_ADMIN')">
    <sf:form method="DELETE" action="${contextPath}/books/${book.id}" id="form"/>
    <script>
        function formSubmit() {
            document.getElementById("form").submit();
        }
    </script>

    <div align="center">
        <ul class="list-inline">
            <li>
                <a href="${contextPath}/books/${book.id}?page=edit"><spring:message code="book.action.edit"/></a>
            </li>
            <li>
                <a href="javascript:formSubmit()"><spring:message code="book.action.delete"/></a>
            </li>
        </ul>
    </div>
</sec:authorize>
<%--@elvariable id="book" type="com.zhytnik.library.domain.Book"--%>
<table class="table table-hover">
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
        <td><a href="${contextPath}/publishers/${book.publisher.id}">${book.publisher.name}</a></td>
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
                    <c:forEach items="${book.categories}" var="publisher">
                        <a href="${contextPath}/categories/${publisher.id}"><c:out value="${publisher.name} "/></a>
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