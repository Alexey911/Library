<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="h1 text-center"><spring:message code="category.name"/></h1>
<springForm:form method="POST" modelAttribute="category"
                 action="${pageContext.request.contextPath}/categories"
                 class="form-horizontal" role="form">
    <%@ include file="common/formBody.jsp" %>

    <spring:message code="category.action.add" var="change"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">${change}</button>
        </div>
    </div>
</springForm:form>