<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
${Locale}
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>

<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">


        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <a href="/RepairAgency/controller/home" class = "btn px-2  "><fmt:message key="title.home"/></a>
            <a href="/RepairAgency/controller/pricing" class = "btn px-2  "><fmt:message key="title.pricing"/></a>
            <a href="/RepairAgency/controller/faqs" class = "btn px-2  "><fmt:message key="title.faqs"/></a>
            <a href="/RepairAgency/controller/about" class = "btn px-2  "><fmt:message key="title.about"/></a>
            <c:if test="${userRole!='Guest'}" >
                <a href="/RepairAgency/controller/cabinet" class = "btn px-2  "><fmt:message key="title.cabinet"/></a>
                <a href="/RepairAgency/controller/order" class = "btn px-2  "><fmt:message key="title.order"/></a>
            </c:if>
            <c:if test="${userRole=='Manager' || userRole=='Admin'}" >
                <a href="/RepairAgency/controller/users" class = "btn px-2  "><fmt:message key="title.users"/></a>
            </c:if>
        </ul>

        <form name="changeLanguage">
             <select  class="form-select form-select-sm" id="language" name="language" onchange="submit()" >
                 <option value="en_US" ${language == 'en_US' ? 'selected' : ''} value="english" >English🇺🇸</option>
                 <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''} value="ukrainian" >Українська🇺🇦</option>
             </select>
        </form>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-right mb-md-0">
            <c:choose>
                <c:when test="${userRole=='Guest'}" >
                    <a href="/RepairAgency/controller/login" class = "btn  btn-outline-primary px-2  "><fmt:message key="text.login"/></a>
                    <a href="/RepairAgency/controller/signup" class = "btn  btn-primary px-2  "><fmt:message key="text.signup"/></a>
                </c:when>
                <c:otherwise >
                    <form name="logoutForm" method="POST" action="">
                        <input type="hidden" name="command" value="logout" />
                        <input type="submit" value="<fmt:message key="text.logout"/>" class = "btn btn-outline-primary px-2  " />
                    </form>
                </c:otherwise>
            </c:choose>
        </ul>

    </header>
</div>