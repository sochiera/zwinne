package mulletsoft.greed.model;

public class Source {
  private Integer id;
  private String protocol;
  private String address;
  private String path;
  private String login;
  private String password;
  
  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }
  
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
  
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getLogin() {
    return login;
  }
  
  public void setLogin(String login) {
    this.login = login;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
}
