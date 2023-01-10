<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<script src="https://www.google.com/recaptcha/api.js"></script>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
    <fmt:setLocale value="${language}"/>
    <fmt:setBundle basename="LocalStrings"/>
    <c:import url="/WEB-INF/template/menu/_menu.jsp"/>

     <div align="center">
        <form class="text-center" name="loginForm" method="POST" action=""  autocomplete="on"  target="_top">
            <input type="hidden" name="command" value="login" />

            <my:message key="${errorEmptyLogin}" defaultvalue=""/>
            <label for="login"><fmt:message key="text.login" /></label><br/>
            <input type="text" name="login" required autofocus autocomplete="on" placeholder="<fmt:message key="text.login" />"/><br/>

            <my:message key="${errorEmptyPassword}" defaultvalue=""/>
            <label for="password"><fmt:message key="text.password"/></label><br/>
            <input type="password" name="password" required placeholder="<fmt:message key="text.password"/>"/><br/>

            <my:message key="${errorRecaptchaMessage}" defaultvalue=""/><br/>
            <div align="center" class="g-recaptcha" data-sitekey="6LfD3asjAAAAADySW6TW3x1ZMqI4FpXSYEG2zR4N"></div><br/>

            <my:message key="${errorLoginPassMessage}" defaultvalue=""/>
            <input type="submit" value="<fmt:message key="text.sign_in"/>"/>
        </form>
        <a href="/RepairAgency/controller/change_password" class = "btn px-2  "><fmt:message key="text.to_change_your_password_press"/></a>
        <hr/>
     </div>

</body>
</html>