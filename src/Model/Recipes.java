package Model;

public class Recipes {
  private Integer recipeId;
  private String recipeName;
  private String cookingDirects;
  private String imageUrl;

  public Recipes(Integer recipeId, String recipeName, String cookingDirects,
      String imageUrl) {
    this.recipeId = recipeId;
    this.recipeName = recipeName;
    this.cookingDirects = cookingDirects;
    this.imageUrl = imageUrl;
  }

  public Recipes(String recipeName, String cookingDirects, String imageUrl) {
    this.recipeName = recipeName;
    this.cookingDirects = cookingDirects;
    this.imageUrl = imageUrl;
  }

  public Integer getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(Integer recipeId) {
    this.recipeId = recipeId;
  }

  public String getRecipeName() {
    return recipeName;
  }

  public void setRecipeName(String recipeName) {
    this.recipeName = recipeName;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getCookingDirects() {
    return cookingDirects;
  }

  public void setCookingDirects(String cookingDirects) {
    this.cookingDirects = cookingDirects;
  }
}
