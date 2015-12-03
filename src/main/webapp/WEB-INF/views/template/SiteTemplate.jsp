<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="library.name"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/screen.css" type="text/css"
          media="screen, projection"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/print.css" type="text/css"
          media="print"/>
    <!--[if IE]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ie.css" type="text/css"
          media="screen, projection"/>
    <![endif]-->
    <style>
        body {
            margin-top: 20px;
            margin-bottom: 20px;
            background-color: #DFDFDF;
        }
    </style>
    <style type="text/css">
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
    </style>
    <style>
        .errorInPass {
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border: 1px solid #ebccd1;
        }

        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border: 1px solid #bce8f1;
        }

        #login-box {
            font-size: small;
            width: 150px;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
        }

        h2 {
            font-size: small;
        }

        input {
            font-size: small;
            max-width: 60px;
        }
    </style>
</head>
<body>
<div class="container" style="border: #C1C1C1 solid 1px; border-radius:10px;">
    <!-- Header -->
    <tiles:insertAttribute name="header"/>
    <!-- Menu Page -->
    <div class="span-5" style="height:400px;background-color:#FCFCFC;">
        <tiles:insertAttribute name="menu"/>
    </div>
    <!-- Body Page -->
    <div class="span-19 last">
        <tiles:insertAttribute name="body"/>
    </div>
    <!-- Footer Page -->
    <tiles:insertAttribute name="footer"/>
</div>
</body>
</html>