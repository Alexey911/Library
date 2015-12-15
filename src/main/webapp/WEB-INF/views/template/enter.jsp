<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <h2 class="h2 text-center"><spring:message code="login.page.title"/></h2>
    <%--@elvariable id="error" type="java.lang.String"--%>
    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center">${error}</div>
    </c:if>
    <%--@elvariable id="msg" type="java.lang.String"--%>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">${msg}</div>
    </c:if>
    <c:url value="${contextPath}/j_spring_security_check" var="checkUrl"/>
    <sf:form method="POST" action="${checkUrl}" cssClass="form-horizontal" role="form">
        <table>
            <tr>
                <td><spring:message code="user.field.login"/></td>
                <td><label><input type='text' name='username' class="form-control" size="15"></label></td>
            </tr>
            <tr>
                <td><spring:message code="user.field.password"/></td>
                <td><label><input type='password' name='password' class="form-control" size="15"/></label></td>
            </tr>
            <tr>
                <td><spring:message code="action.remember"/></td>
                <td>
                    <label><input type="checkbox" name="_spring_security_remember_me"/></label>
                </td>
            </tr>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </table>
        <spring:message code="action.sign.in" var="signIn"/>
        <div class="form-group text-center">
            <button type="submit" class="btn btn-default">${signIn}</button>
        </div>
    </sf:form>
    <div align="center">
        <a href="${contextPath}/register"><spring:message code="action.register"/></a>
    </div>
</div>