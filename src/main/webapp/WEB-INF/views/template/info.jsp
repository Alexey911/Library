<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<li>
    <a href="${pageContext.request.contextPath}/books"><spring:message code="menu.books"/></a>
</li>
<li>
    <a href="${pageContext.request.contextPath}/publishers"><spring:message code="menu.publishers"/></a>
</li>
<li>
    <a href="${pageContext.request.contextPath}/categories"><spring:message code="menu.categories"/></a>
</li>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <li>
        <a href="${pageContext.request.contextPath}/users"><spring:message code="menu.users"/></a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/users?action=confirm"><spring:message code="menu.confirm"/></a>
    </li>
</sec:authorize>