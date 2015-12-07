<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <c:url value="/j_spring_security_check" var="checkUrl"/>
    <form:form name='loginForm' action="${checkUrl}" method='POST'>
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
                <td><spring:message code="action.remember"/></td>
                <td>
                    <label><input type="checkbox" name="_spring_security_remember_me"/></label>
                </td>
            </tr>
            <tr>
                <spring:message code="action.sign.in" var="signIn"/>
                <td colspan='2'><input name="submit" type="submit" value="${signIn}"/></td>
            </tr>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </table>
    </form:form>
    <a href="${pageContext.request.contextPath}/register"><spring:message
            code="action.register"/></a>
</div>