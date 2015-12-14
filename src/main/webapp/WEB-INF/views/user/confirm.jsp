<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1><spring:message code="users.name"/></h1>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<sf:form method="PUT" action="${contextPath}/users">
    <input type="hidden" name="action" value="confirm">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="user.field.login"/></th>
            <th><spring:message code="user.field.role"/></th>
            <th><spring:message code="user.field.enabled"/></th>
            <th><spring:message code="user.action.confirm"/></th>
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
                <td>
                    <label>
                        <input type="checkbox" name="users" value="${user.id}">
                    </label>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <spring:message code="action.confirm" var="confirm"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">${confirm}</button>
        </div>
    </div>
</sf:form>