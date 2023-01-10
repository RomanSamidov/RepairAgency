<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>
<div class="text-center" >

<table class="table table-striped table-bordered table-sm table-th">
              <caption><fmt:message key="title.user"/></caption>
        <tr>
            <th><fmt:message key="text.user.id"/>  </th>
            <th><fmt:message key="text.user.role"/>  </th>
            <th><fmt:message key="text.user.login"/>  </th>
            <th><fmt:message key="text.user.email"/>  </th>
            <c:if test="${goalUser.role == 'Client'}" >
            <th><fmt:message key="text.user.account"/>  </th>
            </c:if>
        </tr>
        <tr>
        <td>${ goalUser.id }</td>
        <td><fmt:message key="${goalUser.role}"/></td>
        <td>${ goalUser.login }</td>
        <td>${ goalUser.email }</td>
        <c:if test="${goalUser.role == 'Client'}" >
        <td>${ goalUser.account }</td>
        </c:if>
 </tr>
</table>

<c:if test="${goalUser.role == 'Client'}" >
 <form method="POST" action="">
            <input type="hidden" name="command" value="add_To_User_Account" />
            <input type="hidden" name="goalIdUser" value="${ goalUser.id }" />
            <input type="number" name="addToAccount" value=""/>
            <input type="submit" value="<fmt:message key="text.add_to_account"/>"/>
        </form>
 </c:if>
            <c:if test="${userRole == 'Admin' && userId != goalUser.id}" >
                <form method="POST" action="">
                    <input type="hidden" name="command" value="delete_User" />
                    <input type="hidden" name="goalIdUser" value="${ goalUser.id }" />
                    <input type="submit" value="<fmt:message key="text.delete_user"/>"/>
                </form>
            </c:if>

</div>
</body></html>