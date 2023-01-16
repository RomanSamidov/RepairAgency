<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<h3><fmt:message key="text.greetings"/> <fmt:message key="${userRole}"/></h3>
<hr/> <fmt:message key="text.your_login"/> ${userLogin}<br/>
<a href="/RepairAgency/controller/change_password" class = "btn px-2  "><fmt:message key="text.to_change_your_password_press"/></a> <br/>
<fmt:message key="text.your_email"/>${ userEmail }<br/>