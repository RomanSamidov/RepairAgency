<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<script src="https://www.google.com/recaptcha/api.js"></script>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<my:message key="${errorRecaptchaMessage}" default_value=""/><br/>
<div align="center" class="g-recaptcha" data-sitekey="6LfD3asjAAAAADySW6TW3x1ZMqI4FpXSYEG2zR4N"></div><br/>