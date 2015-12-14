<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1 class="h1 text-center"><spring:message code="user.action.add"/></h1>
<springForm:form method="POST" modelAttribute="user"
                 action="${pageContext.request.contextPath}/users"
                 class="form-horizontal" role="form">
    <div class="form-group">
        <label class="control-label col-sm-2" for="login"><spring:message code="user.field.login"/>:</label>

        <c:set var="placeholder"><spring:message code="user.placeholder.login"/></c:set>

        <div class="col-sm-3">
            <springForm:input path="login" id="login" cssClass="form-control"
                              placeholder="${placeholder}"/>
        </div>
        <springForm:errors path="login" cssClass="alert alert-danger col-sm-5"/>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="password"><spring:message
                code="user.new.password"/>:</label>
        <spring:message code="user.placeholder.password" var="placeholder"/>
        <div class="col-sm-3">
            <springForm:password path="password" id="password" cssClass="form-control"
                                 placeholder="${placeholder}"/>
        </div>
        <springForm:errors path="password" cssClass="alert alert-danger col-sm-5"/>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="role"><spring:message code="category.name"/>:</label>

        <div class="col-sm-3">
            <select id="role" class="form-control" name="role">
                <option value="USER"><spring:message code="role.user"/></option>
                <option value="LIBRARIAN"><spring:message code="role.librarian"/></option>
                <option value="ADMIN"><spring:message code="role.admin"/></option>
            </select>
        </div>
    </div>

    <spring:message code="action.add" var="add"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">${add}</button>
        </div>
    </div>
</springForm:form>