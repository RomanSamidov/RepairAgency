<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form class="text-center" name="changeEmail" method="POST" action=""  target="_top">
    <input type="hidden" name="command" value="change_Email" />
    <my:message key="${errorEmptyEmail}" default_value=""/>
    <label for="email"><fmt:message key="text.change_email_to"/></label><br/>
    <input type="email" name="email" required maxlength="30" placeholder="<fmt:message key="text.email"/>"/><br/>
    <input type="submit" value="<fmt:message key="text.set_new_email"/>"/>
</form>