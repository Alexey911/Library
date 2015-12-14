<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="error" type="java.lang.String"--%>
<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>
<%--@elvariable id="msg" type="java.lang.String"--%>
<c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
</c:if>
<h2 class="h2"><spring:message code="action.register"/></h2>
<sf:form method="POST" modelAttribute="user"
         action="${pageContext.request.contextPath}/register"
         cssClass="form-horizontal" role="form">
    <%@ include file="user/common/formBody.jsp" %>

    <spring:message code="user.action.register" var="register"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">${register}</button>
        </div>
    </div>
</sf:form>