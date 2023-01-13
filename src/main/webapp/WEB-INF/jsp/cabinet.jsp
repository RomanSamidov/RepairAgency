<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>
  <%--  <c:import url="${_menu_url}"/>--%>

<div class="text-center" >
    <c:import url="/WEB-INF/jsp/parts/forCabinet/cabinet_text.jsp"/>

    <c:import url="/WEB-INF/jsp/parts/forCabinet/change_email_form.jsp"/>

    <c:import url="/WEB-INF/jsp/parts/forCabinet/change_profile_settings_form.jsp"/>

    <c:if test="${ isUserConfirmed }" >
        <fmt:message key="text.your_email_confirmed"/>
    </c:if>
    <c:if test="${ !isUserConfirmed }" >
        <fmt:message key="text.you_not_confirmed_your_email"/>
        <c:if test="${ waitedCode == null }" >
            <form class="text-center" name="confirm" method="POST" action=""  target="_top">
                <input type="hidden" name="command" value="confirm_Email" />
                <fmt:message key="text.to_confirm_your_email_press"/>
                <input type="submit" value="<fmt:message key="text.confirm"/>"/>
            </form>
        </c:if>
        <c:if test="${ waitedCode != null }" >
        <br/>${ waitedCode }
            <form class="text-center" name="confirm" method="POST" action=""  target="_top">
                <input type="hidden" name="command" value="confirm_Email" />
                <my:message key="${confirmationCodeError}" defaultvalue=""/>

                ${confirmationCodeError}
                <label for="confirmationCode"><fmt:message key="text.enter_code" /></label><br/>
                <input type="text" name="confirmationCode" required autofocus autocomplete="off" placeholder="code"/><br/>
                <input type="submit" value="<fmt:message key="text.confirm"/>"/>
            </form>
        </c:if>
    </c:if>


    <hr/><c:if test="${ userRole=='Client' }" >
            <fmt:message key="text.your_account"/> ${userAccount}
         </c:if>
    <hr/>

</div>

</body></html>