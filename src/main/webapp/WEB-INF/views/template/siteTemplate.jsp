<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <c:set var="title"><tiles:insertAttribute name="title"/></c:set>
    <title><spring:message code="${title}"/></title>
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/styles.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/bootstrap.min.js" type="javascript"></script>
    <script src="${contextPath}/resources/js/scripts.js" type="javascript"></script>
</head>
<body>
<%-- Begin Header --%>
<header class="navbar navbar-default navbar-static-top" role="banner">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button"
                    data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <sec:authorize access="isAuthenticated()">
                <%@ include file="userInfo.jsp" %>
            </sec:authorize>
        </div>
        <tiles:insertAttribute name="header"/>
    </div>
</header>

<!-- Begin Body -->
<div class="container">
    <div class="row">
        <div class="col-md-3" id="leftCol">
            <div class="well">
                <ul class="nav nav-stacked" id="sidebar">
                    <sec:authorize access="isAnonymous()">
                        <tiles:insertAttribute name="anonymous"/>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <tiles:insertAttribute name="menu"/>
                    </sec:authorize>
                </ul>
            </div>
        </div>
        <div class="col-md-9">
            <tiles:insertAttribute name="body"/>
        </div>
    </div>
</div>
<footer class="footer text-center">
    <hr>
    <tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>