<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB_INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB_INF/template/_head.jsp"/>
<body>

<c:import url="/WEB_INF/template/menu/_menu.jsp"/>

<h3>Welcome</h3>
<hr/> ${user}, hello!
<hr/>



<form name="logoutForm" method="POST" action="controller">
    <input type="hidden" name="command" value="logout" />
    <input type="submit" value="Log out"/>
    </form>

</body></html>