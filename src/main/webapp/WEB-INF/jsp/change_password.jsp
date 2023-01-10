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
    <form class="text-center" name="signupForm" method="POST" action="" autocomplete="on" target="_top">
        <input type="hidden" name="command" value="change_password" />

        <my:message key="${errorLoginPassMessage}" defaultvalue=""/>
        <my:message key="${errorEmptyLogin}" defaultvalue=""/>
        <label for="login"><fmt:message key="text.login" /></label><br/>
        <input type="text" name="login" value="${login}" required autofocus autocomplete="on" placeholder="login"/><br/>



        <my:message key="${errorEmptyEmail}" defaultvalue=""/>
        <label for="email"><fmt:message key="text.email"/></label><br/>
        <input type="email" name="email" value="${email}" required placeholder="email"/><br/>


        <c:if test="${ waitedCodePassword != null }" >


             <my:message key="${errorEmptyPassword}" defaultvalue=""/>
             <label for="password"><fmt:message key="text.password"/></label><br/>
             <input type="password" name="password" required placeholder="password"/><br/>

             <my:message key="${errorEmptyPasswordRepeat}" defaultvalue=""/>
             <label for="passwordRepeat"><fmt:message key="text.password_repeat"/></label><br/>
             <input type="password" name="passwordRepeat" required placeholder="password" /><br/>

            ${ waitedCodePassword }<br/>

                <input type="hidden" name="command" value="change_password" />
                <my:message key="${confirmationCodeError}" defaultvalue=""/>
                <label for="confirmationCodePassword"><fmt:message key="text.enter_code" /></label><br/>
                <input type="text" name="confirmationCodePassword" required autofocus autocomplete="off" placeholder="code"/><br/>

        </c:if>

        <my:message key="${errorRecaptchaMessage}" defaultvalue=""/><br/>
        <div align="center" class="g-recaptcha" data-sitekey="6LfD3asjAAAAADySW6TW3x1ZMqI4FpXSYEG2zR4N" ></div><br/>

        <my:message key="${errorLoginPassMessage}" defaultvalue=""/>
        <my:message key="${confirmationCodeError}" defaultvalue=""/>
        <input type="submit" value="<fmt:message key="text.change_password"/>"/>
    </form>
    <hr/>
</div>
</body>
</html>