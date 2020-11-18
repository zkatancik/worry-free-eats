// Zack Katancik

package Model;

public class RecipeIngredients {
    protected int recipeIngredientsId;
    protected Recipes recipe;
    protected Ingredient ingredient;

	public RecipeIngredients(int recipeIngredientsId, Recipes recipe, Ingredient ingredient) {
		this.recipeIngredientsId = recipeIngredientsId;
		this.recipe = recipe;
		this.ingredient = ingredient;
    }

	/* Getters and Setters*/
	
	public int getRecipeIngredientsId() {
		return recipeIngredientsId;
	}

	public void setRecipeIngredientsId(int recipeIngredientsId) {
		this.recipeIngredientsId = recipeIngredientsId;
	}

	public Recipes getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipes recipe) {
		this.recipe = recipe;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
}
