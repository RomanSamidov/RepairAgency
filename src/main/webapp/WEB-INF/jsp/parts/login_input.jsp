<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<my:message key="${errorLoginPassMessage}" defaultvalue=""/>
<my:message key="${errorEmptyLogin}" defaultvalue=""/>
<label for="login"><fmt:message key="text.login" /></label><br/>
<input type="text" name="login" value="${login}" required maxlength="30" autofocus autocomplete="on" placeholder="<fmt:message key="text.login" />"/><br/>