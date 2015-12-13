<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="h1 text-center"><spring:message code="book.search.by.category"/></h1>
<springForm:form method="GET" action="${pageContext.request.contextPath}/books" cssClass="form-horizontal">
    <input type="hidden" name="action" value="search"/>
    <input type="hidden" name="filter" value="category"/>

    <div class="form-group">
        <label class="control-label col-sm-2" for="category"><spring:message code="category.name"/>:</label>

        <div class="col-sm-3">
            <select id="category" class="form-control" name="category">
                    <%--@elvariable id="categories" type="java.util.List"--%>
                <c:forEach var="publisher" items="${categories}">
                    <c:choose>
                        <c:when test="${publisher.id eq selectedId}">
                            <option value="${publisher.id}" selected>${publisher.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${publisher.id}">${publisher.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
    </div>
    <spring:message code="book.action.find.by.category" var="search"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">${search}</button>
        </div>
    </div>
</springForm:form>
<c:choose>
    <c:when test="${not empty books}">
        <h2 class="h2 text-center"><spring:message code="books.name"/></h2>
        <%@ include file="common/printBooks.jsp" %>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty selectedId}">
            <h3 class="h3 text-center"><spring:message code="search.result.not.found"/></h3>
        </c:if>
    </c:otherwise>
</c:choose>