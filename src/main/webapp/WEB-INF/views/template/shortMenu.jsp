<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul style="list-style:none;line-height:28px;">
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <a href="${contextPath}?locale=en"><spring:message code="language.en"/></a> |
    <a href="${contextPath}?locale=ru"><spring:message code="language.ru"/></a>
</ul>