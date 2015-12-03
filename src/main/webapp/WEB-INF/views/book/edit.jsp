<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<h1><spring:message code="book.name"/></h1>
<%--@elvariable id="book" type="com.zhytnik.library.domain.Book"--%>
<springForm:form method="POST" modelAttribute="book" action="/">
    <table>
        <tr>
            <td><spring:message code="book.field.name"/></td>
            <td><springForm:input path="name"/></td>
            <td><springForm:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="book.field.authors"/></td>
            <td><springForm:input path="authors"/></td>
            <td><springForm:errors path="authors" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="book.field.authors"/></td>
            <td><springForm:input path="authors"/></td>
            <td><springForm:errors path="authors" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="book.field.page.count"/></td>
            <td><springForm:input path="pageCount"/></td>
            <td><springForm:errors path="pageCount" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="book.field.annotation"/></td>
            <td><springForm:input path="annotation"/></td>
            <td><springForm:errors path="annotation" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="book.field.year"/></td>
            <td><springForm:input path="publishingYear"/></td>
            <td><springForm:errors path="pageCount" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="book.field.weight"/></td>
            <td><springForm:input path="weight"/></td>
            <td><springForm:errors path="weight" cssClass="error"/></td>
        </tr>
        <tr>
            <spring:message code="category.action.save" var="save"/>
            <td colspan="3"><input type="submit" value="${save}"></td>
        </tr>
    </table>
</springForm:form>

<table>
    <tr>
        <td><spring:message code="book.field.publisher"/></td>
        <td><a href="/publishers/${book.publisher.id}">${book.publisher.name}</a></td>
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
</table>