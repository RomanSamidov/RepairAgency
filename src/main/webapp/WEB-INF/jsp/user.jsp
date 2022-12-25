<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>

<h3><fmt:message key="text.greetings"/> ${goalUser.id}</h3>
<hr/> <fmt:message key="text.your_login"/> ${goalUser.login}
<hr/><c:if test="${ goalUser.role_id==4 }" >
        <fmt:message key="text.your_account"/> ${goalUser.account}
     </c:if>
<hr/>

 <form method="POST" action="">
            <input type="hidden" name="command" value="user" />
            <input type="hidden" name="goalIdUser" value="${ goalUser.id }" />
            <input type="text" name="addToAccount" value=""/><br/>
            <input type="submit" value="addToAccount"/>
        </form>


</body></html>