<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>

    <form name="loginForm" method="POST" action="">
    <my:message key="${errorLoginPassMessage}" defaultvalue=""/><br/>
    <input type="hidden" name="command" value="login" />
        <fmt:message key="text.login"/><br/>
        <my:message key="${errorEmptyLogin}" defaultvalue=""/><br/>
    <input type="text" name="login" value=""/><br/>
        <fmt:message key="text.password"/><br/>
        <my:message key="${errorEmptyPassword}" defaultvalue=""/><br/>
    <input type="password" name="password" value=""/>

    <br/>
        ${wrongAction}
    <br/>
    <input type="submit" value="<fmt:message key="text.login"/>"/>
    </form><hr/>

</body>
</html>