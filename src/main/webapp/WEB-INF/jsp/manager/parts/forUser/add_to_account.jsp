<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form method="POST" action="">
    <input type="hidden" name="command" value="add_To_User_Account" />
    <input type="hidden" name="goalIdUser" value="${ goalUser.id }" />
    <input type="number" name="addToAccount" value=""/>
    <input type="submit" value="<fmt:message key="text.add_to_account"/>"/>
</form>