<%@include file="header.jsp"%>
<head>
    <%@include file="component/csshead.jsp"%>
    <title>Nutrition</title>
</head>
<body>
<%@include file="component/navbar.jsp"%>
    <div class="container">
        <div class="row">
            <div class="col-6 offset-3">
                <h1 class="text-center mt-3">Find Nutritions</h1>
                <form class="needs-validation" action="Nutrition" method="get" novalidate>
                    <br>
                    <lable for="recipeId">RecipeId</lable>
                    <input type="number" class="form-control mt-3" name="recipeId" required>
                    <button type="submit" class="btn btn-success mt-3">Submit</button>
                    <span id="successMessage" ><b>${messages.success}</b></span>
                </form>
            </div>
        </div>
    </div>
    <br>
    <div class="container">
        <div class="row">
            <div class="col-8 offset-2">
                <h1 class="mt-3 text-center">Nutritions</h1>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Recipe</th>
                        <th scope="col">Calorie</th>
                        <th scope="col">Fat</th>
                        <th scope="col">Carbohydrates</th>
                        <th scope="col">Protein</th>
                        <th scope="col">Cholesterol</th>
                        <th scope="col">Sodium</th>
                        <th scope="col">Fiber</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><c:out value="${nutrition.getRecipe().getRecipeName()}"/></td>
                        <td><c:out value="${nutrition.getCalories()}"/></td>
                        <td><c:out value="${nutrition.getFat()}"/></td>
                        <td><c:out value="${nutrition.getCarbohydrates()}"/></td>
                        <td><c:out value="${nutrition.getProtein()}"/></td>
                        <td><c:out value="${nutrition.getCholesterol()}"/></td>
                        <td><c:out value="${nutrition.getSodium()}"/></td>
                        <td><c:out value="${nutrition.getFiber()}"/></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <%@include file="component/cssbody.jsp"%>
</body>

<%@include file="footer.jsp"%>

