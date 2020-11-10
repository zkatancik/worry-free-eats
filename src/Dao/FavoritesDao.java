package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Favorites;
import Model.Recipes;
import Model.Users;

public class FavoritesDao {
	  protected ConnectionManager connectionManager;

	  private static FavoritesDao instance = null;
	  protected FavoritesDao() {
	    connectionManager = new ConnectionManager();
	  }
	  public static FavoritesDao getInstance() {
	    if(instance == null) {
	      instance = new FavoritesDao();
	    }
	    return instance;
	  }

	  public Favorites create(Favorites favorites) throws SQLException {
	    String insertFavorites =
	        "INSERT INTO Favorites(RecipeID,UserId) " +
	            "VALUES(?,?);";
	    Connection connection = null;
	    PreparedStatement insertStmt = null;
	    ResultSet resultKey = null;
	    try {
	      connection = connectionManager.getConnection();
	      insertStmt = connection.prepareStatement(insertFavorites,
	          Statement.RETURN_GENERATED_KEYS);
	      insertStmt.setInt(1, favorites.getRecipe().getRecipeId());
	      insertStmt.setInt(2, favorites.getUsers().getUserId());
	      insertStmt.executeUpdate();

	      // Retrieve the auto-generated key and set it, so it can be used by the caller.
	      resultKey = insertStmt.getGeneratedKeys();
	      int favoriteId = -1;
	      if(resultKey.next()) {
	        favoriteId = resultKey.getInt(1);
	      } else {
	        throw new SQLException("Unable to retrieve auto-generated key.");
	      }
	      favorites.setFavoriteId(favoriteId);
	      return favorites;
	    } catch (SQLException e) {
	      e.printStackTrace();
	      throw e;
	    } finally {
	      if(connection != null) {
	        connection.close();
	      }
	      if(insertStmt != null) {
	        insertStmt.close();
	      }
	      if(resultKey != null) {
	        resultKey.close();
	      }
	    }
	  }

	  public Favorites getFavoritesById(int favoriteId) throws SQLException {
	    String selectFavorites =
	        "SELECT FavoriteId,RecipeID,UserId " +
	            "FROM Favorites " +
	            "WHERE FavoriteId=?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	      connection = connectionManager.getConnection();
	      selectStmt = connection.prepareStatement(selectFavorites);
	      selectStmt.setInt(1, favoriteId);
	      results = selectStmt.executeQuery();
	      RecipesDao recipeDao = RecipesDao.getInstance();
	      UsersDao usersDao = UsersDao.getInstance();
	      if(results.next()) {
	        int resultFavoriteId = results.getInt("FavoriteId");
	        int recipeID = results.getInt("RecipeID");
	        int userId = results.getInt("UserId");
	        Recipes recipe = recipeDao.getRecipeById(recipeID);
	        Users users = usersDao.getUsersById(userId);
	        Favorites favorites = new Favorites(resultFavoriteId, recipe, users);
	        return favorites;
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

	  public List<Favorites> getFavoritesForUser(int userId) throws SQLException {
	    List<Favorites> favoritesList = new ArrayList<Favorites>();
	    String selectFavorites =
	        "SELECT FavoriteId,RecipeID,UserId " +
	            "FROM Favorites " +
	            "WHERE FavoriteId=?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	      connection = connectionManager.getConnection();
	      selectStmt = connection.prepareStatement(selectFavorites);
	      selectStmt.setInt(1, userId);
	      results = selectStmt.executeQuery();
	      RecipesDao recipeDao = RecipesDao.getInstance();
	      UsersDao usersDao = UsersDao.getInstance();
	      while(results.next()) {
	        int favoriteId = results.getInt("FavoriteId");
	        int recipeID = results.getInt("RecipeID");

	        Recipes recipe = recipeDao.getRecipeById(recipeID);
	        Users users = usersDao.getUsersById(userId);
	        Favorites favorites = new Favorites(favoriteId, recipe, users);
	        favoritesList.add(favorites);
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
	    return favoritesList;
	  }

	  public Favorites updateFavorites(Favorites favorites, int recipeId) throws SQLException {
	    String updateFavorites = "UPDATE Favorites SET RecipeID=? WHERE FavoriteId=?;";
	    Connection connection = null;
	    PreparedStatement updateStmt = null;
	    try {
	      connection = connectionManager.getConnection();
	      updateStmt = connection.prepareStatement(updateFavorites);
	      updateStmt.setInt(1, recipeId);
	      updateStmt.setInt(2, favorites.getFavoriteId());
	      updateStmt.executeUpdate();
	      RecipesDao recipesDao = RecipesDao.getInstance();
	      Recipes recipe = recipesDao.getRecipeById(recipeId);
	      favorites.setRecipe(recipe);
	      return favorites;
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

	  public Favorites delete(Favorites favorites) throws SQLException {
	    String deleteFavorites = "DELETE FROM Favorites WHERE FavoriteId=?;";
	    Connection connection = null;
	    PreparedStatement deleteStmt = null;
	    try {
	      connection = connectionManager.getConnection();
	      deleteStmt = connection.prepareStatement(deleteFavorites);
	      deleteStmt.setInt(1, favorites.getFavoriteId());
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