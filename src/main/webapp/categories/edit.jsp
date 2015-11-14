<%--@elvariable id="category" type="com.zhytnik.library.entity.Category"--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Edit category</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>Edit Category</h1>
<form method="post" action="update">
    <label>Name<input type="text" name="name" maxlength="50" value="${category.name}"/></label>
    <br>
    <br>
    <label>Description<input type="text" name="description" maxlength="150" value="${category.description}"></label>
    <input type="hidden" name="id" value="${category.id}">
    <br>
    <br>
    <input type="submit" name="submit" value="Save">
</form>
</body>
</html>