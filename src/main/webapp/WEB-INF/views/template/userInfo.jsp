<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="principal" property="principal"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<a href="${contextPath}/users?filter=me" style="color:dodgerblue" class="navbar-brand"><c:out
        value="${principal.username}"/></a>
<!-- csrt for log out-->
<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<form:form action="${logoutUrl}" method="post" id="logoutForm"/>
<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>
<a href="javascript:formSubmit()"><spring:message code="action.logout"/></a>