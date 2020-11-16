package Dao;

import Model.Nutritions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

  public Nutritions getNutritionByRecipeId(Integer recipeId) throws SQLException {
    String get = "SELECT * FROM Nutritions WHERE RecipeId=?;";
    Connection connection = null;
    PreparedStatement getStmt = null;
    ResultSet result = null;
    try {
      connection = connectionManager.getConnection();
      getStmt = connection.prepareStatement(get);
      getStmt.setInt(1, recipeId);

      result = getStmt.executeQuery();
      if (result.next()) {
        BigDecimal calorie = result.getBigDecimal("Calories");
        BigDecimal fat = result.getBigDecimal("Fat");
        BigDecimal carbon = result.getBigDecimal("Carbohydrates");
        BigDecimal protein = result.getBigDecimal("Protein");
        BigDecimal choles = result.getBigDecimal("Cholesterol");
        BigDecimal sodium = result.getBigDecimal("Sodium");
        BigDecimal fiber = result.getBigDecimal("Fiber");

        return new Nutritions(RecipesDao.getInstance().getRecipeById(recipeId), calorie, fat, carbon
        , protein, choles, sodium, fiber);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (getStmt != null) {
        getStmt.close();
      }
      if (result != null) {
        result.close();
      }
    }
    return null;
  }

  public List<Nutritions> getNutritionByAmount(String type, BigDecimal amount) throws SQLException {
    String get = "SELECT * FROM Nutritions WHERE ? >= ?;";
    Connection connection = null;
    PreparedStatement getStmt = null;
    ResultSet result = null;
    List<Nutritions> list = new ArrayList<>();
    try {
      connection = connectionManager.getConnection();
      getStmt = connection.prepareStatement(get);
      getStmt.setString(1,type);
      getStmt.setBigDecimal(2, amount);

      result = getStmt.executeQuery();
      while (result.next()) {
        Integer id = result.getInt(1);
        BigDecimal calorie = result.getBigDecimal("Calories");
        BigDecimal fat = result.getBigDecimal("Fat");
        BigDecimal carbon = result.getBigDecimal("Carbohydrates");
        BigDecimal protein = result.getBigDecimal("Protein");
        BigDecimal choles = result.getBigDecimal("Cholesterol");
        BigDecimal sodium = result.getBigDecimal("Sodium");
        BigDecimal fiber = result.getBigDecimal("Fiber");

        list.add(new Nutritions(RecipesDao.getInstance().getRecipeById(id), calorie, fat, carbon
            , protein, choles, sodium, fiber));
      }
      return list;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (getStmt != null) {
        getStmt.close();
      }
      if (result != null) {
        result.close();
      }
    }
  }

  public Nutritions updateNutritions(Integer recipeId, String type, BigDecimal newValue)
      throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    String update = "UPDATE Nutritions SET ?=? WHERE RecipeId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(update);
      updateStmt.setInt(1, recipeId);
      updateStmt.setString(2, type);
      updateStmt.setBigDecimal(3, newValue);

      updateStmt.executeUpdate();
      Nutritions nutrition = this.getNutritionByRecipeId(recipeId);
      String methodName = "set" + type;
      Method setMethod = nutrition.getClass().getMethod(methodName, BigDecimal.class);
      setMethod.invoke(nutrition, newValue);
      return nutrition;
    } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  public Nutritions delete(Nutritions nutrition) throws SQLException {
    String delete = "DELETE FROM Nutritions WHERE NutritionId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(delete);
      deleteStmt.setInt(1, nutrition.getRecipe().getRecipeId());
      deleteStmt.executeUpdate();

      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }
}
