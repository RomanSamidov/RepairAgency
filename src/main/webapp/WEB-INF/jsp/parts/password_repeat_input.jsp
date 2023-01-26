<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<my:message key="${errorEmptyPasswordRepeat}" default_value=""/>
<label for="passwordRepeat"><fmt:message key="text.password_repeat"/></label><br/>
<input type="password" name="passwordRepeat" required autocomplete="off" maxlength="30" placeholder="<fmt:message key="text.password"/>" /><br/>