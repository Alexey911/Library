<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="category.exception.header"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%--@elvariable id="errMsg" type="java.lang.String"--%>
<p>${errMsg}</p>
<a href="#" onclick="history.back(); return false;"><spring:message code="category.action.back"/></a>
</body>
</html>