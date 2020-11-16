<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Delete a Recipe</title>
</head>
<body>
<form action="deleterecipe" method="post">
<%--    <p>--%>
<%--        <label for="recipeid">RecipeId</label>--%>
<%--        <input id="recipeid" name="recipeid" value="${fn:escapeXml(param.recipeid)}" readonly="readonly">--%>
<%--    </p>--%>
<%--    <p>--%>
<%--        <label for="recipename">RecipeName</label>--%>
<%--        <input id="recipename" name="recipename" value="${fn:escapeXml(param.recipename)}" readonly="readonly">--%>
<%--    </p>--%>
<%--    <p>--%>
<%--        <input type="submit">--%>
<%--    </p>--%>
</form>
<br/><br/>
<h1>${messages.title}</h1>
</body>
</html>
