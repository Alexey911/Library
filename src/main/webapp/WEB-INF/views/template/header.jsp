<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="span-24">
    <img src="${pageContext.request.contextPath}/resources/images/header.png"
         width="950" style="padding-top:10px;"/>
    <sec:authorize access="isAuthenticated()">
        <sec:authentication var="principal" property="principal"/>
        <a href="${pageContext.request.contextPath}/users?action=showMe"><c:out value="${principal.username}"/></a>
        <!-- csrt for log out-->
        <c:url value="/j_spring_security_logout" var="logoutUrl"/>
        <form:form action="${logoutUrl}" method="post" id="logoutForm"/>
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>
        <a href="javascript:formSubmit()"> Logout</a>
    </sec:authorize>
</div>