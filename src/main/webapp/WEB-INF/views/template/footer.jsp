<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<p align="center">
    <b> <spring:message code="site.about"/></b>
    <spring:message code="site.rights"/>
    <a href="${contextPath}?locale=en"><spring:message code="language.en"/></a> |
    <a href="${contextPath}?locale=ru"><spring:message code="language.ru"/></a>
</p>