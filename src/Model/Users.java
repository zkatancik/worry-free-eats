package Model;

import java.sql.Timestamp;

public class Users {
  private Integer userId;
  private String userName;
  private String password;
  private String name;
  private String email;
  private Timestamp createdTime;
  private Timestamp lastModifiedTime;
  private Timestamp lastLogin;

  public Users(String userName, String password, String name, String email,
      Timestamp createdTime, Timestamp lastModifiedTime, Timestamp lastLogin) {
    this.userName = userName;
    this.password = password;
    this.name = name;
    this.email = email;
    this.createdTime = createdTime;
    this.lastModifiedTime = lastModifiedTime;
    this.lastLogin = lastLogin;
  }

  public Users(Integer userId, String userName, String password, String name, String email,
      Timestamp createdTime, Timestamp lastModifiedTime, Timestamp lastLogin) {
    this.userId = userId;
    this.userName = userName;
    this.password = password;
    this.name = name;
    this.email = email;
    this.createdTime = createdTime;
    this.lastModifiedTime = lastModifiedTime;
    this.lastLogin = lastLogin;
  }

  public Users(Integer userId) {
    this.userId = userId;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Timestamp getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Timestamp createdTime) {
    this.createdTime = createdTime;
  }

  public Timestamp getLastModifiedTime() {
    return lastModifiedTime;
  }

  public void setLastModifiedTime(Timestamp lastModifiedTime) {
    this.lastModifiedTime = lastModifiedTime;
  }

  public Timestamp getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(Timestamp lastLogin) {
    this.lastLogin = lastLogin;
  }
}


