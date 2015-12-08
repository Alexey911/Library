<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h2><spring:message code="action.register"/></h2>
    <%--@elvariable id="error" type="java.lang.String"--%>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <%--@elvariable id="msg" type="java.lang.String"--%>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>
    <springForm:form method="POST" modelAttribute="user" action="${pageContext.request.contextPath}/register">
        <table>
            <tr>
                <td><spring:message code="user.field.login"/></td>
                <td><springForm:input path="login" value=""/></td>
                <td><springForm:errors path="login" cssClass="error"/></td>
            </tr>
            <tr>
                <td><spring:message code="user.field.password"/></td>
                <td><springForm:input path="password" value=""/></td>
                <td><springForm:errors path="password" cssClass="error"/></td>
            </tr>
            <tr>
                <td><spring:message code="user.status.librarian"/></td>
                <td><label><input type="checkbox" name="librarian"></label></td>
            </tr>
            <tr>
                <spring:message code="user.action.register" var="register"/>
                <td colspan="3"><input type="submit" value="${register}"></td>
            </tr>
        </table>
    </springForm:form>
</div>