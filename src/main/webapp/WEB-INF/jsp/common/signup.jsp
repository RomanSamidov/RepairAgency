<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<script src="https://www.google.com/recaptcha/api.js"></script>
<c:import url="/WEB-INF/template/_head.jsp"/>
<body>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="LocalStrings"/>
<c:import url="/WEB-INF/template/menu/_menu.jsp"/>

 <div align="center">
    <form class="text-center" name="signupForm" method="POST" action="" autocomplete="on" target="_top">
        <input type="hidden" name="command" value="signup" />

        <my:message key="${errorLoginPassMessage}" defaultvalue=""/>
        <my:message key="${errorEmptyLogin}" defaultvalue=""/>
        <label for="login"><fmt:message key="text.login" /></label><br/>
        <input type="text" name="login" required maxlength="30" autofocus autocomplete="on" placeholder="<fmt:message key="text.login" />"/><br/>

        <my:message key="${errorEmptyPassword}" defaultvalue=""/>
        <label for="password"><fmt:message key="text.password"/></label><br/>
        <input type="password" name="password" required maxlength="30" placeholder="<fmt:message key="text.password"/>"/><br/>

        <my:message key="${errorEmptyPasswordRepeat}" defaultvalue=""/>
        <label for="passwordRepeat"><fmt:message key="text.password_repeat"/></label><br/>
        <input type="password" name="passwordRepeat" required maxlength="30" placeholder="<fmt:message key="text.password"/>" /><br/>

        <c:if test="${userRole=='Admin'}" >
             <label for="role"><fmt:message key="text.role"/></label><br/>
              <select name="role" required>
                 <c:forEach var="rol" items="${roles}">
                    <option value="${rol.ordinal}"  >${rol.ordinal} <fmt:message key="${rol.toString}"/></option>
                 </c:forEach>
             </select><br/>
        </c:if>

        <my:message key="${errorEmptyEmail}" defaultvalue=""/>
        <label for="email"><fmt:message key="text.email"/></label><br/>
        <input type="email" name="email" required maxlength="30" placeholder="<fmt:message key="text.email"/>"/><br/>

        <my:message key="${errorRecaptchaMessage}" defaultvalue=""/><br/>
        <div align="center" class="g-recaptcha" data-sitekey="6LfD3asjAAAAADySW6TW3x1ZMqI4FpXSYEG2zR4N" ></div><br/>

        <my:message key="${errorLoginPassMessage}" defaultvalue=""/>
        <input type="submit" value="<fmt:message key="text.sign_up"/>"/>
    </form>
    <hr/>
</div>
</body>
</html>