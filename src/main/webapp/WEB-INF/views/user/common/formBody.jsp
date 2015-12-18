<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
            code="user.field.password"/>:</label>
    <spring:message code="user.placeholder.password" var="placeholder"/>
    <div class="col-sm-3">
        <springForm:password path="password" id="password" cssClass="form-control"
                             placeholder="${placeholder}"/>
    </div>
    <springForm:errors path="password" cssClass="alert alert-danger col-sm-5"/>
</div>

<div class="form-group">
    <label class="control-label col-sm-2" for="role"><spring:message code="user.field.role"/>:</label>

    <div class="col-sm-3">
        <select id="role" class="form-control" name="role">
            <option value="USER"><spring:message code="role.user"/></option>
            <option value="LIBRARIAN"><spring:message code="role.librarian"/></option>
            <option value="ADMIN"><spring:message code="role.admin"/></option>
        </select>
    </div>
</div>