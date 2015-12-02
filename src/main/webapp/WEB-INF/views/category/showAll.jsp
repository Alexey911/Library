<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <h1><spring:message code="categories.name"/></h1>
        <table border="1">
            <tr>
                <th><spring:message code="category.field.name"/></th>
                <th><spring:message code="category.field.description"/></th>
            </tr>
                <%--@elvariable id="categories" type="java.util.Set"--%>
            <c:forEach items="${categories}" var="publisher">
                <tr class="field">
                    <td><a href="${pageContext.request.contextPath}/categories/${publisher.id}"><c:out
                            value="${publisher.name}"/></a></td>
                    <td><c:out value="${publisher.description}"/></td>
                </tr>
            </c:forEach>
        </table>
    </tiles:putAttribute>
</tiles:insertDefinition>