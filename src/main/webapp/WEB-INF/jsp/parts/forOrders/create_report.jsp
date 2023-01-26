<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form name="createReport" method="POST" action="" target="_blank">
    <input type="hidden" name="command" value="create_report" />
    <select name="reportFormat">
        <c:forEach var="format" items="${reportFormats}">
            <option value="${format.toString}"  ${format.toString==reportFormat?"selected=\"selected\"":""} >${format.toString}</option>
        </c:forEach>
    </select>
    <input type="submit" value="<fmt:message key="text.create_report"/>"/>
</form>