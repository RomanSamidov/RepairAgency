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


    <form class="text-center" name="loginForm" method="POST" action="">
    <my:message key="${errorLoginPassMessage}" defaultvalue=""/>
    <input type="hidden" name="command" value="login" />
        <fmt:message key="text.login"/><br/>
        <my:message key="${errorEmptyLogin}" defaultvalue=""/>
    <input type="text" name="login"  pattern="[a-Z]{4-8}" value=""/><br/>
        <fmt:message key="text.password"/><br/>
        <my:message key="${errorEmptyPassword}" defaultvalue=""/>
    <input type="password" name="password" value=""/>
    <my:message key="${errorRecaptchaMessage}" defaultvalue=""/>
<div class="g-recaptcha" data-sitekey="6LfD3asjAAAAADySW6TW3x1ZMqI4FpXSYEG2zR4N"></div>
    <input type="submit" value="<fmt:message key="text.login"/>"/>
    </form>


</body>
</html>