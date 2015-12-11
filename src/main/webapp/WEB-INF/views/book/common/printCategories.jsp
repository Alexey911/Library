<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="categories" type="java.util.List"--%>
<c:forEach var="category" items="${categories}">
    <c:choose>
        <%--@elvariable id="selected" type="java.util.List"--%>
        <c:when test="${not empty selected and selected.contains(category.id)}">
            <option value="${category.id}" selected>${category.name}</option>
        </c:when>
        <c:otherwise>
            <option value="${category.id}">${category.name}</option>
        </c:otherwise>
    </c:choose>
</c:forEach>