<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Update Character First Name</title>
  </head>
  <body>
    <h1>Update Character's First Name</h1>
    <form action="updategamechar" method="post">
    
      
      <input type="hidden" id="characterID" name="characterID" value="${fn:escapeXml(param.characterID)}">
      
      <p>
        <label for="firstName">New First Name</label>
        <input id="firstName" name="firstName" value="">
      </p>
      <p>
        <input type="submit">
      </p>
    </form>
    <br/><br/>
    <p>
      <span id="responseMessage"><b>${messages.response}</b></span>
    </p>
  </body>
</html>
