<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Registration Page</title>
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jQuery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user_form_checking.js"></script>
</head>
<body>

<h1>Library</h1>

<div>

    <h2>Registration</h2>

    <%--@elvariable id="error" type="java.lang.String"--%>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <%--@elvariable id="msg" type="java.lang.String"--%>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>
    <springForm:form method="POST" modelAttribute="user">
        <table>
            <tr>
                <td><spring:message code="user.field.login"/></td>
                <td><springForm:input path="login"/></td>
                <td><springForm:errors path="login" cssClass="error"/></td>
            </tr>
            <tr>
                <td><spring:message code="user.field.password"/></td>
                <td><springForm:input path="password"/></td>
                <td><springForm:errors path="password" cssClass="error"/></td>
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