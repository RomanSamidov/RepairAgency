<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>

<div class="text-center" >
<h3><fmt:message key="text.greetings"/> <fmt:message key="${userRole}"/></h3>
<hr/> <fmt:message key="text.your_login"/> ${userLogin}
        <td><c:out value="${ goalUser.email }" /></td>
<hr/><c:if test="${ userRole=='Client' }" >
        <fmt:message key="text.your_account"/> ${userAccount}
     </c:if>
<hr/>

</div>

</body></html>