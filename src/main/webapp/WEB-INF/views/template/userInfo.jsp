<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication var="principal" property="principal"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <a href="${contextPath}/users?filter=me" class="navbar-brand"><c:out value="${principal.username}"/></a>
</sec:authorize>