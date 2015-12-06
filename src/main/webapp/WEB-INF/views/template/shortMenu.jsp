<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<ul style="list-style:none;line-height:28px;">
    <a href="${pageContext.request.contextPath}?locale=en"><spring:message code="language.en"/></a> |
    <a href="${pageContext.request.contextPath}?locale=ru"><spring:message code="language.ru"/></a>
</ul>