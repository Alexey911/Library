<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="registration.page.header"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border: 1px solid #ebccd1;
        }

        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border: 1px solid #bce8f1;
        }
    </style>
</head>
<body>
<div>
    <h2><spring:message code="library.action.registration"/></h2>

    <%--@elvariable id="error" type="java.lang.String"--%>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <%--@elvariable id="msg" type="java.lang.String"--%>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>
    <springForm:form method="POST" modelAttribute="user" action="/registration">
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
                <td><label><input type="checkbox"></label></td>
            </tr>
            <tr>
                <spring:message code="user.action.register" var="register"/>
                <td colspan="3"><input type="submit" value="${register}"></td>
            </tr>
        </table>
    </springForm:form>
</div>

</body>
</html>