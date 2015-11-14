<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Add new category</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>Add Category</h1>
<form method="post" action="save">
    <label>Name<input type="text" name="name" maxlength="50"/></label>
    <br>
    <br>
    <label>Description<input type="text" name="description" maxlength="150"></label>
    <br>
    <input type="submit" name="submit" value="Add">
</form>
</body>
</html>