<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>



    <c:choose>
        <c:when test="${userRole=='Client'}">

        <form method="POST" action="">
            <input type="hidden" name="command" value="my_orders" />
            <input type="submit" value="<fmt:message key="title.my_orders"/>"/>
        </form>


            <form id="createOrderForm" method="POST" action="">
                <input type="hidden" name="command" value="create_Order" /><br/>
                <input type="hidden" name="userId" value="${userId}" /><br/>
                    <fmt:message key="text.order_text"/><br/>
                    <my:message key="${errorOrderTextMessage}" defaultvalue=""/> <br/>
                    <my:message key="${errorEmptyOrderText}" defaultvalue=""/><br/>
                <textarea rows="5" cols="50" name="orderText" form="createOrderForm"
                    ><fmt:message key="text.enter_order_text"/></textarea>
                    <br/>

                <input type="submit" value="<fmt:message key="text.create_order"/>"/>
            </form><hr/>




        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>



<form name="logoutForm" method="POST" action="">
    <input type="hidden" name="command" value="logout" />
    <input type="submit" value="<fmt:message key="text.logout"/>"/>
    </form>

</body></html>