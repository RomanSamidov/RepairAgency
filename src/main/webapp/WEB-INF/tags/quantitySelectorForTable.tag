<%@ tag body-content="empty" %>
<%@ attribute name="command" required="true" %>
<%@ attribute name="table_name" required="true"  %>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"      %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"       %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>
<c:set var="nowQuantityFor"><%= session.getAttribute("nowQuantityFor"+table_name) %></c:set>


<form name="pages" method="POST" action="">
    <input type="hidden" name="command" value="${command}" />
    <input type="number" name="newQuantityFor${table_name}" value="${nowQuantityFor}" min="1" size="4"/>
    <input type="submit" value="<fmt:message key="text.show_on_one_page"/>"/>
</form>