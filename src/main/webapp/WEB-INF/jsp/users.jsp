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
     <select name="roleUsers" autocomplete="off">
        <c:if test="${userRole == 'Admin'}" >
            <option value="0" ${roleUsers == null ? 'selected="selected"' : ''}>All</option>
        </c:if>
        <c:forEach var="role" items="${rolesUsers}">
            <option value="${role.toString}"  ${roleUsers.toString == role.toString ? 'selected="selected"' : ''}><fmt:message key="text.user.role.${role.ordinal}"  /></option>
        </c:forEach>

     </select>
    <input type="submit" value="select role"/>
    </form>


<form name="pages" method="POST" action="">
    <input type="hidden" name="command" value="users" />
     <input type="number" name="quantityUsers" value="${nowQuantityUsers}"/>
    <input type="submit" value="<fmt:message key="text.show_on_one_page"/>"/>
    </form>
<c:if test="${users.isEmpty() == true}" >
            Founded no users!
</c:if>
<table class="table table-striped table-bordered table-sm table-th">
              <caption>List of users</caption>
        <tr>
            <th> id </th>
            <th> role </th>
            <th> login </th>
            <th> account </th>
            <th>  </th>
        </tr>
    <c:forEach var="user" items="${users}" varStatus="status">
        <tr>
        <td><c:out value="${ user.id }" /></td>
        <td><c:out value="${ user.role }" /></td>
        <td><c:out value="${ user.login }" /></td>
        <td><c:out value="${ user.account }" /></td>
        <td>
        <a href="/RepairAgency/controller/user?id=${user.id}" class = "btn px-2  ">ch</a>
        </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${users.isEmpty() == true}" >
            Founded no users!
</c:if>

<br/>

<table>
<tr>
<c:forEach var="page" items="${listPagesUsers}" varStatus="status">
        <form method="POST" action="">
            <input type="hidden" name="command" value="users" />
            <input type="hidden" name="skipUsers" value="${ page }" />
            <input type="submit" value="${ status.count }"  ${nowPageUsers+1==status.count?"disabled=\"disabled\"":""}/>
        </form>

    </c:forEach>
</tr>
</table>
</div>
${nowPageUsers}
</body></html>