<%@ tag body-content="empty" %>
<%@ attribute name="command" required="true" %>
<%@ attribute name="table_name" required="true"  %>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"      %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"       %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>
<c:set var="nowPageFor"><%= session.getAttribute("nowPageFor"+table_name) %></c:set>
<c:set var="maxPageFor"><%= request.getAttribute("maxPageFor"+table_name) %></c:set>

<table>
    <tr>
        <form method="POST" action="">
            <input type="hidden" name="command" value="${command}" />
            <input type="hidden" name="goToPageFor${table_name}" value="1" />
            <input type="submit"  value="<<" ${nowPageFor==1?"disabled=\"disabled\"":""}/>
        </form>

        <form method="POST" action="">
            <input type="hidden" name="command" value="${command}" />
            <input type="hidden" name="goToPageFor${table_name}" value="${nowPageFor-1}" />
            <input type="submit"  value="<" ${nowPageFor==1?"disabled=\"disabled\"":""}/>
        </form>


        <form method="POST" action="">
            <input type="hidden" name="command" value="${command}" />
            <label><fmt:message key="text.now_on_page"/> ${nowPageFor} <fmt:message key="text.of"/> ${maxPageFor} </label>
            <input type="submit"  value="go to"/>
            <input type="number" name="goToPageFor${table_name}" required value="${nowPageFor}" min="1" max="${maxPageFor}" size="4"/>
        </form>


        <form method="POST" action="">
            <input type="hidden" name="command" value="${command}" />
            <input type="hidden" name="goToPageFor${table_name}" value="${nowPageFor+1}" />
            <input type="submit"  value=">" ${nowPageFor==maxPageFor?"disabled=\"disabled\"":""}/>
        </form>

        <form method="POST" action="">
            <input type="hidden" name="command" value="${command}" />
            <input type="hidden" name="goToPageFor${table_name}" value="${maxPageFor}" />
            <input type="submit"  value=">>" ${nowPageFor==maxPageFor?"disabled=\"disabled\"":""}/>
        </form>
    </tr>
</table>