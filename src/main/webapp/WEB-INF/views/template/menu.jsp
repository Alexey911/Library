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
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <a href="${contextPath}?locale=en"><spring:message code="language.en"/></a> |
    <a href="${contextPath}?locale=ru"><spring:message code="language.ru"/></a>
</ul>