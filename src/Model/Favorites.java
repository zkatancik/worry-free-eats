package Model;

public class Favorites {
  protected int favoriteId;
  protected Recipes recipe;
  protected Users users;

  public Favorites(int favoriteId, Recipes recipe, Users users) {
    this.favoriteId = favoriteId;
    this.recipe = recipe;
    this.users = users;
  }

  public Favorites(Recipes recipe, Users users) {
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
        ", recipe=" + recipe +
        ", users=" + users +
        '}';
  }
}