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


        <form name="pages" method="POST" action="">
            <input type="hidden" name="command" value="orders" />
             <input type="text" name="quantityOrders" value="5"/>
            <input type="submit" value="<fmt:message key="text.show_on_one_page"/>"/>
            </form>
        <table>
        <tr>
                <td> id </td>
                <td> user_id </td>
                <td> craftsman_id </td>
                <td> text </td>
                <td> price </td>
                <td> status_id </td>
                <td> feedback_text </td>
                <td> feedback_mark </td>
                </tr>
            <c:forEach var="order" items="${orders}" varStatus="status">
                <tr>
                <td><c:out value="${ order.id }" /></td>
                <td><c:out value="${ order.user_id }" /></td>
                <td><c:out value="${ order.craftsman_id }" /></td>
                <td><c:out value="${ order.text }" /></td>
                <td><c:out value="${ order.price }" /></td>
                <td><c:out value="${ order.status_id }" /></td>
                <td><c:out value="${ order.feedback_text }" /></td>
                <td><c:out value="${ order.feedback_mark }" /></td>
                <td> <form method="POST" action="">
                                        <input type="hidden" name="command" value="order" />
                                        <input type="hidden" name="goalIdOrder" value="${ order.id }" />
                                        <input type="submit" value="ch"/>
                </form></td>
                </tr>
            </c:forEach>
        </table>

        <br/>

        <table>
        <tr>
        <c:forEach var="page" items="${listPagesOrders}" varStatus="status">
                <form method="POST" action="">
                    <input type="hidden" name="command" value="my_orders" />
                    <input type="hidden" name="skipOrders" value="${ page }" />
                    <input type="submit" value="${ status.count }"/>
                </form>

            </c:forEach>
        </tr>
        </table>


</body></html>