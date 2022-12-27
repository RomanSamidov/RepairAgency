<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>

<c:import url="/WEB-INF/template/menu/_menu.jsp"/>
<div class="text-center" >

<form name="pages" method="POST" action="">
    <input type="hidden" name="command" value="users" />
     <select name="roleUsers">
       <option value="Client">Client</option>
       <option value="Craftsman">Craftsman</option>
     </select>
    <input type="submit" value="select role"/>
    </form>


<form name="pages" method="POST" action="">
    <input type="hidden" name="command" value="users" />
     <input type="text" name="quantityUsers" value="5"/>
    <input type="submit" value="<fmt:message key="text.show_on_one_page"/>"/>
    </form>
<table class="table table-striped table-bordered table-sm table-th">
              <caption>List of users</caption>
        <tr>
            <th> id </th>
            <th> role </th>
            <th> login </th>
            <th> account </th>
        </tr>
    <c:forEach var="user" items="${users}" varStatus="status">
        <tr>
        <td><c:out value="${ user.id }" /></td>
        <td><c:out value="${ user.role }" /></td>
        <td><c:out value="${ user.login }" /></td>
        <td><c:out value="${ user.account }" /></td>
        <td> <form method="POST" action="">
                        <input type="hidden" name="command" value="user" />
                        <input type="hidden" name="goalIdUser" value="${ user.id }" />
                        <input type="submit" value="ch"/>
             </form></td>
        </tr>
    </c:forEach>
</table>

<br/>

<table>
<tr>
<c:forEach var="page" items="${listPagesUsers}" varStatus="status">
        <form method="POST" action="">
            <input type="hidden" name="command" value="users" />
            <input type="hidden" name="skipUsers" value="${ page }" />
            <input type="submit" value="${ status.count }"/>
        </form>

    </c:forEach>
</tr>
</table>
</div>
</body></html>