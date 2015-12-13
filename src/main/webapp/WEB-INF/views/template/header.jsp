<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<nav class="collapse navbar-collapse" role="navigation">
    <ul class="nav navbar-nav">
        <li>
            <a href="${contextPath}/home"><spring:message code="menu.home"/></a>
        </li>
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
    </ul>
</nav>