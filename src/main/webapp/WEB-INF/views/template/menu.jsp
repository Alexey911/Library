<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul style="list-style:none;line-height:28px;">
	<sec:authorize access="isAnonymous()">
		<h1><spring:message code="library.action.login"/></h1>

		<div id="login-box">
			<h2><spring:message code="login.page.title"/></h2>
				<%--@elvariable id="error" type="java.lang.String"--%>
			<c:if test="${not empty error}">
				<div class="errorInPass">${error}</div>
			</c:if>
				<%--@elvariable id="msg" type="java.lang.String"--%>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			<form name='loginForm' action="<c:url value='j_spring_security_check'/>" method='POST'>
				<table>
					<tr>
						<td><spring:message code="user.field.login"/></td>
						<td><label><input type='text' name='username' value=''></label></td>
					</tr>
					<tr>
						<td><spring:message code="user.field.password"/></td>
						<td><label><input type='password' name='password'/></label></td>
					</tr>
					<tr>
						<spring:message code="library.action.login" var="login"/>
						<td colspan='2'><input name="submit" type="submit" value="${login}"/></td>
					</tr>
				</table>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
			<a href="${pageContext.request.contextPath}/registration"><spring:message
					code="library.action.registration"/></a>
		</div>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
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
    </sec:authorize>
	<a href="${pageContext.request.contextPath}?locale=en">English </a> |
	<a href="${pageContext.request.contextPath}?locale=ru">Russian</a>
</ul>