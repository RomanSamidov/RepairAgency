<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%--<c:if test="${goalOrder.status!=6 && goalOrder.status!=4}" >--%>

<form method="POST" action="">
    <input type="hidden" name="command" value="CANCEL_ORDER" />
    <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
    <input type="hidden" name="goalOrderStatus" value="4" />
    <input type="submit" value="<fmt:message key="text.cancel_order"/>"/>
</form>
