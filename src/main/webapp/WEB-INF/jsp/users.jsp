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




    <my:quantitySelectorForTable table_name="Users" command ="users"/>

    <c:import url="/WEB-INF/jsp/parts/forUsers/show_users.jsp"/>
    <%--<c:import url="${_show_users_url}"/>--%>

    <my:pageControlPanelForTable table_name="Users" command ="users"/>



</div>
</body></html>