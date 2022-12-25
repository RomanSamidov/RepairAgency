<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>



<form name="pages" method="POST" action="">
    <input type="hidden" name="command" value="users" />
     <input type="text" name="quantityUsers" value="5"/>
    <input type="submit" value="<fmt:message key="text.show_on_one_page"/>"/>
    </form>
<table>
        <tr>
            <td> id </td>
            <td> login </td>
            <td> account </td>
        </tr>
    <c:forEach var="user" items="${users}" varStatus="status">
        <tr>
        <td><c:out value="${ user.id }" /></td>
        <td><c:out value="${ user.login }" /></td>
        <td><c:out value="${ user.account }" /></td>
        <td> <form method="POST" action="">
                        <input type="hidden" name="command" value="user" />
                        <input type="hidden" name="goalIdUser" value="${ user.id }" />
                        <input type="submit" value="ch"/>
             </form></td>
        </tr>
    </c:forEach>
</table>

<br/>

<table>
<tr>
<c:forEach var="page" items="${listPagesUsers}" varStatus="status">
        <form method="POST" action="">
            <input type="hidden" name="command" value="users" />
            <input type="hidden" name="skipUsers" value="${ page }" />
            <input type="submit" value="${ status.count }"/>
        </form>

    </c:forEach>
</tr>
</table>
</body></html>