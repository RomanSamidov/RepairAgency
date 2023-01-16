<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<my:message key="${errorEmptyEmail}" defaultvalue=""/>
<label for="email"><fmt:message key="text.email"/></label><br/>
<input type="email" name="email" required value="${email}" maxlength="30" placeholder="<fmt:message key="text.email"/>"/><br/>