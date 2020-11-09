package Dao;

import Model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class UsersDao {

  protected ConnectionManager connectionManager;


  private static UsersDao instance = null;
  protected UsersDao() {
    connectionManager = new ConnectionManager();
  }
  public static UsersDao getInstance() {
    if(instance == null) {
      instance = new UsersDao();
    }
    return instance;
  }


  /**
   * Save the User instance by storing it in your MySQL instance.
   * This runs a INSERT statement.
   */
  public Users create(Users user) throws SQLException {
    String insertUser = "INSERT INTO Users(ID, UserName,Password,Name,Email,CreatedTime, "
        + "LastModifiedTime, LastLogin) VALUES(?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertUser,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt = connection.prepareStatement(insertUser);
      insertStmt.setString(1, user.getUserName());
      insertStmt.setString(2, user.getPassword());
      insertStmt.setString(3, user.getName());
      insertStmt.setString(5, user.getEmail());
      insertStmt.setTimestamp(6, user.getCreatedTime());
      insertStmt.setTimestamp(7, user.getLastModifiedTime());
      insertStmt.setTimestamp(8, user.getLastLogin());
      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int userId = -1;
      if(resultKey.next()) {
        userId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      user.setUserId(userId);
      return user;
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
   * Get the User record 
   * This runs a SELECT statement and returns a single User instance, By ID.
   */

  public Users getUsersById(int userId) throws SQLException {
    String selectUserId =
        "SELECT ID,UserName,Password,Name,Email,CreatedTime,LastModifiedTime,LastLogin" +
            "FROM Users "
            + "WHERE ID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUserId);
      selectStmt.setInt(1, userId);
      results = selectStmt.executeQuery();

      if(results.next()) {
        int resultUserId = results.getInt("ID");
        String userName = results.getString("UserName");
        String password = results.getString("Password");
        String name = results.getString("Name");
        String email = results.getString("Email");
        Timestamp createdTime = results.getTimestamp("CreatedTime");
        Timestamp LastModifiedTime = results.getTimestamp("LastModifiedTime");
        Timestamp LastLogin = results.getTimestamp("LastLogin");

        Users user = new Users(resultUserId, userName, password, name, email,
            createdTime, LastModifiedTime, LastLogin);

        return user;
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
   * Get the User record
   * This runs a SELECT statement and returns a single User instance, by UserName.
   */


  public Users getUserFromUserName(String userName) throws SQLException {
    String selectUser = "SELECT UserName FROM Users WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUser);
      selectStmt.setString(1, userName);
      results = selectStmt.executeQuery();

      if(results.next()) {
        String resultUserName = results.getString("UserName");
        String password = results.getString("Password");
        String name = results.getString("Name");
        String email = results.getString("Email");
        Timestamp createdTime = results.getTimestamp("CreatedTime");
        Timestamp lastModifiedTime = results.getTimestamp("LastModifiedTime");
        Timestamp lastLogin = results.getTimestamp("LastLogin");

        Users user = new Users(resultUserName, password, name, email, createdTime, lastModifiedTime,
            lastLogin);
        return user;
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
   * Update the User instance.
   * This runs a update on UserName statement.
   */

  public Users updateUserUsername(Users user, String newUserName) throws SQLException {
    String updateUserName = "UPDATE Users SET UserName=? WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateUserName);
      updateStmt.setString(1, newUserName);
      updateStmt.setString(1, user.getUserName());
      updateStmt.executeUpdate(newUserName);
      user.setUserName(newUserName);
      return user;

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



  /**
   * Delete the User instance.
   * This runs a DELETE statement.
   */
  public Users delete(Users user) throws SQLException {
    String deleteUser = "DELETE FROM User WHERE UserId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteUser);
      deleteStmt.setInt(1, user.getUserId());
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


