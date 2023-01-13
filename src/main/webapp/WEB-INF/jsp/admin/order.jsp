<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/admin/_menu.jsp"/>
<div class="text-center" >

    <my:message key="${error}" defaultvalue=""/>

    <%--<c:import url="/WEB-INF/jsp/parts/forOrder/show_order.jsp"/>--%>
    <c:import url="${_show_order_url}"/>

    <%--<c:import url="WEB-INF/jsp/craftsman/parts/forOrder/take_order.jsp"/>--%>
    <%--<c:import url="WEB-INF/jsp/craftsman/parts/forOrder/complete_order.jsp"/>--%>
    <c:import url="${_craft_order_url}"/>=

    <%--<c:import url="WEB-INF/jsp/client/parts/forOrder/set_feedback.jsp"/>--%>
    <%--<c:import url="WEB-INF/jsp/client/parts/forOrder/pay_order.jsp"/>--%>
    <c:import url="${_user_order_url}"/>

    <%--<c:import url="/WEB-INF/jsp/parts/forOrder/cancel_order.jsp"/>--%>
    <c:import url="${_cancel_order_url}"/>

</div>
</body></html>