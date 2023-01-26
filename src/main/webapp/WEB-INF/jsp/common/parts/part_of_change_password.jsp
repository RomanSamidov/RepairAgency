 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
 <%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

    <c:import url="/WEB-INF/jsp/parts/password_input.jsp"/>

    <c:import url="/WEB-INF/jsp/parts/password_repeat_input.jsp"/>

    ${waitedCodePassword}<br/>

    <my:message key="${confirmationCodeError}" default_value=""/>
    <label for="confirmationCodePassword"><fmt:message key="text.enter_code" /></label><br/>
    <input type="text" name="confirmationCodePassword" required autofocus autocomplete="off" placeholder="<fmt:message key="text.code"/>"/><br/>

    <label for="sendCodeAgain"><fmt:message key="text.send_code_again" /></label>
    <input type="checkbox" name="sendCodeAgain" value="true" default="false" >