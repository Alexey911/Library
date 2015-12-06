<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1><spring:message code="book.name"/></h1>
<springForm:form method="POST" modelAttribute="book" action="/books">
    <springForm:hidden path="id"/>
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
            <td><springForm:errors path="publishingYear" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="book.field.weight"/></td>
            <td><springForm:input path="weight"/></td>
            <td><springForm:errors path="weight" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label>
                <springForm:select path="publisher.id" multiple="false">
                    <c:forEach var="publisher" items="${publishers}">
                        <option value="${publisher.id}" label="${publisher.name}"></option>
                    </c:forEach>
                </springForm:select>
            </label>
            </td>
        </tr>
        <tr>
            <td>
                <label>
                    <select name="newCategories" multiple>
                        <c:forEach var="category" items="${newCategories}">
                            <option value="${category.id}" label="${category.name}"></option>
                        </c:forEach>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <spring:message code="book.action.add" var="save"/>
            <td colspan="3"><input type="submit" value="${save}"></td>
        </tr>
    </table>
</springForm:form>