<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<table class="table table-striped table-bordered table-sm table-th">
    <caption><fmt:message key="title.user"/></caption>
    <tr>
        <th><fmt:message key="text.user.id"/>  </th>
        <th><fmt:message key="text.user.role"/>  </th>
        <th><fmt:message key="text.user.login"/>  </th>
        <th><fmt:message key="text.user.email"/>  </th>
        <th><fmt:message key="text.user.account"/>  </th>
    </tr>
    <tr>
        <td>${ goalUser.id }</td>
        <td><fmt:message key="${goalUser.role}"/></td>
        <td>${ goalUser.login }</td>
        <td>${ goalUser.email }</td>
        <td>${ goalUser.account }</td>
    </tr>
</table>