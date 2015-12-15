<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<h1 class="h1 text-center"><spring:message code="publisher.action.edit"/></h1>
<sf:form method="POST" modelAttribute="publisher"
         action="${pageContext.request.contextPath}/publishers/update"
         cssClass="form-horizontal" role="form">
    <springForm:hidden path="id"/>
    <%@ include file="common/formBody.jsp" %>
    <spring:message code="publisher.action.save" var="save"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">${save}</button>
        </div>
    </div>
</sf:form>