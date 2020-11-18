<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%@ page language="java"
contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Find an Ingredient</title>
  </head>
  <body>
    <form action="findingredient" method="post">
      <h1>Search for an Ingredient by AllergyType</h1>
      <p>
        <label for="allergytypeid">AllergyTypeId</label>
        <input
          id="allergytypeid"
          name="allergytypeid"
          value="${fn:escapeXml(param.allergytypeid)}"
        />
      </p>
      <p>
        <input type="submit" />
        <br /><br /><br />
        <span id="successMessage"><b>${messages.success}</b></span>
      </p>
    </form>
    <br />
    <div id="ingredientcreate">
      <a href="ingredientcreate">Create Ingredient</a>
    </div>
    <br />
    <h1>Matching Ingredients</h1>
    <table border="1">
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>AllergyType</th>
      </tr>
      <c:forEach items="${ingredients}" var="ingredient">
        <tr>
          <td><c:out value="${ingredient.getIngredientId()}" /></td>
          <td><c:out value="${ingredient.getName()}" /></td>
          <td>
            <c:out value="${ingredient.getAllergyTypes().getAllergy()}" />
          </td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
