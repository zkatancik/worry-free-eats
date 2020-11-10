package Dao;

import Model.Likes;
import Model.Recipes;
import Model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LikesDao {

  protected ConnectionManager connectionManager;

  private static LikesDao instance = null;

  protected LikesDao() {
    connectionManager = new ConnectionManager();
  }

  public static LikesDao getInstance() {
    if (instance == null) {
      instance = new LikesDao();
    }
    return instance;
  }

  public Likes create(Likes likes) throws SQLException {
    String insertLikes =
        "INSERT INTO Favorites(RecipeID,UserId) " +
            "VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertLikes,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, likes.getRecipe().getRecipeId());
      insertStmt.setInt(2, likes.getUsers().getUserId());
      insertStmt.executeUpdate();

      // Retrieve the auto-generated key and set it, so it can be used by the caller.
      resultKey = insertStmt.getGeneratedKeys();
      int likeId = -1;
      if (resultKey.next()) {
        likeId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      likes.setLikeId(likeId);
      return likes;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
      if (resultKey != null) {
        resultKey.close();
      }
    }
  }

  public Likes getLikesById(int likesId) throws SQLException {
    String selectLikes =
        "SELECT LikeId,RecipeID,UserId " +
            "FROM Likes " +
            "WHERE LikeId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectLikes);
      selectStmt.setInt(1, likesId);
      results = selectStmt.executeQuery();
      RecipesDao recipesDao = RecipesDao.getInstance();
      UsersDao usersDao = UsersDao.getInstance();
      if(results.next()) {
        int resultLikeId = results.getInt("LikeId");
        int recipeID = results.getInt("RecipeID");
        int userId = results.getInt("UserId");

        Recipes recipe = recipesDao.getRecipeById(recipeID);
        Users users = usersDao.getUsersById(userId);
        Likes likes = new Likes(resultLikeId, recipe, users);
        return likes;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }

  public List<Likes> getLikesForUser(int userId) throws SQLException {
    List<Likes> likesList = new ArrayList<Likes>();
    String selectLikes =
        "SELECT LikeId,RecipeID,UserId " +
            "FROM Likes " +
            "WHERE UserId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectLikes);
      selectStmt.setInt(1, userId);
      results = selectStmt.executeQuery();
      RecipesDao recipesDao = RecipesDao.getInstance();
      UsersDao usersDao = UsersDao.getInstance();
      while(results.next()) {
        int likeId = results.getInt("LikeId");
        int recipeID = results.getInt("RecipeID");

        Recipes recipe = recipesDao.getRecipeById(recipeID);
        Users users = usersDao.getUsersById(userId);
        Likes likes = new Likes(likeId, recipe, users);
        likesList.add(likes);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return likesList;
  }

  public Likes updateLikes(Likes likes, int recipeId) throws SQLException {
    String updateLikes = "UPDATE Likes SET RecipeID=? WHERE LikeId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateLikes);
      updateStmt.setInt(1, recipeId);
      updateStmt.setInt(2, likes.getLikeId());
      updateStmt.executeUpdate();
      RecipesDao recipesDao = RecipesDao.getInstance();
      Recipes recipe = recipesDao.getRecipeById(recipeId);
      likes.setRecipe(recipe);
      return likes;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  public Likes delete(Likes likes) throws SQLException {
    String deleteLikes = "DELETE FROM Likes WHERE LikeId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteLikes);
      deleteStmt.setInt(1, likes.getLikeId());
      deleteStmt.executeUpdate();
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }
}