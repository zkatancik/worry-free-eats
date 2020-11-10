package Model;

public class Likes {
  protected int likeId;
  protected int recipeID;
  protected int userId;
  protected Recipes recipe;
  protected Users users;

  public Likes(int likeId, int recipeID, int userId, Recipes recipe, Users users) {
    this.likeId = likeId;
    this.recipeID = recipeID;
    this.userId = userId;
    this.recipe = recipe;
    this.users = users;
  }

  public Likes(int recipeID, int userId, Recipes recipe, Users users) {
    this.recipeID = recipeID;
    this.userId = userId;
    this.recipe = recipe;
    this.users = users;
  }

  public Likes(int likeId) {
    this.likeId = likeId;
  }

  public int getLikeId() {
    return likeId;
  }

  public void setLikeId(int likeId) {
    this.likeId = likeId;
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
    return "Likes{" +
        "likeId=" + likeId +
        ", recipeID=" + recipeID +
        ", userId=" + userId +
        ", recipe=" + recipe +
        ", users=" + users +
        '}';
  }
}