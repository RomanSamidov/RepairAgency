<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form method="POST" action="">
    <input type="hidden" name="command" value="delete_order" />
    <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
    <input type="submit" value="<fmt:message key="text.delete_order"/>"/>
</form>