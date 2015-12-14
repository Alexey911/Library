<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1 class="h1 text-center"><spring:message code="users.name"/></h1>
<table class="table table-striped">
    <thead>
    <tr>
        <th><spring:message code="user.field.login"/></th>
        <th><spring:message code="user.field.role"/></th>
        <th><spring:message code="user.field.enabled"/></th>
        <th><spring:message code="user.field.confirmed"/></th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="users" type="java.util.List"--%>
    <%--@elvariable id="user" type="com.zhytnik.library.domain.User"--%>
    <c:forEach items="${users}" var="user">
        <tr class="field">
            <td><a href="${contextPath}/users/${user.id}"><c:out
                    value="${user.login}"/></a></td>
            <td><c:out value="${user.role}"/></td>
            <td><c:out value="${user.isEnable()}"/></td>
            <td><c:out value="${user.isConfirmed()}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>