<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>

<h3><fmt:message key="text.greetings"/> <fmt:message key="${userRole}"/></h3>
<hr/> <fmt:message key="text.your_login"/> ${userLogin}
<hr/><c:if test="${ userRole=='Client' }" >
        <fmt:message key="text.your_account"/> ${userAccount}
     </c:if>
<hr/>



<form name="logoutForm" method="POST" action="">
    <input type="hidden" name="command" value="logout" />
    <input type="submit" value="<fmt:message key="text.logout"/>"/>
    </form>

</body></html>