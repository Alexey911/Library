<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<li>
    <a href="${contextPath}/books"><spring:message code="menu.books"/></a>
</li>
<li>
    <a href="${contextPath}/publishers"><spring:message code="menu.publishers"/></a>
</li>
<li>
    <a href="${contextPath}/categories"><spring:message code="menu.categories"/></a>
</li>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <li>
        <a href="${contextPath}/users"><spring:message code="menu.users"/></a>
    </li>
</sec:authorize>