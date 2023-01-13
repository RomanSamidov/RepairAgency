<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<my:message key="${errorEmptyPassword}" defaultvalue=""/>
<label for="password"><fmt:message key="text.password"/></label><br/>
<input type="password" name="password" required maxlength="30" placeholder="<fmt:message key="text.password"/>"/><br/>