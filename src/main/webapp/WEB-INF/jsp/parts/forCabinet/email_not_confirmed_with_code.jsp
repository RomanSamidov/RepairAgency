<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<fmt:message key="text.you_not_confirmed_your_email"/>
<br/>${ waitedCode }<br/>
<form class="text-center" name="confirm" method="POST" action=""  target="_top">
    <input type="hidden" name="command" value="confirm_Email" />
    <my:message key="${confirmationCodeError}" defaultvalue=""/>
    <label for="confirmationCode"><fmt:message key="text.enter_code" /></label><br/>
    <input type="text" name="confirmationCode" required autofocus autocomplete="off" placeholder="code"/><br/>
    <input type="submit" value="<fmt:message key="text.confirm"/>"/>
</form>