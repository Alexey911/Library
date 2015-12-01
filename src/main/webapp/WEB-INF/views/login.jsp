<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="login.page.header"/></title>
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

        #login-box {
            width: 300px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
    </style>
</head>
<body onload='document.loginForm.username.focus();'>

<h1><spring:message code="library.action.login"/></h1>

<div id="login-box">

    <h2><spring:message code="login.page.title"/></h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>

    <form name='loginForm' action="<c:url value='j_spring_security_check'/>" method='POST'>
        <table>
            <tr>
                <td><spring:message code="user.field.login"/></td>
                <td><label><input type='text' name='username' value=''></label></td>
            </tr>
            <tr>
                <td><spring:message code="user.field.password"/></td>
                <td><label><input type='password' name='password'/></label></td>
            </tr>
            <tr>
                <spring:message code="library.action.login" var="login"/>
                <td colspan='2'><input name="submit" type="submit" value="${login}"/></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <a href="${pageContext.request.contextPath}/registration"><spring:message code="library.action.registration"/></a>
</div>

</body>
</html>