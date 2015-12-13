<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--@elvariable id="books" type="java.util.List"--%>
<%--@elvariable id="book" type="com.zhytnik.library.domain.Book"--%>
<table class="table table-striped">
    <thead>
        <tr>
            <th><spring:message code="book.field.name"/></th>
            <th><spring:message code="book.field.authors"/></th>
            <th><spring:message code="book.field.page.count"/></th>
            <th><spring:message code="book.field.publisher"/></th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><a href="${contextPath}/books/${book.id}">${book.name}</a></td>
            <td>${book.authors}</td>
            <td>${book.pageCount}</td>
            <td><a href="${contextPath}/publishers/${book.publisher.id}">${book.publisher.name}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>