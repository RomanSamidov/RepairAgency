<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB_INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB_INF/template/_head.jsp"/>
<body>

<c:import url="/WEB_INF/template/menu/_menu.jsp"/>

    <form name="loginForm" method="POST" action="controller">
    ${errorLoginPassMessage}<br/>
    <input type="hidden" name="command" value="login" />
        Login:<br/>
        ${errorEmptyLogin}<br/>
    <input type="text" name="login" value=""/>
        <br/>Password:<br/>
        ${errorEmptyPassword}<br/>
    <input type="password" name="password" value=""/>

    <br/>
        ${wrongAction}
    <br/>
        ${nullPage}
    <br/>
    <input type="submit" value="Log in"/>
    </form><hr/>

</body>
</html>