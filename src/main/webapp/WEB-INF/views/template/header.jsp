<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span-24">
    <img src="${pageContext.request.contextPath}/resources/images/header.png"
         width="950" style="padding-top:10px;"/>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication var="principal" property="principal"/>
		<c:out value="${principal.username}"/>
	</sec:authorize>
</div>