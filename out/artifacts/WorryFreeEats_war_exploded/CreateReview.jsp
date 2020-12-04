<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@include file="component/csshead.jsp"%>
<title>Create a Review</title>
</head>
<body>
<%@include file="component/navbar.jsp"%>
<div class="container">
	<h1>Create Review</h1>
	<form action="createreview" method="post">
		<p>
			<label for="reviewtext">ReviewText</label>
			<input id="reviewtext" name="reviewtext" value="">
		</p>
		<p>
			<label for="recipeid">RecipeId</label>
			<input id="recipeId" name="recipeid" value="">
		</p>
		<p>
			<label for="rating">Rating</label>
			<input id="rating" name="rating" value="">
		</p>
		<p>
			<label for="userid">UserId</label>
			<input id="userid" name="userid" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</div>
</body>
</html>