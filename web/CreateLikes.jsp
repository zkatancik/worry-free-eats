<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%@ page language="java"
contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Create a Like for recipe</title>
  </head>
  <body>
    <h1>Create a Like for recipe</h1>
    <form action="likecreate" method="post">
      <p>
        <label for="likeId">LikeId</label>
        <input id="likeId" name="likeId" value="" />
      </p>
      <p>
        <label for="recipeID">RecipeID</label>
        <input id="recipeID" name="recipeID" value="" />
      </p>
      <p>
        <label for="userId">UserId</label>
        <input id="userId" name="userId" value="" />
      </p>
      <p>
        <input type="submit" />
      </p>
    </form>
    <br /><br />
    <p>
      <span id="successMessage"><b>${messages.success}</b></span>
    </p>
  </body>
</html>
