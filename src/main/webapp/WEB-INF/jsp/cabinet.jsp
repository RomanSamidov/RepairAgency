<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>

<div class="text-center" >
    <h3><fmt:message key="text.greetings"/> <fmt:message key="${userRole}"/></h3>
    <hr/> <fmt:message key="text.your_login"/> ${userLogin}<br/>
    <td><c:out value="${ userEmail }" /></td>

<form class="text-center" name="changeEmail" method="POST" action=""  target="_top">
        <input type="hidden" name="command" value="changeEmail" />
        <my:message key="${errorEmptyEmail}" defaultvalue=""/>
        <label for="email"><fmt:message key="text.change_email_to"/></label><br/>
        <input type="email" name="email" required placeholder="email"/><br/>
        <input type="submit" value="<fmt:message key="text.set_new_email"/>"/>
</form>
    <form class="text-center" name="allowLetters" method="POST" action=""  target="_top">
        <input type="hidden" name="command" value="cabinet" />
        <input type="checkbox" name="newIsUserAllowLetters" value="${!isUserAllowLetters}"   ${isUserAllowLetters ? 'checked' : ''}>
        <label for="newIsUserAllowLetters"> I agree to  receive letters</label><br>
        <input type="submit" value="<fmt:message key="text.update"/>"/>
    </form>
<c:if test="${ isUserAllowLetters }" >
    <c:if test="${ isUserConfirmed }" >
        <fmt:message key="text.you_already_confirmed_your_email"/>
    </c:if>
    <c:if test="${ !isUserConfirmed }" >
        <fmt:message key="text.you_not_confirmed_your_email"/>
        <c:if test="${ waitedCode == null }" >
            <form class="text-center" name="confirm" method="POST" action=""  target="_top">
                <input type="hidden" name="command" value="confirmEmail" />
                <fmt:message key="text.to_confirm_your_email_press"/>
                <input type="submit" value="<fmt:message key="text.confirm"/>"/>
            </form>
        </c:if>
        <c:if test="${ waitedCode != null }" >
        ${ waitedCode }
            <form class="text-center" name="confirm" method="POST" action=""  target="_top">
                <input type="hidden" name="command" value="confirmEmail" />
                <my:message key="${confirmationCodeError}" defaultvalue=""/>
                ${confirmationCodeError}
                <label for="confirmationCode"><fmt:message key="text.enter_code" /></label><br/>
                <input type="text" name="confirmationCode" required autofocus autocomplete="off" placeholder="code"/><br/>
                <input type="submit" value="<fmt:message key="text.confirm"/>"/>
            </form>
        </c:if>
    </c:if>
</c:if>

    <hr/><c:if test="${ userRole=='Client' }" >
            <fmt:message key="text.your_account"/> ${userAccount}
         </c:if>
    <hr/>

</div>

</body></html>