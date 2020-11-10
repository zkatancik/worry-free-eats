package Dao;

import Model.CookingDirects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CookingDirectsDao {
  protected ConnectionManager connectionManager;
  private static CookingDirectsDao instance = null;
  protected CookingDirectsDao() {
    connectionManager = new ConnectionManager();
  }

  public static CookingDirectsDao getInstance() {
    if (instance == null) {
      instance = new CookingDirectsDao();
    }
    return instance;
  }

  public CookingDirects create(CookingDirects cookingDirect) throws SQLException {
    String insertCookingDirect = "INSERT INTO CookingDirects(RecipeId, CookingTime) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCookingDirect, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, cookingDirect.getRecipeId());
      insertStmt.setString(2, cookingDirect.getCookingTime());
      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int directId = -1;
      if (resultKey.next()) {
        directId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      cookingDirect.setDirectId(directId);
      return cookingDirect;
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

  public CookingDirects getCookingDirectsById(int directId) throws SQLException {
    String getDirect = "SELECT DirectId,RecipeId,CookingTime FROM CookingDirects WHERE DirectId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;
    CookingDirects cookingDirect = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(getDirect);
      selectStmt.setInt(1, directId);
      resultSet = selectStmt.executeQuery();
      if (resultSet.next()) {
        int resultDirectId = resultSet.getInt("DirectId");
        int recipeId = resultSet.getInt("RecipeId");
        String cookingTime = resultSet.getString("CookingTime");
        cookingDirect = new CookingDirects(resultDirectId, recipeId, cookingTime);
      }
      return cookingDirect;
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

  public CookingDirects getCookingDirectsByRecipeId(int recipeId) throws SQLException {
    String getDirect = "SELECT DirectId,RecipeId,CookingTime FROM CookingDirects WHERE RecipeId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;
    CookingDirects cookingDirect = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(getDirect);
      selectStmt.setInt(1, recipeId);
      resultSet = selectStmt.executeQuery();
      if (resultSet.next()) {
        int directId = resultSet.getInt("DirectId");
        int resultRecipeId = resultSet.getInt("RecipeId");
        String cookingTime = resultSet.getString("CookingTime");
        cookingDirect = new CookingDirects(directId, resultRecipeId, cookingTime);
      }
      return cookingDirect;
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

  public CookingDirects delete(CookingDirects cookingDirect) throws SQLException {
    String deleteCookingDirect = "DELETE FROM CookingDirects WHERE DirectId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteCookingDirect);
      deleteStmt.setInt(1, cookingDirect.getDirectId());
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
