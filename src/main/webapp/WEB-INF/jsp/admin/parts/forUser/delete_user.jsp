<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form method="POST" action="">
    <input type="hidden" name="command" value="delete_User" />
    <input type="hidden" name="goalIdUser" value="${ goalUser.id }" />
    <input type="submit" value="<fmt:message key="text.delete_user"/>"/>
</form>