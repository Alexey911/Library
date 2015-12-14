<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1 class="h1 text-center"><spring:message code="user.action.add"/></h1>
<springForm:form method="POST" modelAttribute="user"
                 action="${pageContext.request.contextPath}/users"
                 class="form-horizontal" role="form">
    <%@ include file="common/formBody.jsp" %>

    <spring:message code="action.add" var="add"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">${add}</button>
        </div>
    </div>
</springForm:form>