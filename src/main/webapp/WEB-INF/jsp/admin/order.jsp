<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/admin/_menu.jsp"/>
<div class="text-center" >

    <my:message key="${error}" default_value=""/>

    <c:import url="${_show_order_url}"/>

    <c:import url="${_craft_order_url}"/>

    <c:import url="${_client_order_url}"/>

    <c:import url="${_manager_order_url}"/>

    <c:import url="${_cancel_order_url}"/>

    <c:import url="/WEB-INF/jsp/admin/parts/forOrder/delete_order.jsp"/>



</div>
</body></html>