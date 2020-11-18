<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%@ page language="java"
contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Create an AllergyType</title>
  </head>
  <body>
    <h1>Create an AllergyType</h1>
    <form action="ingredientcreate" method="post">
      <p>
        <label for="ingredientId">IngredientId</label>
        <input id="ingredientId" name="ingredientId" value="" />
      </p>
      <p>
        <label for="name">Name</label>
        <input id="name" name="name" value="" />
      </p>
      <p>
        <label for="allergyTypes">AllergyTypes</label>
        <input id="allergyTypes" name="allergyTypes" value="" />
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
