<%@ tag body-content="empty" %>
<%@ attribute name="key" required="true" %>
<%@ attribute name="default_value" required="true"  %>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"      %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"       %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>
<c:set var="msg"><fmt:message key="${key}" /></c:set>

<c:choose>

    <c:when test="${!fn:startsWith(msg,'???')}">
        ${msg}<br/>
    </c:when>

    <c:otherwise>
        ${default_value}
    </c:otherwise>

</c:choose>