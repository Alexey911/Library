<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${user.login}</h1>
<%--@elvariable id="user" type="com.zhytnik.library.domain.User"--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<table border="1">
    <tr>
        <td><spring:message code="user.field.role"/></td>
        <td>${user.role}</td>
    </tr>
    <tr>
        <td><spring:message code="user.field.confirmed"/></td>
        <td>${user.isConfirmed()}</td>
    </tr>
</table>
<a href="${contextPath}/users/${user.id}?page=edit"><spring:message code="user.action.edit"/></a>
<a href="${contextPath}/users/${user.id}?page=edit&field=password"><spring:message
        code="user.action.change.password"/> </a>
<sf:form method="DELETE" action="${contextPath}/users/${user.id}">
    <input type="submit" value=<spring:message code="user.action.delete"/>>
</sf:form>
<sec:authentication var="principal" property="principal"/>
<c:if test="${not principal.getUsername() eq user.login}">
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <br>
        <c:choose>
            <c:when test="${user.isConfirmed()}">
                <sf:form method="PUT" action="${contextPath}/users/${user.id}">
                    <input type="hidden" name="action" value="resetConfirm">
                    <input type="submit" value=<spring:message code="user.action.reset.confirm"/>>
                </sf:form>
            </c:when>
            <c:otherwise>
                <sf:form method="PUT" action="${contextPath}/users/${user.id}">
                    <input type="hidden" name="action" value="confirm">
                    <input type="submit" value=<spring:message code="user.action.confirm"/>>
                </sf:form>
            </c:otherwise>
        </c:choose>
        <br>
        <c:choose>
            <c:when test="${user.isEnable()}">
                <sf:form method="PUT" action="${contextPath}/users/${user.id}">
                    <input type="hidden" name="action" value="disable">
                    <input type="submit" value=<spring:message code="user.action.disable"/>>
                </sf:form>
            </c:when>
            <c:otherwise>
                <sf:form method="PUT" action="${contextPath}/users/${user.id}">
                    <input type="hidden" name="action" value="activate">
                    <input type="submit" value=<spring:message code="user.action.activate"/>>
                </sf:form>
            </c:otherwise>
        </c:choose>
    </sec:authorize>
</c:if>