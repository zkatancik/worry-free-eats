package Dao;

import Model.Recipes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class RecipesDao {
  protected ConnectionManager connectionManager;
  private static RecipesDao instance = null;
  protected RecipesDao() {
    connectionManager = new ConnectionManager();
  }

  public static RecipesDao getInstance() {
    if (instance == null) {
      instance = new RecipesDao();
    }
    return instance;
  }

  public Recipes create(Recipes recipes) throws SQLException {
    String insert = "INSERT INTO Recipe(RecipeId, RecipeName, ImageUrl) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insert);
      insertStmt.setInt(1, recipes.getRecipeId());
      insertStmt.setString(2, recipes.getRecipeName());
      insertStmt.setString(3, recipes.getImageUrl());

      insertStmt.executeUpdate();
      return recipes;
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
    }
  }

  public Recipes getRecipeById(int recipeId) throws SQLException {
    String getRecipe = "SELECT RecipeId,RecipeName,ImageUrl FROM Recipe WHERE RecipeId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;
    Recipes recipe = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(getRecipe);
      selectStmt.setInt(1, recipeId);
      resultSet = selectStmt.executeQuery();
      if (resultSet.next()) {
        int resultRecipeId = resultSet.getInt("RecipeId");
        String recipeName = resultSet.getString("RecipeName");
        String imageUrl = resultSet.getString("ImageUrl");
        recipe = new Recipes(resultRecipeId, recipeName, imageUrl);
      }
      return recipe;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
    }
  }

  public List<Recipes> getRecipesByKeyword(String keyword, int pageIndex, int pageSize) throws SQLException {
    String getRecipe = "SELECT RecipeId,RecipeName,ImageUrl FROM Recipe WHERE INSTR(RecipeName, ?) LIMIT ?, ?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;
    List<Recipes> resultList = new LinkedList<>();
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(getRecipe, ResultSet.TYPE_SCROLL_INSENSITIVE);
      selectStmt.setString(1, keyword);
      selectStmt.setInt(2, pageIndex);
      selectStmt.setInt(3, pageSize);
      resultSet = selectStmt.executeQuery();

      while (resultSet.next()) {
        int recipeId = resultSet.getInt("RecipeId");
        String recipeName = resultSet.getString("RecipeName");
        String imageUrl = resultSet.getString("ImageUrl");
        resultList.add(new Recipes(recipeId, recipeName, imageUrl));
      }
      return resultList;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
    }
  }

  public int getTotalRecipesCnt(String keyword) throws SQLException {
    String getCnt = "SELECT COUNT(*) FROM Recipe WHERE INSTR(RecipeName, ?)";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;
    int cnt = 0;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(getCnt);
      selectStmt.setString(1, keyword);
      resultSet = selectStmt.executeQuery();

      if (resultSet.next()) {
        cnt = resultSet.getInt(1);
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
    }
    return cnt;
  }

  public Recipes updateImageUrl(Recipes recipe, String newImageUrl) throws SQLException {
    String updateRecipe = "UPDATE Recipes SET ImageUrl=? WHERE RecipeId = ?;";
    Connection connection = connectionManager.getConnection();
    PreparedStatement preparedStatement = null;
    try {
      connection = connectionManager.getConnection();
      preparedStatement = connection.prepareStatement(updateRecipe);
      preparedStatement.setString(1, newImageUrl);
      preparedStatement.setInt(2, recipe.getRecipeId());
      preparedStatement.executeUpdate();
      return recipe;
    } catch(SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection!=null) {
        connection.close();
      }
      if(preparedStatement!=null) {
        preparedStatement.close();
      }
    }
  }

  public Recipes delete(Recipes recipe) throws SQLException {
    String deleteRecipe = "DELETE FROM Recipes WHERE RecipeId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteRecipe);
      deleteStmt.setInt(1, recipe.getRecipeId());
      int affectedRows = deleteStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("No records available to delete for recipeId="+recipe.getRecipeId());
      }
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

  public Recipes delete(int recipeId) throws SQLException {
    String deleteRecipe = "DELETE FROM Recipes WHERE RecipeId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteRecipe);
      deleteStmt.setInt(1, recipeId);
      int affectedRows = deleteStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("No records available to delete for recipeId="+recipeId);
      }
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
