<%@include file="header.jsp"%>
<head>
    <%@include file="component/csshead.jsp"%>
    <title>Welcome</title>
</head>
<body>
<%@include file="component/navbar.jsp"%>
<div class="container">
    <div class="row">
        <h1 class="mt-5 mb-5 col-6 offset-3 text-center">WorryFreeEats</h1>
    </div>
    <div class="d-flex justify-content-center mb-3">
        <a href="FindRecipes.jsp"><button class="btn btn-primary">Find recipe</button></a>
    </div>
    <div class="d-flex justify-content-center mb-3">
        <a href="CreateRecipe.jsp"><button class="btn btn-warning">Create recipe</button></a>
    </div>
    <div class="d-flex justify-content-center mb-3">
        <a href="FindReviews.jsp"><button class="btn btn-primary">Find review</button></a>
    </div>
    <div class="d-flex justify-content-center mb-3">
        <a href="CreateReview.jsp"><button class="btn btn-warning">Create review</button></a>
    </div>
    <div class="d-flex justify-content-center mb-3">
        <a href="FindAllergyTypes.jsp"><button class="btn btn-primary">Find allergy type</button></a>
    </div>
</div>
<%@include file="component/cssbody.jsp"%>
</body>
<%@include file="footer.jsp"%>
