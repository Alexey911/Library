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
<a href="${contextPath}/users/${user.id}?action=edit"><spring:message code="user.action.edit"/></a>
<a href="${contextPath}/users/${user.id}?action=changePassword"><spring:message
        code="user.action.change.password"/> </a>
<sf:form method="post" action="${contextPath}/users/${user.id}">
    <input type="submit" value=<spring:message code="user.action.delete"/>>
</sf:form>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <br>
    <sf:form method="POST" action="${contextPath}/users/${user.id}?action=disable">
        <input type="submit" value=<spring:message code="user.action.disable"/>>
    </sf:form>
    <br>
    <sf:form method="POST" action="${contextPath}/users/${user.id}?action=activate">
        <input type="submit" value=<spring:message code="user.action.activate"/>>
    </sf:form>
</sec:authorize>