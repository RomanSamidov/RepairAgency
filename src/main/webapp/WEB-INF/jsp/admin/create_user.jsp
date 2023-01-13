<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/admin/_menu.jsp"/>

 <div align="center">
    <form class="text-center" name="createUserForm" method="POST" action="" autocomplete="on" target="_top">
        <input type="hidden" name="command" value="create_user" />

        <c:import url="/WEB-INF/jsp/parts/login_input.jsp"/>

        <c:import url="/WEB-INF/jsp/parts/password_input.jsp"/>

        <c:import url="/WEB-INF/jsp/parts/password_repeat_input.jsp"/>

        <label for="role"><fmt:message key="text.role"/></label><br/>
        <select name="role" required>
            <c:forEach var="rol" items="${roles}">
                <option value="${rol.ordinal}"  >${rol.ordinal} <fmt:message key="${rol.toString}"/></option>
            </c:forEach>
        </select><br/>

        <c:import url="/WEB-INF/jsp/parts/email_input.jsp"/>

        <my:message key="${errorLoginPassMessage}" defaultvalue=""/>
        <input type="submit" value="<fmt:message key="text.sign_up"/>"/>
    </form>
    <hr/>
</div>
</body>
</html>








































