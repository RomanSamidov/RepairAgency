<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB_INF/jspf/directive/taglib.jspf" %>
${Locale}
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>

<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
      <a href="/RepairAgency/controller/home" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
         <fmt:message key="helloworld.title"/>
      </a>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <a href="/RepairAgency/controller/home" class = "btn px-2  ">Home</a>
            <a href="/RepairAgency/controller/pricing" class = "btn px-2  ">Pricing</a>
            <a href="/RepairAgency/controller/faqs" class = "btn px-2  ">FAQs</a>
            <a href="/RepairAgency/controller/about" class = "btn px-2  ">About</a>
        </ul>

        <form name="changeLanguage">
             <select  class="form-select form-select-sm" id="language" name="language" onchange="submit()" >
                 <option value="en_US" ${language == 'en_US' ? 'selected' : ''} value="english" >English🇺🇸</option>
                 <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''} value="ukrainian" >Українська🇺🇦</option>
             </select>
        </form>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-right mb-md-0">
            <c:choose>
                <c:when test="${ empty userRole }" >
                    <a href="/RepairAgency/controller/login" class = "btn  btn-outline-primary px-2  ">Login</a>
                    <a href="/RepairAgency/controller/signup" class = "btn  btn-primary px-2  ">Sign-up</a>
                </c:when>
                <c:when test="${ not empty userRole }" >
                    <form name="logoutForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="logout" />
                        <input type="submit" value="Log out" class = "btn btn-outline-primary px-2  " />
                    </form>
                </c:when>
            </c:choose>
        </ul>

    </header>
</div>