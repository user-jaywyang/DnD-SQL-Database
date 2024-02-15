<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Weapons</title>
  </head>
  <body>
    <h1>${messages.title}</h1>
    <table border="1">
      <tr>
        <th>Name</th>
        <th>Level</th>
        <th>Price</th>
        <th>Max Stack Size</th>
        <th>Required Level</th>
        <th>Job</th>
        <th>Attack Damage</th>
      </tr>
      <c:forEach items="${weapons}" var="weapons" >
        <tr>
          <td><c:out value="${weapons.getItemName()}" /></td>
          <td><c:out value="${weapons.getItemLevel()}" /></td>
          <td><c:out value="${weapons.getItemPrice()}" /></td>
          <td><c:out value="${weapons.getItemMaxStackSize()}" /></td>
          <td><c:out value="${weapons.getRequiredLevel()}" /></td>
          <td><c:out value="${weapons.getJob().getJobName()}" /></td>
          <td><c:out value="${weapons.getAttackDamage()}" /></td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
