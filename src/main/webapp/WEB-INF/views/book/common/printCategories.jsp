<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="categories" type="java.util.List"--%>
<c:forEach var="publisher" items="${categories}">
    <c:choose>
        <%--@elvariable id="selected" type="java.util.List"--%>
        <c:when test="${not empty selected and selected.contains(publisher.id)}">
            <option value="${publisher.id}" selected>${publisher.name}</option>
        </c:when>
        <c:otherwise>
            <option value="${publisher.id}">${publisher.name}</option>
        </c:otherwise>
    </c:choose>
</c:forEach>