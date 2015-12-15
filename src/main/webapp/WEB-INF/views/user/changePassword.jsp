<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1 class="h1 text-center"><spring:message code="user.change.password"/></h1>
<springForm:form method="POST" modelAttribute="wrapper"
                 action="${pageContext.request.contextPath}/users/updatePassword"
                 class="form-horizontal" role="form">
    <springForm:hidden path="ownerId"/>
    <div class="form-group">
        <label class="control-label col-sm-2" for="lastPassword"><spring:message
                code="user.last.password"/>:</label>
        <spring:message code="user.placeholder.last.password" var="placeholder"/>
        <div class="col-sm-3">
            <springForm:password path="lastPassword" id="lastPassword" cssClass="form-control"
                                 placeholder="${placeholder}"/>
        </div>
        <springForm:errors path="lastPassword" cssClass="alert alert-danger col-sm-5"/>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="newPassword"><spring:message
                code="user.new.password"/>:</label>
        <spring:message code="user.placeholder.new.password" var="placeholder"/>
        <div class="col-sm-3">
            <springForm:password path="newPassword" id="newPassword" cssClass="form-control"
                                 placeholder="${placeholder}"/>
        </div>
        <springForm:errors path="newPassword" cssClass="alert alert-danger col-sm-5"/>
    </div>
    <spring:message code="action.change" var="change"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">${change}</button>
        </div>
    </div>
</springForm:form>
