<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group">
    <label class="control-label col-sm-2" for="name"><spring:message code="category.field.name"/>:</label>

    <c:set var="placeholder"><spring:message code="category.placeholder.name"/></c:set>

    <div class="col-sm-5">
        <springForm:input path="name" id="name" cssClass="form-control"
                          placeholder="${placeholder}"/>
    </div>
    <springForm:errors path="name" cssClass="alert alert-danger col-sm-5"/>
</div>

<div class="form-group">
    <label class="control-label col-sm-2" for="description"><spring:message code="category.field.description"/>:</label>

    <div class="col-sm-5">
        <springForm:textarea path="description" id="description" cssClass="form-control" rows="5"/>
    </div>
    <springForm:errors path="description" cssClass="alert alert-danger col-sm-5"/>
</div>