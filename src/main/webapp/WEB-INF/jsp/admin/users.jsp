<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/admin/_menu.jsp"/>
<div class="text-center" >

    <%--<c:import url="/WEB-INF/jsp/admin/parts/forUsers/select_role_users.jsp"/>--%>
    <c:import url="${_select_role_users_url}"/>

<form name="pages" method="POST" action="">
    <input type="hidden" name="command" value="users" />
    <input type="number" name="quantityUsers" value="${nowQuantityUsers}" min="1" max="500"/>
    <input type="submit" value="<fmt:message key="text.show_on_one_page"/>"/>
</form>

    <%--<c:import url="/WEB-INF/jsp/parts/forUsers/show_users.jsp"/>--%>
    <c:import url="${_show_users_url}"/>


<my:message key="${error}" defaultvalue=""/>

<br/>

<table>
<tr>
<c:forEach var="page" items="${listPagesUsers}" varStatus="status">
        <form method="POST" action="">
            <input type="hidden" name="command" value="users" />
            <input type="hidden" name="skipUsers" value="${ page }" />
            <input type="submit" value="${ status.count }"  ${nowPageUsers+1==status.count?"disabled=\"disabled\"":""}/>
        </form>

    </c:forEach>
</tr>
</table>

</div>
</body></html>