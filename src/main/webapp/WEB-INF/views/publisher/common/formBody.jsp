<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="form-group">
    <label class="control-label col-sm-2" for="name"><spring:message code="publisher.field.name"/>:</label>

    <c:set var="placeholder"><spring:message code="publisher.placeholder.name"/></c:set>

    <div class="col-sm-5">
        <springForm:input path="name" id="name" cssClass="form-control"
                          placeholder="${placeholder}"/>
    </div>
    <springForm:errors path="name" cssClass="alert alert-danger col-sm-5"/>
</div>

<div class="form-group">
    <label class="control-label col-sm-2" for="address"><spring:message code="publisher.field.address"/>:</label>

    <div class="col-sm-5">
        <springForm:textarea path="address" id="address" cssClass="form-control" rows="5"/>
    </div>
    <springForm:errors path="address" cssClass="alert alert-danger col-sm-5"/>
</div>