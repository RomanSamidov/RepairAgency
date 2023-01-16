<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form method="POST" action="">
    <input type="hidden" name="command" value="SET_FEEDBACK_FOR_ORDER" />
    <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
    <label for="goalOrderFeedback_text"><fmt:message key="text.order.feedback_text"/></label>
    <input type="text" name="goalOrderFeedback_text" value=""/><br/>
    <label for="goalOrderFeedback_mark"><fmt:message key="text.order.feedback_mark"/></label>
    <input type="number" name="goalOrderFeedback_mark" value=""/><br/>
    <input type="submit" value="<fmt:message key="text.change"/>"/>
</form>