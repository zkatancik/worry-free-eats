package Dao;

import Model.UserAllergies;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserAllergiesDao {
  protected ConnectionManager connectionManager;


  private static UserAllergiesDao instance = null;


  protected UserAllergiesDao() {
    connectionManager = new ConnectionManager();
  }
  public static UserAllergiesDao getInstance() {
    if(instance == null) {
      instance = new UserAllergiesDao();
    }
    return instance;
  }




  /**
   * Create UserAllergies
   * This runs a INSERT statement.
   */
  public UserAllergies create(UserAllergies userAllergies) throws SQLException {
    String insertUserAllergies = "INSERT INTO Users(ID, UserAllergiesId, UserName) "
        + "VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertUserAllergies,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(2, userAllergies.getUserName());
      insertStmt.setInt(1, userAllergies.getAllergiesTypesId());
      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int userAllergiesId = -1;
      if(resultKey.next()) {
        userAllergiesId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      userAllergies.setId(userAllergiesId);
      return userAllergies;
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
    }
  }


  /**
   * Returns a list of UserAllergies
   * This runs a SELECT statement.
   */

  public List<UserAllergies> getUserAllergiesByUserName(String userName)
      throws SQLException {
    List<UserAllergies> userAllergiesList = new ArrayList<>();
    String selectUserAllergies =
        "SELECT ID,UserName, AllergyTypesId FROM UserAllergies WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUserAllergies);
      selectStmt.setString(1, userName);
      results = selectStmt.executeQuery();
      while(results.next()) {
        Integer id = results.getInt("ID");
        String resultUserName = results.getString("UserName");
        Integer allergyTypesId = results.getInt("AllergyTypesId");
        UserAllergies userAllergies = new UserAllergies(id, resultUserName, allergyTypesId);
        userAllergiesList.add(userAllergies);
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
    return userAllergiesList;
  }



  /**
   * Get a UserAllergy by Id
   * This runs a SELECT statement and returns a single User instance.
   */

  public UserAllergies getUsersById(int userId) throws SQLException {
    String selectUserAllergiesId =
        "SELECT ID,UserName,AllergyTypeID" +
            "FROM UserAllergies "
            + "WHERE AllergyTypesId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUserAllergiesId);
      selectStmt.setInt(1, userId);
      results = selectStmt.executeQuery();

      if(results.next()) {
        Integer resultUserId = results.getInt("ID");
        String userName = results.getString("UserName");
        Integer allergyTypesId = results.getInt("AllergyTypesId");
        UserAllergies userAllergiesId = new UserAllergies(resultUserId, userName, allergyTypesId);

        return userAllergiesId;
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





  /**
   * Delete the UserAllergies instance.
   * This runs a DELETE statement.
   */

  public UserAllergies delete(UserAllergies userAllergies) throws SQLException {
    String deleteUserAllergies = "DELETE FROM UserAllergies WHERE ID=?";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteUserAllergies);
      deleteStmt.setInt(1, userAllergies.getId());
      deleteStmt.executeUpdate();

      // Return null so the caller can no longer operate on the User instance.
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




