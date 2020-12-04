<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Update Likes</h1>
	<form action="Updatelikes" method="post">
		<p>
			<label for="likeId">LikeId</label>
			<input id="likeId" name="likeId" value="${likes.getLikeId()}">
		</p>
		<p>
			<label for="recipeID">RecipeID</label>
			<input id="recipeID" name="recipeID" value="${likes.getRecipe().getRecipeId()}">
		</p>
		<p>
			<label for="userId">UserId</label>
			<input id="userId" name="userId" value="${likes.getUsers().getUserId()}">
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