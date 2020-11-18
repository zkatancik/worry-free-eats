package Model;

public class UserAllergies {
  private Integer id;
  private Integer allergiesTypesId;
  private Users userName;


  public UserAllergies(Integer id, Users userName, Integer allergiesTypesId) {
    this.allergiesTypesId = allergiesTypesId;
    this.userName = userName;
  }

  public UserAllergies(Users userName, Integer allergiesTypesId) {
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

  public Users getUserName() {
    return userName;
  }

  public void setUserName(Users userName) {
    this.userName = userName;
  }
}
