<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<div class="text-center " align="center">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <c:import url="/WEB-INF/template/menu/parts/commonLinks.jsp"/>
        </ul>
        <myc:info-time/>
        <c:import url="/WEB-INF/template/menu/parts/changeLanguageForm.jsp"/>
        <ul class="text-right" >
            <a href="/RepairAgency/controller/login" class = "btn  btn-outline-primary px-2  "><fmt:message key="text.sign_in"/></a>
            <a href="/RepairAgency/controller/signup" class = "btn  btn-primary px-2  "><fmt:message key="text.sign_up"/></a>
        </ul>
    </header>
</div>