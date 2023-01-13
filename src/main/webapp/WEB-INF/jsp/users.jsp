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
            <option value="0" ${roleUsers == null ? 'selected="selected"' : ''}> <fmt:message key="text.all"/></option>
        </c:if>
        <c:forEach var="role" items="${rolesUsers}">
            <option value="${role.toString}"  ${roleUsers.toString == role.toString ? 'selected="selected"' : ''}><fmt:message key="text.user.role.${role.ordinal}"  /></option>
        </c:forEach>

     </select>
    <input type="submit" value="<fmt:message key="text_select_role"/>"/>
    </form>


<form name="pages" method="POST" action="">
    <input type="hidden" name="command" value="users" />
     <input type="number" name="quantityUsers" value="${nowQuantityUsers}" min="1" max="500"/>
    <input type="submit" value="<fmt:message key="text.show_on_one_page"/>"/>
    </form>
<c:if test="${users.isEmpty() == true}" >
            Founded no users!
</c:if>


    <c:import url="/WEB-INF/jsp/parts/forUsers/show_users.jsp"/>
    <%--<c:import url="${_show_users_url}"/>--%>


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
</body></html>