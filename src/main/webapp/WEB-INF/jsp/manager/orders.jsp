<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>
<div class="text-center" >

    <c:import url="/WEB-INF/jsp/parts/forOrders/set_filters.jsp"/>
    <%--<c:import url="${_show_orders_url}"/>--%>

    <c:import url="/WEB-INF/jsp/parts/forOrders/create_report.jsp"/>
    <%--<c:import url="${_show_orders_url}"/>--%>

    <my:quantitySelectorForTable table_name="Orders" command ="orders"/>

    <c:import url="/WEB-INF/jsp/parts/forOrders/show_orders.jsp"/>
    <%--<c:import url="${_show_orders_url}"/>--%>

    <my:message key="${error}" defaultvalue=""/>

    <my:pageControlPanelForTable table_name="Orders" command ="orders"/>

</div>
</body></html>