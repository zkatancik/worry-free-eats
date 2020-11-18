<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Update Favorites</h1>
	<form action="updatefavorites" method="post">
		<p>
			<label for="favoriteId">FavoriteId</label>
			<input id="favoriteId" name="favoriteId" value="${favorites.getFavoriteId()}">
		</p>
		<p>
			<label for="recipeID">RecipeID</label>
			<input id="recipeID" name="recipeID" value="${favorites.getRecipe().getRecipeId()}">
		</p>
		<p>
			<label for="userId">UserId</label>
			<input id="userId" name="userId" value="${favorites.getUsers().getUserId()}">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${message.success}</b></span>
	</p>
</body>
</html>