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

         <c:when test="${error == null}">

    <table class="table table-striped table-bordered table-sm table-th">
            <caption>Order</caption>
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
                         <input type="hidden" name="command" value="order" />
                         <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
                         feedback_text
                         <input type="text" name="goalOrderFeedback_text" value=""/><br/>
                         feedback_mark
                         <input type="number" name="goalOrderFeedback_mark" value=""/><br/>
                         <input type="submit" value="change"/>
                      </form>
                </c:if>
                <c:if test="${goalOrder.status==2}" >
                             <form method="POST" action="">
                                 <input type="hidden" name="command" value="order" />
                                 <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
                                 <input type="hidden" name="payOrder" value="true" />
                                 <input type="submit" value="pay"/>
                              </form>
                </c:if>
             </c:when>

            <c:when test="${userRole=='Manager'||userRole=='Admin'}">
                <form method="POST" action="">
                <input type="hidden" name="command" value="order" />
                <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
                craftsman_id
                <select name="goalOrderCraftsman_id" required>
                    <c:forEach var="craftsman" items="${craftsmen}">
                       <option value="${craftsman.id}"  ${craftsman.id==goalOrder.craftsman_id?"selected=\"selected\"":""} >${craftsman.id} ${craftsman.login}</option>
                    </c:forEach>
                </select>
                price
                <input type="number" name="goalOrderPrice" value="${goalOrder.price}" required/><br/>
                 Status
                  <select name="goalOrderStatus" required>
                     <c:forEach var="orStatus" items="${orderStatuses}">
                         <option value="${orStatus.ordinal}"  ${orStatus.ordinal==goalOrder.status?"selected=\"selected\"":""} >${orStatus.ordinal} ${orStatus.toString}</option>
                     </c:forEach>
                  </select>
                <input type="submit" value="change"/>
            </form>
            </c:when>
            <c:when test="${userRole=='Craftsman'}">
                        <form method="POST" action="">
                        <input type="hidden" name="command" value="order" />
                        <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
                         Status
                        <select name="goalOrderStatus" required>
                             <c:forEach var="orStatus" items="${orderStatuses}">
                                 <option value="${orStatus.ordinal}" ${orStatus.ordinal==goalOrder.status?"selected=\"selected\"":""} >${orStatus.ordinal} ${orStatus.toString}</option>
                             </c:forEach>
                          </select><br/>
                        <input type="submit" value="change"/>
                    </form>
            </c:when>
        </c:choose>

 </c:when>
</c:choose>

</div>
</body></html>