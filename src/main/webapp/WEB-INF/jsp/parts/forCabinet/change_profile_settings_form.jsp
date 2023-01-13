<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form class="text-center" name="allowLetters" method="POST" action=""  target="_top">
    <input type="hidden" name="command" value="change_profile_settings" />
    <input type="checkbox" name="newIsUserAllowLetters" value="true" default="false"   ${isUserAllowLetters ? 'checked' : ''}>
    <label for="newIsUserAllowLetters"><fmt:message key="text.i_agree_to_receive_letters"/></label><br>
    <input type="submit" value="<fmt:message key="text.update"/>"/>
</form>