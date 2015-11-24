<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="publisher.page.show.header"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1><spring:message code="publisher.name"/></h1>
<table border="1">
    <tr>
        <th><spring:message code="publisher.field.name"/></th>
        <th><spring:message code="publisher.field.address"/></th>
    </tr>
    <%--@elvariable id="publishers" type="java.util.Set"--%>
    <c:forEach items="${publishers}" var="publisher">
        <tr class="field">
            <td><a href="${pageContext.request.contextPath}/publishers/${publisher.id}"><c:out
                    value="${publisher.name}"/></a></td>
            <td><c:out value="${publisher.address}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/publishers/add"><spring:message code="publisher.action.add"/></a>
</body>
</html>