<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Update Review</h1>
	<form action="updatereview" method="post">
		<p>
			<label for="reviewid">ReviewId</label>
			<input id="reviewid" name="reviewid" value="${review.getReviewId()}">
		</p>
		<p>
			<label for="reviewtext">ReviewText</label>
			<input id="reviewtext" name="reviewtext" value="${review.getReviewText()}">
		</p>
		<p>
			<label for="recipeid">RecipeId</label>
			<input id="recipeId" name="recipeid" value="${review.getRecipeId()}">
		</p>
		<p>
			<label for="rating">Rating</label>
			<input id="rating" name="rating" value="${review.getRating()}">
		</p>
		<p>
			<label for="userid">UserId</label>
			<input id="userid" name="userid" value="${review.getUserId()}">
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