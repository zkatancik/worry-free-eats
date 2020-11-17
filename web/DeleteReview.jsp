<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${messages.title}</h1>
	<form action="deletereview" method="post">
		<p>
			<div>
				<label for="reviewid">ReviewId</label>
				<input id="reviewid" name="reviewid" value="">
			</div>
		</p>
		<p>
			<span id="submitButton">
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>