<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1><spring:message code="books.name"/></h1>
<%--@elvariable id="books" type="java.util.List"--%>
<%--@elvariable id="book" type="com.zhytnik.library.domain.Book"--%>
<%@ include file="print.jsp" %>
<a href="${contextPath}/books?action=showSearchPage&filter=publisher"><spring:message
        code="book.search.by.publisher"/></a>
<br>
<a href="${contextPath}/books?action=showSearchPage&filter=category"><spring:message
        code="book.search.by.category"/></a>
<br>
<sec:authorize access="hasAnyRole('ROLE_LIBRARIAN', 'ROLE_ADMIN')">
    <a href="${contextPath}/books/add"><spring:message code="book.action.add"/></a>
</sec:authorize>
