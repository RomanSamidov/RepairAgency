<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<fmt:message key="text.you_not_confirmed_your_email"/>
<br/>${ waitedCode }<br/>
<form class="text-center" name="confirm" method="POST" action=""  target="_top">
    <input type="hidden" name="command" value="confirm_Email" />
    <my:message key="${confirmationCodeError}" default_value=""/>
    <label for="confirmationCode"><fmt:message key="text.enter_code" /></label><br/>
    <input type="text" name="confirmationCode" autofocus autocomplete="off" placeholder="code"/><br/>

     <label for="sendCodeAgain"><fmt:message key="text.send_code_again" /></label>
    <input type="checkbox" name="sendCodeAgain" value="true" default="false"><br/>

    <input type="submit" value="<fmt:message key="text.confirm"/>"/>
</form>