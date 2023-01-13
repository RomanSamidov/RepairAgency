<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/manager/_menu.jsp"/>
<div class="text-center" >

    <my:message key="${error}" defaultvalue=""/>

    <%--<c:import url="/WEB-INF/jsp/parts/forUser/show_user.jsp"/>--%>
    <c:import url="${_show_user_url}"/>

    <%--<c:import url="/WEB-INF/jsp/manager/parts/forUser/add_to_account.jsp"/>--%>
    <c:import url="${_add_to_account_url}"/>

</div>
</body></html>