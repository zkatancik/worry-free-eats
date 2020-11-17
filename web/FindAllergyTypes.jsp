<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find An AllergyType</title>
</head>
<body>
	<form action="findallergytypes" method="post">
		<h1>Search for an allergyType by Id</h1>
		<p>
			<label for="allergytypesid">AllergyTypesId</label>
			<input id="allergytypesid" name="allergytypesid" value="${fn:escapeXml(param.allergytypesid)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="allergytypecreate"><a href="allergytypescreate">Create AllergyType</a></div>
	<br/>
	<h1>Matching AllergyType</h1>
        <table border="1">
            <tr>
                <th>AllergyTypesId</th>
                <th>Allergy</th>
            </tr>
                <tr>
                    <td><c:out value="${allergytype.getAllergyTypesId()}" /></td>
                    <td><c:out value="${allergytype.getAllergy()}" /></td>
                </tr>
       </table>
</body>
</html>
