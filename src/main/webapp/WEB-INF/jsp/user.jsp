<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>
<div class="text-center" >

<table class="table table-striped table-bordered table-sm table-th">
              <caption>User</caption>
        <tr>
            <th> id </th>
            <th> role </th>
            <th> login </th>
            <th> email </th>
            <th> account </th>
            <th>  </th>
        </tr>
        <tr>
        <td><c:out value="${ goalUser.id }" /></td>
        <td><c:out value="${ goalUser.role }" /></td>
        <td><c:out value="${ goalUser.login }" /></td>
        <td><c:out value="${ goalUser.email }" /></td>
        <td><c:out value="${ goalUser.account }" /></td>
 </tr>
</table>

<c:if test="${goalUser.role == 'Client'}" >
 <form method="POST" action="">
            <input type="hidden" name="command" value="user" />
            <input type="hidden" name="goalIdUser" value="${ goalUser.id }" />
            <input type="number" name="addToAccount" value=""/><br/>
            <input type="submit" value="addToAccount"/>
        </form>
 </c:if>
            <c:if test="${userRole == 'Admin' && userId != goalUser.id}" >
                <form method="POST" action="">
                    <input type="hidden" name="command" value="deleteUser" />
                    <input type="hidden" name="goalIdUser" value="${ goalUser.id }" />
                    <input type="submit" value="Delete user"/>
                </form>
            </c:if>

</div>
</body></html>