<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>
<div class="text-center" >


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
            <c:choose>
                    <c:when test="${userRole!='Craftsman'}">
                        <label for="craftsmanIdOrders">Craftsmen:</label>
                        <select name="craftsmanIdOrders" size="2" multiple>
                            <option value="0">All</option>
                            <c:forEach var="craftsman" items="${craftsmen}">
                               <option value="${craftsman.id}">${craftsman.id} ${craftsman.login}</option>
                            </c:forEach>
                        </select>
                    </c:when>
            </c:choose>

                    <label for="statusOrders">Statuses:</label>
                    <select name="statusOrders" size="2" multiple>
                        <option value="0">All</option>
                        <c:forEach var="orStatus" items="${orderStatuses}">
                            <option value="${orStatus.ordinal}">${craftsman.id} ${orStatus.toString}</option>
                        </c:forEach>
                    </select>

        <input type="submit" value="select"/>
    </form>


        <form name="pages" method="POST" action="">
            <input type="hidden" name="command" value="orders" />
             <input type="number" name="quantityOrders" value="${nowQuantityOrders}"/>
            <input type="submit" value="<fmt:message key="text.show_on_one_page"/>"/>
            </form>
        <table class="table table-striped table-bordered table-sm table-th">
        <caption>List of orders</caption>
        <tr>
                <th> id </th>
                <th> user_id </th>
                <th> craftsman_id </th>
                <th> creation_date </th>
                <th> text </th>
                <th> price </th>
                <th> finish_date </th>
                <th> status </th>
                <th> feedback_date </th>
                <th> feedback_text </th>
                <th> feedback_mark </th>
                </tr>
            <c:forEach var="order" items="${orders}" varStatus="status">
                <tr>
                <td><c:out value="${ order.id }" /></td>
                <td><c:out value="${ order.user_id }" /></td>
                <td><c:out value="${ order.craftsman_id }" /></td>
                <td><c:out value="${ order.creation_date }" /></td>
                <td><c:out value="${ order.text }" /></td>
                <td><c:out value="${ order.price }" /></td>
                <td><c:out value="${ order.finish_date }" /></td>
                <td><c:out value="${ order.status }" /></td>
                <td><c:out value="${ order.feedback_date }" /></td>
                <td><c:out value="${ order.feedback_text }" /></td>
                <td><c:out value="${ order.feedback_mark }" /></td>
                <td>
                 <a href="/RepairAgency/controller/order?id=${order.id}" class = "btn px-2  ">ch</a>
                </td>
                </tr>
            </c:forEach>
        </table>

        <br/>

        <table>
        <tr>
        <c:forEach var="page" items="${listPagesOrders}" varStatus="status">
                <form method="POST" action="">
                    <input type="hidden" name="command" value="orders" />
                    <input type="hidden" name="skipOrders" value="${ page }" />
                    <input type="submit" value="${ status.count }" ${nowPageOrders+1==status.count?"disabled=\"disabled\"":""}/>
                </form>

            </c:forEach>
        </tr>
        </table>

</div>
</body></html>