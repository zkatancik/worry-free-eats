<%@include file="header.jsp"%>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
          crossorigin="anonymous">
    <title>Nutrition</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-6">
                <h1>test page</h1>
                <form class="needs-validation" action="Nutrition" method="get" novalidate>
                    <h1>Find Nutritions</h1>
                    <br>
                    <lable for="recipeId">RecipeId</lable>
                    <input type="number" class="form-control" name="recipeId" required>
                    <button type="submit" class="btn btn-success">Submit</button>
                    <span id="successMessage" ><b>${messages.success}</b></span>
                </form>
            </div>
        </div>
    </div>
    <br>
    <h1>Nutritions</h1>
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
</body>

<%@include file="footer.jsp"%>

