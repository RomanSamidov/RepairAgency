<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
    <c:import url="${_menu_url}"/>

 <div align="center">
    <form class="text-center" name="changePasswordForm" method="POST" action="" autocomplete="on" target="_top">

        <c:import url="/WEB-INF/jsp/parts/login_input.jsp"/>

        <c:import url="/WEB-INF/jsp/parts/email_input.jsp"/>

        <input type="hidden" name="command" value="change_password" />

        <c:import url="${_part_of_change_password_url}"/>

        <c:import url="/WEB-INF/jsp/parts/recaptcha_input.jsp"/>

        <my:message key="${errorLoginPassMessage}" default_value=""/>
        <my:message key="${confirmationCodeError}" default_value=""/>
        <input type="submit" value="<fmt:message key="text.change_password"/>"/>
    </form>
    <hr/>
</div>
</body>
</html>