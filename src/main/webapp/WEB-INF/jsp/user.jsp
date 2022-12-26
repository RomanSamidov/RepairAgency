<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>

<table>
        <tr>
            <td> id </td>
            <td> role </td>
            <td> login </td>
            <td> account </td>
        </tr>
        <tr>
        <td><c:out value="${ goalUser.id }" /></td>
        <td><c:out value="${ goalUser.role }" /></td>
        <td><c:out value="${ goalUser.login }" /></td>
        <td><c:out value="${ goalUser.account }" /></td>
 </tr>
</table>

 <form method="POST" action="">
            <input type="hidden" name="command" value="user" />
            <input type="hidden" name="goalIdUser" value="${ goalUser.id }" />
            <input type="text" name="addToAccount" value=""/><br/>
            <input type="submit" value="addToAccount"/>
        </form>


</body></html>