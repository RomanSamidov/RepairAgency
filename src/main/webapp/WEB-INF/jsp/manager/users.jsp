<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/manager/_menu.jsp"/>
<div class="text-center" >

    <%--<c:import url="/WEB-INF/jsp/manager/parts/forUsers/select_role_users.jsp"/>--%>
    <c:import url="${_select_role_users_url}"/>

    <my:quantitySelectorForTable table_name="Users" command ="users"/>

    <%--<c:import url="/WEB-INF/jsp/parts/forUsers/show_users.jsp"/>--%>
    <c:import url="${_show_users_url}"/>

    <my:message key="${error}" defaultvalue=""/>

    <my:pageControlPanelForTable table_name="Users" command ="users"/>

</div>
</body></html>