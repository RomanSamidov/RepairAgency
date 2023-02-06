<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<table class="table table-striped table-bordered table-sm table-th">
    <caption><fmt:message key="title.order"/></caption>
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
    </tr>
    <tr>
        <td>${ goalOrder.id }</td>
        <td>${ goalOrder.user_id }</td>
        <td>${ goalOrder.craftsman_id==0?"":goalOrder.craftsman_id }</td>
        <td>${ goalOrder.creation_date }</td>
        <td>${ goalOrder.text }</td>
        <td>${ goalOrder.price }</td>
        <td>${ goalOrder.finish_date }</td>
        <td><fmt:message key="text.order.status.${goalOrder.status}"/> </td>
        <td>${ goalOrder.feedback_date }</td>
        <td>${ goalOrder.feedback_text }</td>
        <td>${ goalOrder.feedback_mark==0?"":goalOrder.feedback_mark }</td>
    </tr>
</table>