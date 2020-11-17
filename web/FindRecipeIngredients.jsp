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
    <form action="findrecipeingredients" method="post">
      <h1>Search for an RecipeIngredient by Recipe</h1>
      <p>
        <label for="recipeid">RecipeId</label>
        <input
          id="recipeid"
          name="recipeid"
          value="${fn:escapeXml(param.recipeid)}"
        />
      </p>
      <p>
        <input type="submit" />
        <br /><br /><br />
        <span id="successMessage"><b>${messages.success}</b></span>
      </p>
    </form>
    <br />
    <div id="recipeingredientscreate">
      <a href="recipeingredientscreate">Create RecipeIngredient</a>
    </div>
    <br />
    <h1>Matching RecipeIngredients</h1>
    <table border="1">
      <tr>
        <th>ID</th>
        <th>Recipe</th>
        <th>Ingredient</th>
      </tr>
      <c:forEach items="${recipeIngredients}" var="recipeIngredient">
        <tr>
          <td>
            <c:out value="${recipeIngredient.getRecipeIngredientsId()}" />
          </td>
          <td>
            <c:out value="${recipeIngredient.getRecipe().getRecipeName()}" />
          </td>
          <td>
            <c:out value="${recipeIngredient.getIngredient().getName()}" />
          </td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
