<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <springForm:form method="POST" modelAttribute="user"
                     action="${pageContext.request.contextPath}/users">
        <table>
            <tr>
                <td><spring:message code="user.field.login"/></td>
                <td><springForm:input path="login" value=""/></td>
                <td><springForm:errors path="login" cssClass="error"/></td>
            </tr>
            <tr>
                <td><spring:message code="user.field.password"/></td>
                <td><springForm:password path="password" value=""/></td>
                <td><springForm:errors path="password" cssClass="error"/></td>
            </tr>
            <tr>
                <td><spring:message code="user.status"/></td>
                <td>
                    <label>
                        <select name="role">
                            <option value="USER"><spring:message code="role.user"/></option>
                            <option value="LIBRARIAN"><spring:message code="role.librarian"/></option>
                            <option value="ADMIN"><spring:message code="role.admin"/></option
                        </select>
                    </label>
                </td>
            </tr>
            <tr>
                <spring:message code="user.action.add" var="add"/>
                <td colspan="3"><input type="submit" value="${add}"></td>
            </tr>
        </table>
    </springForm:form>
</div>