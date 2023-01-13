<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form name="changeLanguage">
     <select  class="form-select form-select-sm" id="language" name="language" onchange="submit()" >
         <option value="en_US" ${language == 'en_US' ? 'selected' : ''} value="english" >English🇺🇸</option>
         <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''} value="ukrainian" >Українська🇺🇦</option>
     </select>
</form>