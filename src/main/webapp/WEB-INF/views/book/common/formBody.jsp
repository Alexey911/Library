<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="form-group">
    <label class="control-label col-sm-2" for="name"><spring:message code="book.field.name"/>:</label>

    <c:set var="placeholder"><spring:message code="book.placeholder.name"/></c:set>

    <div class="col-sm-5">
        <springForm:input path="name" id="name" cssClass="form-control"
                          placeholder="${placeholder}"/>
    </div>
    <springForm:errors path="name" cssClass="alert alert-danger col-sm-5"/>
</div>

<div class="form-group">
    <label class="control-label col-sm-2" for="authors"><spring:message code="book.field.authors"/>:</label>

    <c:set var="placeholder"><spring:message code="book.placeholder.authors"/></c:set>

    <div class="col-sm-5">
        <springForm:input path="authors" id="authors" cssClass="form-control" placeholder="${placeholder}"/>
    </div>
    <springForm:errors path="authors" cssClass="alert alert-danger col-sm-5"/>
</div>

<div class="form-group">
    <label class="control-label col-sm-2" for="pageCount"><spring:message code="book.field.page.count"/>:</label>

    <c:set var="placeholder"><spring:message code="book.placeholder.page.count"/></c:set>

    <div class="col-sm-5">
        <springForm:input path="pageCount" type="number" id="pageCount" cssClass="form-control"
                          placeholder="${placeholder}"/>
    </div>
    <springForm:errors path="pageCount" cssClass="alert alert-danger col-sm-5"/>
</div>

<div class="form-group">
    <label class="control-label col-sm-2" for="publisher"><spring:message code="book.field.publisher"/>:</label>

    <div class="col-sm-5">
        <springForm:select path="publisher.id" id="publisher" multiple="false" cssClass="form-control">
            <%@ include file="printPublishers.jsp" %>
        </springForm:select>
    </div>
    <springForm:errors path="publisher" cssClass="alert alert-danger col-sm-5"/>
</div>

<div class="form-group">
    <label class="control-label col-sm-2" for="categories"><spring:message code="book.field.categories"/>:</label>

    <div class="col-sm-5">
        <select id="categories" class="form-control" name="selected" multiple>
            <%@ include file="printCategories.jsp" %>
        </select>
    </div>
</div>

<div class="form-group">
    <label class="control-label col-sm-2" for="publishingYear"><spring:message code="book.field.year"/>:</label>

    <div class="col-sm-5">
        <springForm:input path="publishingYear" id="publishingYear" cssClass="form-control"/>
    </div>
    <springForm:errors path="publishingYear" cssClass="alert alert-danger col-sm-5"/>
</div>

<div class="form-group">
    <label class="control-label col-sm-2" for="weight"><spring:message code="book.field.weight"/>:</label>

    <div class="col-sm-5">
        <springForm:input path="weight" id="weight" cssClass="form-control"/>
    </div>
    <springForm:errors path="weight" cssClass="alert alert-danger col-sm-5"/>
</div>

<div class="form-group">
    <label class="control-label col-sm-2" for="annotation"><spring:message code="book.field.annotation"/>:</label>

    <div class="col-sm-5">
        <springForm:textarea path="annotation" id="annotation" cssClass="form-control" rows="5"/>
    </div>
    <springForm:errors path="annotation" cssClass="alert alert-danger col-sm-5"/>
</div>