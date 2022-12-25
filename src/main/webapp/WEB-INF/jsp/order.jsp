<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>

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
    <tr>
                <td><c:out value="${ goalOrder.id }" /></td>
                <td><c:out value="${ goalOrder.user_id }" /></td>
                <td><c:out value="${ goalOrder.craftsman_id }" /></td>
                <td><c:out value="${ goalOrder.text }" /></td>
                <td><c:out value="${ goalOrder.price }" /></td>
                <td><c:out value="${ goalOrder.status_id }" /></td>
                <td><c:out value="${ goalOrder.feedback_text }" /></td>
                <td><c:out value="${ goalOrder.feedback_mark }" /></td>

    </tr>
</table>


 <c:choose>

         <c:when test="${userRole=='Client'}">
         <form method="POST" action="">
                     <input type="hidden" name="command" value="order" />
                     <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
                     feedback_text
                     <input type="text" name="goalOrderFeedback_text" value=""/><br/>
                     goalOrder.feedback_mark
                     <input type="text" name="goalOrderFeedback_mark" value=""/><br/>
                     <input type="submit" value="change"/>
                 </form>


         </c:when>

        <c:when test="${userRole=='Manager'||userRole=='Admin'}">
            <form method="POST" action="">
            <input type="hidden" name="command" value="order" />
            <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
            craftsman_id
            <input type="text" name="goalOrderCraftsman_id" value=""/><br/>
            price
            <input type="text" name="goalOrderPrice" value=""/><br/>
             Status
            <input type="text" name="goalOrderStatus" value=""/><br/>
            <input type="submit" value="change"/>
        </form>
        </c:when>
        <c:when test="${userRole=='Craftsman'}">
                    <form method="POST" action="">
                    <input type="hidden" name="command" value="order" />
                    <input type="hidden" name="goalIdOrder" value="${ goalOrder.id }" />
                     Status
                    <input type="text" name="goalOrderStatus" value=""/><br/>
                    <input type="submit" value="change"/>
                </form>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>



</body></html>