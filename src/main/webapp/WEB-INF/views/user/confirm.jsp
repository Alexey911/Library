<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1><spring:message code="users.name"/></h1>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<sf:form method="POST" action="${contextPath}/users/confirm">
    <table border="1">
        <tr>
            <th><spring:message code="user.field.login"/></th>
            <th><spring:message code="user.field.role"/></th>
            <th><spring:message code="user.field.enabled"/></th>
            <th><spring:message code="user.action.confirm"/></th>
        </tr>
            <%--@elvariable id="users" type="java.util.Set"--%>
            <%--@elvariable id="user" type="com.zhytnik.library.domain.User"--%>
        <c:forEach items="${users}" var="user">
            <tr class="field">
                <td><a href="${contextPath}/users/${user.id}"><c:out
                        value="${user.login}"/></a></td>
                <td><c:out value="${user.role}"/></td>
                <td><c:out value="${user.isEnable()}"/></td>
                <td>
                    <label>
                        <input type="checkbox" name="users" value="${user.id}">
                    </label>
                </td>
            </tr>
        </c:forEach>
    </table>
    <spring:message code="action.confirm" var="change"/>
    <input type="submit" value="${change}">
</sf:form>