<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form name="pages" method="POST" action="">
    <input type="hidden" name="command" value="orders" />

    <label for="craftsmanIdOrders"><fmt:message key="text.craftsmen"/></label>
    <select name="craftsmanIdOrders" size="2" multiple>
        <option value="0"><fmt:message key="text.all"/></option>
        <c:forEach var="craftsman" items="${craftsmen}">
           <option value="${craftsman.id}">${craftsman.id} ${craftsman.login}</option>
        </c:forEach>
    </select>

    <c:import url="/WEB-INF/jsp/parts/forOrders/inner_set_filters.jsp"/>

    <input type="submit" value="<fmt:message key="text.select"/>"/>
</form>