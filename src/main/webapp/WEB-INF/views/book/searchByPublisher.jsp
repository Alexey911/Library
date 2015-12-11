<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1><spring:message code="book.search.by.publisher"/></h1>
<springForm:form method="GET" action="${pageContext.request.contextPath}/books">
    <input type="hidden" name="action" value="search"/>
    <input type="hidden" name="filter" value="publisher"/>
    <table>
        <tr>
            <td><spring:message code="publisher.name"/></td>
            <td>
                <label>
                </label>
                <select name="publisher">
                    <c:forEach var="publisher" items="${publishers}">
                        <c:choose>
                            <%--@elvariable id="selectedId" type="java.lang.Integer"--%>
                            <c:when test="${publisher.id eq selectedId}">
                                <option value="${publisher.id}" selected>${publisher.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${publisher.id}">${publisher.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <spring:message code="book.action.find.by.publisher" var="search"/>
            <td colspan="3"><input type="submit" value="${search}"></td>
        </tr>
    </table>
</springForm:form>
<h1><spring:message code="books.name"/></h1>
<c:choose>
    <c:when test="${not empty books}">
        <%@ include file="common/printBooks.jsp" %>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty selectedId}">
            <h2><spring:message code="search.result.not.found"/></h2>
        </c:if>
    </c:otherwise>
</c:choose>