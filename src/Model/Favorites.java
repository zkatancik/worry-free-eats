package Model;

public class Favorites {
  protected int favoriteId;
  protected int recipeID;
  protected int userId;
  protected Recipes recipe;
  protected Users users;

  public Favorites(int favoriteId, int recipeID, int userId, Recipes recipe, Users users) {
    this.favoriteId = favoriteId;
    this.recipeID = recipeID;
    this.userId = userId;
    this.recipe = recipe;
    this.users = users;
  }

  public Favorites(int recipeID, int userId, Recipes recipe, Users users) {
    this.recipeID = recipeID;
    this.userId = userId;
    this.recipe = recipe;
    this.users = users;
  }

  public Favorites(int favoriteId) {
    this.favoriteId = favoriteId;
  }

  public int getFavoriteId() {
    return favoriteId;
  }

  public void setFavoriteId(int favoriteId) {
    this.favoriteId = favoriteId;
  }

  public int getRecipeID() {
    return recipeID;
  }

  public void setRecipeID(int recipeID) {
    this.recipeID = recipeID;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public Recipes getRecipe() {
    return recipe;
  }

  public void setRecipe(Recipes recipe) {
    this.recipe = recipe;
  }

  public Users getUsers() {
    return users;
  }

  public void setUsers(Users users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "Favorites{" +
        "favoriteId=" + favoriteId +
        ", recipeID=" + recipeID +
        ", userId=" + userId +
        ", recipe=" + recipe +
        ", users=" + users +
        '}';
  }
}