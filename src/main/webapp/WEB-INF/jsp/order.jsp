<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>
<div class="text-center" >

<my:message key="${error}" defaultvalue=""/>
<c:choose>

         <c:when test="${goalOrder != null}">

    <table class="table table-striped table-bordered table-sm table-th">
            <caption><fmt:message key="title.order"/></caption>
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
                    </tr>

                <tr>
                    <td>${ goalOrder.id }</td>
                    <td>${ goalOrder.user_id }</td>
                    <td>${ goalOrder.craftsman_id }</td>
                    <td>${ goalOrder.creation_date }</td>
                    <td>${ goalOrder.text }</td>
                    <td>${ goalOrder.price }</td>
                    <td>${ goalOrder.finish_date }</td>
                    <td><fmt:message key="text.order.status.${goalOrder.status}"/> </td>
                    <td>${ goalOrder.feedback_date }</td>
                    <td>${ goalOrder.feedback_text }</td>
                    <td>${ goalOrder.feedback_mark }</td>

                </tr>

            </table>

     <c:choose>

             <c:when test="${userRole=='Client'}">
                <c:if test="${goalOrder.status==6}" >
                     <form method="POST" action="">
                         <input type="hidden" name="command" value="SET_FEEDBACK_FOR_ORDER" />
                         <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
                         <label for="goalOrderFeedback_text"><fmt:message key="text.order.feedback_text"/></label>
                         <input type="text" name="goalOrderFeedback_text" value=""/><br/>
                         <label for="goalOrderFeedback_mark"><fmt:message key="text.order.feedback_mark"/></label>
                         <input type="number" name="goalOrderFeedback_mark" value=""/><br/>
                         <input type="submit" value="<fmt:message key="text.change"/>"/>
                      </form>
                </c:if>
                <c:if test="${goalOrder.status==2}" >
                             <form method="POST" action="">
                                 <input type="hidden" name="command" value="pay_For_Order" />
                                 <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
                                 <input type="submit" value="<fmt:message key="text.pay"/>"/>
                              </form>
                </c:if>
             </c:when>

            <c:when test="${userRole=='Manager'||userRole=='Admin'}">
                <c:if test="${goalOrder.status==1}" >
                    <form method="POST" action="">
                    <input type="hidden" name="command" value="SET_CRAFTSMAN_AND_PRICE" />
                    <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
                    <label for="goalOrderCraftsman_id"><fmt:message key="text.craftsman"/></label>
                    <select name="goalOrderCraftsman_id" required>
                        <c:forEach var="craftsman" items="${craftsmen}">
                           <option value="${craftsman.id}"  ${craftsman.id==goalOrder.craftsman_id?"selected=\"selected\"":""} >${craftsman.id} ${craftsman.login}</option>
                        </c:forEach>
                    </select>
                    <label for="goalOrderCraftsman_id"><fmt:message key="text.order.price"/></label>
                    <input type="number" name="goalOrderPrice" value="${goalOrder.price}" required/>
                    <input type="submit" value="<fmt:message key="text.change"/>"/>
                    </form>
                </c:if>
                <c:if test="${goalOrder.status==2}" >
                     <form method="POST" action="">
                         <input type="hidden" name="command" value="pay_For_Order" />
                         <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
                         <input type="submit" value="<fmt:message key="text.pay"/>"/>
                      </form>
                </c:if>
            </c:when>
            <c:when test="${userRole=='Craftsman'}">
            <c:if test="${goalOrder.status==3}" >
            <form method="POST" action="">
                                <input type="hidden" name="command" value="SET_ORDER_STATUS" />
                                <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
                                <input type="hidden" name="goalOrderStatus" value="5" />
                                <input type="submit" value="<fmt:message key="text.take_to_progress"/>"/>
                            </form>
            </c:if>
            <c:if test="${goalOrder.status==5}" >
            <form method="POST" action="">
                                <input type="hidden" name="command" value="SET_ORDER_STATUS" />
                                <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
                                <input type="hidden" name="goalOrderStatus" value="6" />
                                <input type="submit" value="<fmt:message key="text.complete"/>"/>
                            </form>
            </c:if>

            </c:when>
        </c:choose>


<c:if test="${goalOrder.status!=6 && goalOrder.status!=4}" >
            <form method="POST" action="">
                                <input type="hidden" name="command" value="CANCEL_ORDER" />
                                <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
                                <input type="hidden" name="goalOrderStatus" value="4" />
                                <input type="submit" value="<fmt:message key="text.cancel_order"/>"/>
                            </form>
            </c:if>

 </c:when>
</c:choose>

</div>
</body></html>