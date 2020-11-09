package Dao;

import Model.Recipes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
