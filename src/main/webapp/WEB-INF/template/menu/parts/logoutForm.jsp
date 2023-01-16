<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>


    <form name="logoutForm" method="POST" action="">
        <input type="hidden" name="command" value="logout" />
        <input type="submit" value="<fmt:message key="text.logout"/>" class = "btn btn-outline-primary px-2  " />
    </form>
