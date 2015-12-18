<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="user" type="com.zhytnik.library.domain.User"--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1 class="h1 text-center">${user.login}</h1>
<sec:authorize access="hasAnyRole('ROLE_LIBRARIAN','ROLE_ADMIN')">
    <sf:form method="DELETE" action="${contextPath}/users/${user.id}" id="form"/>
    <script>
        function formSubmit() {
            document.getElementById("form").submit();
        }
    </script>

    <div align="center">
        <ul class="list-inline">
            <li>
                <a href="${contextPath}/users/${user.id}?page=edit"><spring:message code="user.action.edit"/></a>
            </li>
            <li>
                <a href="${contextPath}/users/${user.id}?page=edit&field=password"><spring:message
                        code="user.action.change.password"/> </a>
            </li>
            <li>
                <a href="javascript:formSubmit()"><spring:message code="user.action.delete"/></a>
            </li>
        </ul>
    </div>
</sec:authorize>
<table class="table table-hover">
    <tr>
        <td><spring:message code="user.field.role"/></td>
        <td>${user.role}</td>
    </tr>
    <tr>
        <td><spring:message code="user.field.confirmed"/></td>
        <td>${user.isConfirmed()}</td>
    </tr>
    <tr>
        <td><spring:message code="user.field.enabled"/></td>
        <td>${user.isEnable()}</td>
    </tr>
</table>

<sec:authentication var="principal" property="principal"/>
<c:if test="${principal.getUsername() ne user.login}">
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <br>

        <h2 class="h2"><spring:message code="user.admin.control"/></h2>
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
                    <input type="submit" class="button" value=<spring:message code="user.action.disable"/>>
                </sf:form>
            </c:when>
            <c:otherwise>
                <sf:form method="PUT" action="${contextPath}/users/${user.id}">
                    <input type="hidden" name="action" value="activate">
                    <input type="submit" class="button" value=<spring:message code="user.action.activate"/>>
                </sf:form>
            </c:otherwise>
        </c:choose>
    </sec:authorize>
</c:if>