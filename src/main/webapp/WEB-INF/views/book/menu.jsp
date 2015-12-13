<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<li>
    <a href="${contextPath}/books"><spring:message code="book.action.show.all"/></a>
</li>
<li>
    <a href="${contextPath}/books?page=search&filter=category"><spring:message code="book.search.by.category"/></a>
</li>
<li>
    <a href="${contextPath}/books?page=search&filter=publisher"><spring:message code="book.search.by.publisher"/></a>
</li>
<sec:authorize access="hasAnyRole('ROLE_LIBRARIAN', 'ROLE_ADMIN')">
    <li>
        <a href="${contextPath}/books?page=add"><spring:message code="book.action.add"/></a>
    </li>
</sec:authorize>
