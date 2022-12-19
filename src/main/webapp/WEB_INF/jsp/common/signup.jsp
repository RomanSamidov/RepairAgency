<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB_INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB_INF/template/_head.jsp"/>
<body>

<c:import url="/WEB_INF/template/menu/_menu.jsp"/>

    <br/>
        ${errorLoginPassMessage}
    <br/>

    <form name="signupForm" method="POST" action="controller">
    ${errorLoginPassMessage}<br/>
        <input type="hidden" name="command" value="signup" />
            Login:<br/>
            ${errorEmptyLogin}<br/>
        <input type="text" name="login" value=""/>
            <br/>Password:<br/>
            ${errorEmptyPassword}<br/>
        <input type="password" name="password" value=""/>
        <input type="submit" value="signup"/>
    </form><hr/>


 <label for="browser" class="form-label">Choose your browser from the list:</label>
<input class="form-control" list="browsers" name="browser" id="browser">
<datalist id="browsers">
  <option value="Edge">
  <option value="Firefox">
  <option value="Chrome">
  <option value="Opera">
  <option value="Safari">
</datalist>

</body>
</html>