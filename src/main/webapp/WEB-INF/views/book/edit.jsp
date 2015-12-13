<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="h1 text-center"><spring:message code="book.name"/></h1>
<springForm:form method="POST" modelAttribute="book"
                 action="${pageContext.request.contextPath}/books/update"
                 class="form-horizontal" role="form">
    <springForm:hidden path="id"/>
    <%@ include file="common/formBody.jsp" %>
    <spring:message code="book.action.save" var="search"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">${search}</button>
        </div>
    </div>
</springForm:form>