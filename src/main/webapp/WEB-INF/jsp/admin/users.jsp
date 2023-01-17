<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/admin/_menu.jsp"/>
<div class="text-center" >

    <c:import url="/WEB-INF/jsp/admin/parts/forUsers/select_role_users.jsp"/>

    <my:quantitySelectorForTable table_name="Users" command ="users"/>

    <c:import url="/WEB-INF/jsp/parts/forUsers/show_users.jsp"/>

    <my:message key="${error}" default_value=""/>

    <my:pageControlPanelForTable table_name="Users" command ="users"/>

</div>
</body></html>