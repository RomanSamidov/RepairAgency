<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form name="pages" method="POST" action="">
    <input type="hidden" name="command" value="orders" />

    <c:import url="/WEB-INF/jsp/parts/forOrders/inner_set_filters.jsp"/>

    <input type="submit" value="<fmt:message key="text.select"/>"/>
</form>
