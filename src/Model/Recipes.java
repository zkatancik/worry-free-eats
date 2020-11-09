package Model;

public class Recipes {
  private Integer recipeId;
  private String recipeName;
  private String imageUrl;

  public Recipes(Integer recipeId, String recipeName, String imageUrl) {
    this.recipeId = recipeId;
    this.recipeName = recipeName;
    this.imageUrl = imageUrl;
  }

  public Recipes(String recipeName, String imageUrl) {
    this.recipeName = recipeName;
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
}
