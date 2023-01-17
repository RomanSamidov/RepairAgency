<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form id="createOrderForm" method="POST" action="">
    <input type="hidden" name="command" value="admin_create_Order" /><br/>

    <fmt:message key="text.order_client_id"/><br/>
    <my:message key="${errorOrderClientIdMessage}" default_value=""/>
    <input type="number" name="clientId" value="0" min="1"/>

    <fmt:message key="text.order_text"/><br/>
    <my:message key="${errorOrderTextMessage}" default_value=""/>
    <textarea rows="5" cols="50" name="orderText" form="createOrderForm"><fmt:message key="text.enter_order_text"/></textarea>
    <br/>
    <input type="submit" value="<fmt:message key="text.create_order"/>"/>
</form><hr/>