<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
        <c:import url="/WEB-INF/template/_head.jsp"/>
    <body>



            <form>
              <input type="radio" id="html" name="fav_language" value="HTML">
              <label for="html">HTML</label><br>
              <input type="radio" id="css" name="fav_language" value="CSS">
              <label for="css">CSS</label><br>
              <input type="radio" id="javascript" name="fav_language" value="JavaScript">
              <label for="javascript">JavaScript</label>
            </form>

             <form>
              <input type="checkbox" id="vehicle1" name="vehicle1" value="Bike">
              <label for="vehicle1"> I have a bike</label><br>
              <input type="checkbox" id="vehicle2" name="vehicle2" value="Car">
              <label for="vehicle2"> I have a car</label><br>
              <input type="checkbox" id="vehicle3" name="vehicle3" value="Boat">
              <label for="vehicle3"> I have a boat</label>
            </form>


             <label for="cars">Choose a car:</label>
            <select id="cars" name="cars" size="2" multiple>
              <option value="volvo">Volvo</option>
              <option value="saab">Saab</option>
              <option value="fiat">Fiat</option>
              <option value="audi">Audi</option>
            </select>



          <form action="/action_page.php" target="_top">
         <input type="submit" formtarget="_blank" value="Submit to a new window/tab">
          </form>

          _blank 	The response is displayed in a new window or tab
          _self 	The response is displayed in the current window
          _parent 	The response is displayed in the parent frame
          _top 	The response is displayed in the full body of the window
          framename 	The response is displayed in a named iframe


        <form>
          <label for="country_code">Country code:</label>
          <input type="text" id="country_code" name="country_code"
          pattern="[A-Za-z]{3}" title="Three letter country code">
        </form>

        <form>
          <label for="phone">Enter a phone number:</label>
          <input type="tel" id="phone" name="phone"
          placeholder="123-45-678"
          pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}">
        </form>

         <form>
          <input list="browsers">
          <datalist id="browsers">
            <option value="Internet Explorer">
            <option value="Firefox">
            <option value="Chrome">
            <option value="Opera">
            <option value="Safari">
          </datalist>
        </form>

    </body>
</html>