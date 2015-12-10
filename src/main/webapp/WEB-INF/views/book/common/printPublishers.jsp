<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="publishers" type="java.util.List"--%>
<c:forEach var="publisher" items="${publishers}">
    <c:choose>
        <c:when test="${book.publisher.id eq publisher.id}">
            <option value="${publisher.id}" label="${publisher.name}" selected></option>
        </c:when>
        <c:otherwise>
            <option value="${publisher.id}" label="${publisher.name}"></option>
        </c:otherwise>
    </c:choose>
</c:forEach>