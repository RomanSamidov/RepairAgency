<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>
<div class="text-center" >

    <c:import url="/WEB-INF/jsp/parts/forOrders/set_filters.jsp"/>
    <%--<c:import url="${_show_orders_url}"/>--%>

    <c:import url="/WEB-INF/jsp/parts/forOrders/create_report.jsp"/>
    <%--<c:import url="${_show_orders_url}"/>--%>



        <form name="pages" method="POST" action="">
            <input type="hidden" name="command" value="orders" />
             <input type="number" name="quantityOrders" value="${nowQuantityOrders}" min="1" max="500"/>
            <input type="submit" value="<fmt:message key="text.show_on_one_page"/>"/>
        </form>




    <%--<c:import url="/WEB-INF/jsp/parts/forOrders/show_orders.jsp"/>--%>
    <c:import url="${_show_orders_url}"/>

    <my:message key="${error}" defaultvalue=""/>



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