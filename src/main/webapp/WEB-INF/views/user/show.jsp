<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1><spring:message code="user.name"/></h1>
<%--@elvariable id="user" type="com.zhytnik.library.domain.User"--%>
<table border="1">
    <tr>
        <td><spring:message code="user.field.login"/></td>
        <td>${user.login}</td>
    </tr>
    <tr>
        <td><spring:message code="user.field.role"/></td>
        <td>${user.role}</td>
    </tr>
</table>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <sf:form method="DELETE" action="/users/${user.id}">
        <input type="submit" value="Delete">
    </sf:form>
    <br>
    <sf:form method="POST" action="/users/${user.id}?action=disable">
        <input type="submit" value="Disable">
    </sf:form>
    <br>
    <sf:form method="POST" action="/users/${user.id}?action=activate">
        <input type="submit" value="Activate">
    </sf:form>
</sec:authorize>