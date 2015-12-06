<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--@elvariable id="errMsg" type="java.lang.String"--%>
<p>${errMsg}</p>
<a href="#" onclick="history.back(); return false;"><spring:message code="action.back"/></a>