<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--@elvariable id="info" type="java.lang.String"--%>
<html>
<head>
    <title><spring:message code="home.page.header"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>${info}</h2>
<a href="${pageContext.request.contextPath}/publishers"><spring:message code="library.publishers"/></a>
<a href="${pageContext.request.contextPath}/categories"><spring:message code="library.categories"/></a>
<a href="${pageContext.request.contextPath}/books"><spring:message code="library.books"/></a>
<sec:authorize access="hasRole('ROLE_ADMIN')">
<a href="/users><spring:message code="library.users"/></a>
</sec:authorize>
</body>
</html>