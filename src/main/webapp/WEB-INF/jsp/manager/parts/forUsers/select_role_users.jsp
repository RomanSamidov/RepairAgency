<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form name="pages" method="POST" action="">
    <input type="hidden" name="command" value="users" />
    <select name="roleUsers" autocomplete="off">
        <c:forEach var="role" items="${rolesUsers}">
            <option value="${role.toString}"  ${roleUsers.toString == role.toString ? 'selected="selected"' : ''}><fmt:message key="text.user.role.${role.ordinal}"  /></option>
        </c:forEach>
    </select>
    <input type="submit" value="<fmt:message key="text_select_role"/>"/>
</form>