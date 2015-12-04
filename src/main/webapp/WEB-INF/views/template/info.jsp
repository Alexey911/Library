<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<li>
    <a href="${pageContext.request.contextPath}/books"><spring:message code="library.books"/></a>
</li>
<li>
    <a href="${pageContext.request.contextPath}/publishers"><spring:message code="library.publishers"/></a>
</li>
<li>
    <a href="${pageContext.request.contextPath}/categories"><spring:message code="library.categories"/></a>
</li>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <li>
        <a href="${pageContext.request.contextPath}/users"><spring:message code="library.users"/></a>
    </li>
</sec:authorize>