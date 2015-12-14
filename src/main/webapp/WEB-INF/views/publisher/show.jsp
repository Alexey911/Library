<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1 class="h1 text-center">${publisher.name}</h1>
<%--@elvariable id="publisher" type="com.zhytnik.library.domain.Publisher"--%>
<sec:authorize access="hasAnyRole('ROLE_LIBRARIAN','ROLE_ADMIN')">
    <sf:form method="post" action="${contextPath}/publishers/${publisher.id}/delete" id="form"/>
    <script>
        function formSubmit() {
            document.getElementById("form").submit();
        }
    </script>

    <div align="center">
        <ul class="list-inline">
            <li>
                <a href="${contextPath}/publishers/${publisher.id}?page=edit"><spring:message
                        code="publisher.action.edit"/></a>
            </li>
            <li>
                <a href="javascript:formSubmit()"><spring:message code="category.action.delete"/></a>
            </li>
        </ul>
    </div>
</sec:authorize>

<table class="table">
    <tr>
        <td><spring:message code="publisher.field.address"/></td>
        <td>${publisher.address}</td>
    </tr>
</table>