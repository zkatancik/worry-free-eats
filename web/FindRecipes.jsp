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
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous" defer="defer"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous" defer="defer"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous" defer="defer"></script>
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
    <div class="accordion" id="accordionExample">
<%--    <table border="1">--%>
<%--        <tr>--%>
<%--            <th>Image</th>--%>
<%--            <th>RecipeId</th>--%>
<%--            <th>RecipeName</th>--%>
<%--            <th>Details</th>--%>
<%--            <th>Update Recipe</th>--%>
<%--            <th>Delete Recipe</th>--%>
<%--        </tr>--%>
        <c:forEach items="${recipesList}" var="recipe">
<%--            <tr>--%>
<%--                <td><img src="<c:out value="${recipe.getImageUrl()}"/>" alt="N/A" width="100"></td>--%>
<%--                <td><c:out value="${recipe.getRecipeId()}" /></td>--%>
<%--                <td><c:out value="${recipe.getRecipeName()}" /></td>--%>
<%--                <td><button type="button" class="btn btn-lg btn-danger" data-toggle="popover" title="Popover title" data-content="<c:out value="${recipe.getCookingDirects()}"/>">Details</button></td>--%>
<%--                <td><a href="updaterecipe?recipeid=<c:out value="${recipe.getRecipeId()}"/>&recipename=<c:out value="${recipe.getRecipeName()}"/>&imgurl=<c:out value="${recipe.getImageUrl()}"/>">Update</a></td>--%>
<%--                <td><a href="deleterecipe?recipeid=<c:out value="${recipe.getRecipeId()}"/>&recipename=<c:out value="${recipe.getRecipeName()}"/>">Delete</a></td>--%>
<%--            </tr>--%>

                <div class="card">
                    <div class="card-header" id="heading<c:out value="${recipe.getRecipeId()}"/>">
                        <h2 class="mb-0">
                            <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapse<c:out value="${recipe.getRecipeId()}"/>" aria-expanded="true" aria-controls="collapse<c:out value="${recipe.getRecipeId()}"/>">
                                <img src="<c:out value="${recipe.getImageUrl()}"/>" alt="N/A" width="100">
                                <c:out value="${recipe.getRecipeId()}" />&nbsp;&nbsp;
                                <c:out value="${recipe.getRecipeName()}" />
                            </button>
                            <a href="updaterecipe?recipeid=<c:out value="${recipe.getRecipeId()}"/>&recipename=<c:out value="${recipe.getRecipeName()}"/>&imgurl=<c:out value="${recipe.getImageUrl()}"/>">Update</a>
                            <a href="deleterecipe?recipeid=<c:out value="${recipe.getRecipeId()}"/>&recipename=<c:out value="${recipe.getRecipeName()}"/>">Delete</a>
                        </h2>
                    </div>

                    <div id="collapse<c:out value="${recipe.getRecipeId()}"/>" class="collapse" aria-labelledby="heading<c:out value="${recipe.getRecipeId()}"/>" data-parent="#accordionExample">
                        <div class="card-body">
                            <c:out value="${recipe.getCookingDirects()}"/>
                        </div>
                    </div>
                </div>

        </c:forEach>
<%--    </table>--%>
    </div>
</div>
</body>
</html>
