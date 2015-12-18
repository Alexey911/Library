<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1 class="h1 text-center"><spring:message code="user.action.edit"/></h1>
<sf:form method="POST" modelAttribute="user"
         action="${contextPath}/users/update"
         cssClass="form-horizontal" role="form">
    <springForm:hidden path="id"/>
    <input type="hidden" name="password" value="UNKNOWN">
    <input type="hidden" name="lastRole" value="${user.role}">

    <div class="form-group">
        <label class="control-label col-sm-2" for="login"><spring:message code="user.field.login"/>:</label>

        <c:set var="placeholder"><spring:message code="user.field.login"/></c:set>

        <div class="col-sm-2">
            <springForm:input path="login" id="login" cssClass="form-control"
                              placeholder="${placeholder}"/>
        </div>
        <springForm:errors path="login" cssClass="alert alert-danger col-sm-5"/>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="role"><spring:message code="user.field.role"/>:</label>

        <div class="col-sm-5">
            <select name="role" id="role">
                <option value="USER"><spring:message code="role.user"/></option>
                <option value="LIBRARIAN"><spring:message code="role.librarian"/></option>
                <option value="ADMIN"><spring:message code="role.admin"/></option>
            </select>
        </div>
    </div>

    <spring:message code="action.save" var="save"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">${save}</button>
        </div>
    </div>
</sf:form>