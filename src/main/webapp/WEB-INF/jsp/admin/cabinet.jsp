<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
    <c:import url="/WEB-INF/template/menu/admin/_menu.jsp"/>

    <div class="text-center" >
        <c:import url="/WEB-INF/jsp/parts/forCabinet/cabinet_text.jsp"/>

        <c:import url="/WEB-INF/jsp/parts/forCabinet/change_email_form.jsp"/>

        <c:import url="/WEB-INF/jsp/parts/forCabinet/change_profile_settings_form.jsp"/>

        <c:import url="${_email_confirmed_url}"/>

    </div>

</body></html>