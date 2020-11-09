package Model;

public class UserAllergies {
  private Integer id;
  private Integer allergiesTypesId;
  private String userName;


  public UserAllergies(Integer id, String userName, Integer allergiesTypesId) {
    this.allergiesTypesId = allergiesTypesId;
    this.userName = userName;
  }

  public UserAllergies(String userName, Integer allergiesTypesId) {
    this.allergiesTypesId = allergiesTypesId;
    this.userName = userName;
  }

  public UserAllergies(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAllergiesTypesId() {
    return allergiesTypesId;
  }

  public void setAllergiesTypesId(Integer allergiesTypesId) {
    this.allergiesTypesId = allergiesTypesId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
