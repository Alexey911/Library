<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <%--@elvariable id="errMsg" type="java.lang.String"--%>
        <p>${errMsg}</p>
        <a href="#" onclick="history.back(); return false;"><spring:message code="category.action.back"/></a>
    </tiles:putAttribute>
</tiles:insertDefinition>