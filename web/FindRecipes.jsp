<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find a recipe</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<form action="findrecipes" method="post">
    <h1>Search for a recipe by keyword</h1>
    <p>
        <label for="recipename">RecipeName</label>
        <input id="recipename" name="keyword" value="${fn:escapeXml(param.keyword)}">
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<br/>
<div id="createRecipe"><a href="createrecipe">Add a new recipe</a></div>
<br/>
<h1>Matching Recipes</h1>
<div <c:if test="${empty gotAny || gotAny == false}">style="display: none"</c:if>>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="findrecipes?pageindex=0&keyword=${fn:escapeXml(param.keyword)}"><<</a></li>
            <li class="page-item"><a class="page-link" href="findrecipes?pageindex=${pre}&keyword=${fn:escapeXml(param.keyword)}"><</a></li>
            <li class="page-item"><c:out value="Page ${pageIndex} of ${pageCnt}" /></li>
            <li class="page-item"><a class="page-link" href="findrecipes?pageindex=${next}&keyword=${fn:escapeXml(param.keyword)}">></a></li>
            <li class="page-item"><a class="page-link" href="findrecipes?pageindex=${last}&keyword=${fn:escapeXml(param.keyword)}">>></a></li>
        </ul>
    </nav>
    <table border="1">
        <tr>
            <th>Image</th>
            <th>RecipeId</th>
            <th>RecipeName</th>
            <th>Delete Recipe</th>
            <th>Update Recipe</th>
        </tr>
        <c:forEach items="${recipesList}" var="recipe">
            <tr>
                <td><img src="<c:out value="${recipe.getImageUrl()}"/>" alt="N/A" width="100"></td>
                <td><c:out value="${recipe.getRecipeId()}" /></td>
                <td><c:out value="${recipe.getRecipeName()}" /></td>
                    <%--            <td><a href="userblogposts?username=<c:out value="${recipe.getUserName()}"/>">BlogPosts</a></td>--%>
                    <%--            <td><a href="blogcomments?username=<c:out value="${recipe.getUserName()}"/>">BlogComments</a></td>--%>
                    <%--            <td><a href="userdelete?username=<c:out value="${recipe.getUserName()}"/>">Delete</a></td>--%>
                    <%--            <td><a href="userupdate?username=<c:out value="${recipe.getUserName()}"/>">Update</a></td>--%>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
