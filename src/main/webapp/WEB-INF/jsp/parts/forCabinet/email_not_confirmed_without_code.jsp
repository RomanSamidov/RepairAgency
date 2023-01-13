<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<fmt:message key="text.you_not_confirmed_your_email"/>
<form class="text-center" name="confirm" method="POST" action=""  target="_top">
    <input type="hidden" name="command" value="confirm_Email" />
    <fmt:message key="text.to_confirm_your_email_press"/>
    <input type="submit" value="<fmt:message key="text.confirm"/>"/>
</form>