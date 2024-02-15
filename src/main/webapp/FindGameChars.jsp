<!-- 2-7: copy in all jsp files -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find a Character</title>
  </head>
  <body>
    <form action="findgamechars" method="post">		
      <h1>Search for a Character By First Name</h1>
      <p>
        <label for="firstName">First Name</label>
        
        
        <input id="firstName" name="firstName" value="${fn:escapeXml(param.firstName)}">
      </p>
      <p>
        <input type="submit">
        <br/><br/><br/>		
       
        <span id="responseMessage"><b>${messages.response}</b></span>
      </p>
    </form>

    <h1>Matching Characters</h1>
    <table border="1">
      <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Player Name</th>
        <th>Clan</th>
        <th>Current Job</th>
        <th>Equipped Weapon</th>
        <th>Weapon</th>
        <th>Update First Name</th>
      </tr>
      

      <c:forEach items="${gameChar}" var="gameChar" >
        <tr>
          <td><c:out value="${gameChar.getFirstName()}" /></td>
          <td><c:out value="${gameChar.getLastName()}" /></td>
          <td><c:out value="${gameChar.getPlayer().getFullName()}" /></td>
          <td><c:out value="${gameChar.getClan().getClanName()}" /></td>
          <td><c:out value="${gameChar.getCurrentJob().getJobName()}" /></td>
          <td><c:out value="${gameChar.getCurrWeapon().getItemName()}" /></td>
          

          <td><a href="gamecharweapons?characterID=<c:out value="${gameChar.getCharacterID()}"/>">Weapons</a></td>
          <td><a href="updategamechar?characterID=<c:out value="${gameChar.getCharacterID()}"/>">Update</a></td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
