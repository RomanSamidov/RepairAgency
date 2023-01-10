<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>
<div class="text-center" >



        <c:if test="${userRole=='Client'}">
            <form id="createOrderForm" method="POST" action="">
                <input type="hidden" name="command" value="create_Order" /><br/>
                <input type="hidden" name="userId" value="${userId}" /><br/>
                    <fmt:message key="text.order_text"/><br/>
                    <my:message key="${errorOrderTextMessage}" defaultvalue=""/>
                <textarea rows="5" cols="50" name="orderText" form="createOrderForm"
                    ><fmt:message key="text.enter_order_text"/></textarea>
                    <br/>
                <input type="submit" value="<fmt:message key="text.create_order"/>"/>
            </form><hr/>
        </c:if>




    <form name="pages" method="POST" action="">
        <input type="hidden" name="command" value="orders" />
            <c:choose>
                    <c:when test="${userRole!='Craftsman'}">
                        <label for="craftsmanIdOrders"><fmt:message key="text.craftsmen"/></label>
                        <select name="craftsmanIdOrders" size="2" multiple>
                            <option value="0"><fmt:message key="text.all"/></option>
                            <c:forEach var="craftsman" items="${craftsmen}">
                               <option value="${craftsman.id}">${craftsman.id} ${craftsman.login}</option>
                            </c:forEach>
                        </select>
                    </c:when>
            </c:choose>

                    <label for="statusOrders"> <fmt:message key="text.statuses"/></label>
                    <select name="statusOrders" size="2" multiple>
                        <option value="0"><fmt:message key="text.all"/></option>
                        <c:forEach var="orStatus" items="${orderStatuses}">
                            <option value="${orStatus.ordinal}">${orStatus.ordinal} <fmt:message key="text.order.status.${orStatus.ordinal}"/></option>
                        </c:forEach>
                    </select>

                    <label for="sortTypeOrders"><fmt:message key="text.sort_by"/></label>
                    <select name="sortTypeOrders">
                        <c:forEach var="sortType" items="${sortTypesOrders}">
                            <option value="${sortType.toString}"  ${sortType.toString==sortTypeOrders?"selected=\"selected\"":""} ><fmt:message key="text.sort_by.${sortType.toString}"/></option>
                        </c:forEach>
                    </select>

        <input type="submit" value="<fmt:message key="text.select"/>"/>
    </form>


            <form name="createReport" method="POST" action="" target="_blank">
                <input type="hidden" name="command" value="orders" />
                <input type="hidden" name="createReport" value="true" />
                <select name="reportFormat">
                    <c:forEach var="format" items="${reportFormats}">
                        <option value="${format.toString}"  ${format.toString==reportFormat?"selected=\"selected\"":""} >${format.toString}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="<fmt:message key="text.create_report"/>"/>
            </form>



        <form name="pages" method="POST" action="">
            <input type="hidden" name="command" value="orders" />
             <input type="number" name="quantityOrders" value="${nowQuantityOrders}" min="1" max="500"/>
            <input type="submit" value="<fmt:message key="text.show_on_one_page"/>"/>
            </form>
            <c:if test="${orders.isEmpty() == true}" >
            Founded no orders!
            </c:if>
        <table class="table table-striped table-bordered table-sm table-th">
        <caption><fmt:message key="text.list_of_orders"/></caption>
        <tr>
                <th><fmt:message key="text.order.id"/>  </th>
                <th><fmt:message key="text.order.user_id"/>  </th>
                <th><fmt:message key="text.order.craftsman_id"/>  </th>
                <th><fmt:message key="text.order.creation_date"/>  </th>
                <th><fmt:message key="text.order.text"/>  </th>
                <th><fmt:message key="text.order.price"/>  </th>
                <th><fmt:message key="text.order.finish_date"/>  </th>
                <th><fmt:message key="text.order.status"/>  </th>
                <th><fmt:message key="text.order.feedback_date"/>  </th>
                <th><fmt:message key="text.order.feedback_text"/>  </th>
                <th><fmt:message key="text.order.feedback_mark"/>  </th>
                <th> </th>
                </tr>
            <c:forEach var="order" items="${orders}" varStatus="status">
                <tr>
                <td>${ order.id }</td>
                <td>${ order.user_id }</td>
                <td>${ order.craftsman_id==0?"":order.craftsman_id }</td>
                <td>${ order.creation_date }</td>
                <td>${ order.text }</td>
                <td>${ order.price }</td>
                <td>${ order.finish_date }</td>
                <td><fmt:message key="text.order.status.${order.status}"/> </td>
                <td>${ order.feedback_date }</td>
                <td>${ order.feedback_text }</td>
                <td>${ order.feedback_mark==0?"":order.feedback_mark}</td>
                <td>
                 <a href="/RepairAgency/controller/order?id=${order.id}" class = "btn px-2  "><fmt:message key="text.go_to"/> </a>
                </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${orders.isEmpty() == true}" >
            Founded no orders!
        </c:if>
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