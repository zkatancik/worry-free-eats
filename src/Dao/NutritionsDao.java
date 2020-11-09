package Dao;

import Model.Nutritions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NutritionsDao {
  protected ConnectionManager connectionManager;
  private static NutritionsDao instance = null;
  protected NutritionsDao() {
    connectionManager = new ConnectionManager();
  }

  public static NutritionsDao getInstance() {
    if (instance == null) {
      instance = new NutritionsDao();
    }
    return instance;
  }

  public Nutritions create(Nutritions nutritions) throws SQLException {
    String insert =
        "INSERT INTO Nutritions(RecipeId, Calories, Fat, Carbohydrates, Protein, Cholesterol, Sodium, Fiber)"
            + " VALUES(?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet result = null;
    try{
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, nutritions.getRecipe().getRecipeId());
      insertStmt.setBigDecimal(2, nutritions.getCalories());
      insertStmt.setBigDecimal(3, nutritions.getFat());
      insertStmt.setBigDecimal(4, nutritions.getCarbohydrates());
      insertStmt.setBigDecimal(5, nutritions.getProtein());
      insertStmt.setBigDecimal(6, nutritions.getCholesterol());
      insertStmt.setBigDecimal(7, nutritions.getSodium());
      insertStmt.setBigDecimal(8, nutritions.getFiber());

      insertStmt.executeUpdate();
      result = insertStmt.getGeneratedKeys();
      int id = -1;
      if (result.next()) {
        id = result.getInt(1);
      }
      nutritions.setNutritionId(id);
      return nutritions;
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
      if (result != null) {
        result.close();
      }
    }

  }
}
