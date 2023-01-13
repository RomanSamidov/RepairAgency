<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<table class="table table-striped table-bordered table-sm table-th">
    <caption><fmt:message key="text.list_of_orders"/></caption>
    <tr>
        <th><fmt:message key="text.order.id"/>  </th>
        <th><fmt:message key="text.order.user_id"/>  </th>
        <th><fmt:message key="text.order.craftsman_id"/>  </th>
        <th><fmt:message key="text.order.creation_date"/>  </th>
        <th><fmt:message key="text.order.text"/>  </th>
        <th><fmt:message key="text.order.price"/>  </th>
        <th><fmt:message key="text.order.finish_date"/>  </th>
        <th><fmt:message key="text.order.status"/>  </th>
        <th><fmt:message key="text.order.feedback_date"/>  </th>
        <th><fmt:message key="text.order.feedback_text"/>  </th>
        <th><fmt:message key="text.order.feedback_mark"/>  </th>
        <th> </th>
    </tr>
    <c:forEach var="order" items="${orders}" varStatus="status">
        <tr>
            <td>${ order.id }</td>
            <td>${ order.user_id }</td>
            <td>${ order.craftsman_id==0?"":order.craftsman_id }</td>
            <td>${ order.creation_date }</td>
            <td>${ order.text }</td>
            <td>${ order.price }</td>
            <td>${ order.finish_date }</td>
            <td><fmt:message key="text.order.status.${order.status}"/> </td>
            <td>${ order.feedback_date }</td>
            <td>${ order.feedback_text }</td>
            <td>${ order.feedback_mark==0?"":order.feedback_mark}</td>
            <td>
                <a href="/RepairAgency/controller/order?id=${order.id}" class = "btn px-2  "><fmt:message key="text.go_to"/> </a>
            </td>
        </tr>
    </c:forEach>
</table>