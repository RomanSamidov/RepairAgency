<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%-- <c:if test="${goalOrder.status==1}" >--%>

<form method="POST" action="">
    <input type="hidden" name="command" value="SET_CRAFTSMAN_AND_PRICE" />
    <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
    <label for="goalOrderCraftsman_id"><fmt:message key="text.craftsman"/></label>

    <select name="goalOrderCraftsman_id" required>
    <c:forEach var="craftsman" items="${craftsmen}">
        <option value="${craftsman.id}"  ${craftsman.id==goalOrder.craftsman_id?"selected=\"selected\"":""} >${craftsman.id} ${craftsman.login}</option>
    </c:forEach>
    </select>

    <label for="goalOrderCraftsman_id"><fmt:message key="text.order.price"/></label>
    <input type="number" name="goalOrderPrice" value="${goalOrder.price}" required/>
    <input type="submit" value="<fmt:message key="text.change"/>"/>
</form>
