<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<label for="statusOrders"> <fmt:message key="text.statuses"/></label>
<select name="statusOrders" size="2" multiple>
    <option value="0"><fmt:message key="text.all"/></option>
    <c:forEach var="orStatus" items="${orderStatuses}">
        <option value="${orStatus.ordinal}">${orStatus.ordinal} <fmt:message key="text.order.status.${orStatus.ordinal}"/></option>
    </c:forEach>
</select>

<label for="sortTypeOrders"><fmt:message key="text.sort_by"/></label>
<select name="sortTypeOrders">
    <c:forEach var="sortType" items="${sortTypesOrders}">
        <option value="${sortType.toString}"  ${sortType.toString==sortTypeOrders?"selected=\"selected\"":""} ><fmt:message key="text.sort_by.${sortType.toString}"/></option>
    </c:forEach>
</select>