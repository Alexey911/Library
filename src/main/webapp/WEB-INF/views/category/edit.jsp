<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<h1 class="h1 text-center"><spring:message code="category.action.edit"/></h1>
<sf:form method="POST" modelAttribute="category"
         action="${pageContext.request.contextPath}/categories/update"
         cssClass="form-horizontal" role="form">
    <springForm:hidden path="id"/>
    <%@ include file="common/formBody.jsp" %>
    <spring:message code="category.action.save" var="change"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">${change}</button>
        </div>
    </div>
</sf:form>