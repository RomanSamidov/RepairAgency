<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<table class="table table-striped table-bordered table-sm table-th">
    <caption><fmt:message key="text.list_of_users"/></caption>
    <tr>
        <th><fmt:message key="text.user.id"/>  </th>
        <th><fmt:message key="text.user.role"/>  </th>
        <th><fmt:message key="text.user.login"/>  </th>
        <th><fmt:message key="text.user.account"/>  </th>
        <th>  </th>
    </tr>
    <c:forEach var="user" items="${users}" varStatus="status">
        <tr>
            <td>${ user.id }</td>
            <td><fmt:message key="${user.role}"/></td>
            <td>${ user.login }</td>
            <td>${ user.account }</td>
            <td>
                <a href="/RepairAgency/controller/user?id=${user.id}" class = "btn px-2  "><fmt:message key="text.go_to"/></a>
            </td>
        </tr>
    </c:forEach>
</table>