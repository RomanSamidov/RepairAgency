<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<script src="https://www.google.com/recaptcha/api.js"></script>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>

 <div align="center">
    <form class="text-center" name="signupForm" method="POST" action="" autocomplete="on" target="_top">
        <input type="hidden" name="command" value="signup" />

        <label for="login"><fmt:message key="text.login" /></label><br/>
        <input type="text" name="login" required autofocus autocomplete="on" placeholder="login"/><br/>


        <label for="password"><fmt:message key="text.password"/></label><br/>
        <input type="password" name="password" required placeholder="password"/><br/>


        <label for="passwordRepeat"><fmt:message key="text.password_repeat"/></label><br/>
        <input type="password" name="passwordRepeat" required placeholder="password" /><br/>

        <label for="email"><fmt:message key="text.email"/></label><br/>
        <input type="email" name="email" required placeholder="email"/><br/>

        <my:message key="${errorRecaptchaMessage}" defaultvalue=""/><br/>
        <div align="center" class="g-recaptcha" data-sitekey="6LfD3asjAAAAADySW6TW3x1ZMqI4FpXSYEG2zR4N" ></div><br/>

        <my:message key="${errorEmpty}" defaultvalue=""/>
        <input type="submit" value="<fmt:message key="text.signup"/>"/>
    </form>
    <hr/>
</div>
</body>
</html>