<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form method="POST" action="">
    <input type="hidden" name="command" value="SET_ORDER_STATUS" />
    <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
    <input type="hidden" name="goalOrderStatus" value="5" />
    <input type="submit" value="<fmt:message key="text.take_to_progress"/>"/>
</form>

