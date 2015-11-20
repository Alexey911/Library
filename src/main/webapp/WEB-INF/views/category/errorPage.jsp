<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Exception</title>
</head>
<body>

<%--@elvariable id="errMsg" type="java.lang.String"--%>

<p>${errMsg}</p>
<a href="#" onclick="history.back(); return false;">Back</a>

</body>
</html>