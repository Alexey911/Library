<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <li>
        <a href="${contextPath}/users"><spring:message code="users.action.show.all"/></a>
    </li>
    <li>
        <a href="${contextPath}/users?filter=notConfirmed"><spring:message code="menu.confirm"/></a>
    </li>
    <li>
        <a href="${contextPath}/users?page=add"><spring:message code="user.action.add"/></a>
    </li>
</sec:authorize>