<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<ul style="list-style:none;line-height:28px;">
    <sec:authorize access="isAnonymous()">
        <%@ include file="enter.jsp" %>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <%@ include file="userInfo.jsp" %>
        <%@ include file="info.jsp" %>
    </sec:authorize>
    <a href="${pageContext.request.contextPath}?locale=en">English </a> |
    <a href="${pageContext.request.contextPath}?locale=ru">Russian</a>
</ul>