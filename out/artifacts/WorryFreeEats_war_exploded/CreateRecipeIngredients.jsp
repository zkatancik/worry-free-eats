<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%@ page language="java"
contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Create a RecipeIngredient</title>
  </head>
  <body>
    <h1>Create an RecipeIngredient</h1>
    <form action="recipeingredientscreate" method="post">
      <p>
        <label for="recipeIngredientsId">RecipeIngredientsId</label>
        <input id="recipeIngredientsId" name="recipeIngredientsId" value="" />
      </p>
      <p>
        <label for="recipe">RecipeId</label>
        <input id="recipe" name="recipe" value="" />
      </p>
      <p>
        <label for="ingredient">IngredientId</label>
        <input id="ingredient" name="ingredient" value="" />
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
