package Model;

public class CookingDirects {
  private int directId;
  private int recipeId;
  private String cookingTime;

  public CookingDirects(int directId, int recipeId, String cookingTime) {
    this.directId = directId;
    this.recipeId = recipeId;
    this.cookingTime = cookingTime;
  }

  public CookingDirects(int directId) {
    this.directId = directId;
  }

  public CookingDirects(int recipeId, String cookingTime) {
    this.recipeId = recipeId;
    this.cookingTime = cookingTime;
  }

  public int getDirectId() {
    return directId;
  }

  public void setDirectId(int directId) {
    this.directId = directId;
  }

  public int getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(int recipeId) {
    this.recipeId = recipeId;
  }

  public String getCookingTime() {
    return cookingTime;
  }

  public void setCookingTime(String cookingTime) {
    this.cookingTime = cookingTime;
  }
}
