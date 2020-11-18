<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%@ page language="java"
contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Find a RecipeIngredient</title>
  </head>
  <body>
    <form action="findlikes" method="post">
      <h1>Search for likes by users</h1>
      <p>
        <label for="userId">UserId</label>
        <input
          id="userId"
          name="userId"
          value="${fn:escapeXml(param.userId)}"
        />
      </p>
      <p>
        <input type="submit" />
        <br /><br /><br />
        <span id="successMessage"><b>${messages.success}</b></span>
      </p>
    </form>
    <br />
    <div id="likescreate">
      <a href="likescreate">Create Likes</a>
    </div>
    <br />
    <h1>Matching Likes</h1>
    <table border="1">
      <tr>
        <th>ID</th>
        <th>Recipe</th>
        <th>Users</th>
      </tr>
      <c:forEach items="${likes}" var="likes">
        <tr>
          <td>
            <c:out value="${likes.getLikeId()}" />
          </td>
          <td>
            <c:out value="${likes.getRecipe().getRecipeName()}" />
          </td>
          <td>
            <c:out value="${likes.getUsers().getUserName()}" />
          </td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
