<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Publishers</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jQuery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/publishers/script.js"></script>
</head>
<body>
<p>Publishers</p>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Address</th>
    </tr>
    <%--@elvariable id="publishers" type="java.util.Set"--%>
    <c:forEach items="${publishers}" var="publisher">
        <tr class="field">
            <td class="name"><c:out value="${publisher.name}"/></td>
            <td class="address"><c:out value="${publisher.address}"/></td>
            <td hidden class="id"><c:out value="${publisher.id}"/></td>
        </tr>
    </c:forEach>
</table>
<br>
<form method="post" action="${pageContext.request.contextPath}/publishers">
    <label>Name<input type="text" name="name" maxlength="40" id="name"/></label>
    <br>
    <label>Address<input type="text" name="address" maxlength="100" id="address"></label>
    <br>
    <br>
    <input type="hidden" name="id" value="" id="fieldForId">
    <input type="radio" name="action" value="add" title="Add">Add<br>
    <input type="radio" name="action" value="delete" title="Delete">Delete<br>
    <input type="radio" name="action" value="update" title="Update">Update<br>
    <br>
    <input type="submit" value="Perform">
</form>
</body>
</html>