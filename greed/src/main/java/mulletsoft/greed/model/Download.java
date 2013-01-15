package mulletsoft.greed.model;

import java.sql.Timestamp;
import java.util.Date;

public class Download {
  private Integer id;
  private Source source;
  private Timestamp downloadTime;
  private User user;
  private String path;
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public Source getSource() {
    return source;
  }
  
  public void setSource(Source source) {
    this.source = source;
  }
  
  public Timestamp getDownloadTime() {
    return downloadTime;
  }
  
  public void setDownloadTime(Timestamp downloadTime) {
    this.downloadTime = downloadTime;
  }
  
  public void setCurrentTime(){
    Date d = new Date();
    downloadTime = new Timestamp(d.getTime());
  }
  
  public User getUser() {
    return user;
  }
  
  public void setUser(User user) {
    this.user = user;
  }
  
  public String getPath() {
    return path;
  }
  
  public void setPath(String path) {
    this.path = path;
  }
}
