package mulletsoft.greed.model;

public class User {
  private Integer id;
  private String name;
  private String passwordHash;
  private String salt;
  
  public Integer getId() {
    return id;
  }
  
  protected void setId(Integer id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getPasswordHash() {
    return passwordHash;
  }
  
  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }
  
  public String getSalt() {
    return salt;
  }
  
  public void setSalt(String salt) {
    this.salt = salt;
  }
}
