package Model;

public class Likes {
  protected int likeId;
  protected Recipes recipe;
  protected Users users;

  public Likes(int likeId, Recipes recipe, Users users) {
    this.likeId = likeId;
    this.recipe = recipe;
    this.users = users;
  }

  public Likes(Recipes recipe, Users users) {
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
        ", recipe=" + recipe +
        ", users=" + users +
        '}';
  }
}