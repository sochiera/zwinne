package mulletsoft.greed;

import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;



public class FTPDownloader implements Runnable{
  private String server;
  private String file;
  
  private String username = "anonymous";
  private String password = "";
  
  private String result;
  private boolean success = false;
  private String error = null;
  
  
  public FTPDownloader(String server, String file){
    this.server = server;
    this.file = file;
  }

  public FTPDownloader(String server, String file, String username, String password){
    this.server = server;
    this.file = file;
    this.username = username;
    this.password = password;
  }
  

  public String getServer() {
    return server;
  }

  public String getFile() {
    return file;
  }
  
  public String getResult(){
    return result;
  }
  
  public String getError(){
    return error;
  }
  
  public boolean wasSuccessful(){
    return success;
  }

  public void run() {
    FTPClient client = new FTPClient();
    success = false;
    try{
      client.connect(server);
      client.login(username, password);
      
      InputStream is = client.retrieveFileStream(file);
      if(is == null){
        throw new Exception(client.getReplyString());
      }
      
      StringWriter sw = new StringWriter();
      IOUtils.copy(is, sw);
      result = sw.toString();
      
      client.disconnect();
      success = true;
    }
    catch(Exception e){
      success = false;
      error = e.toString();
      System.out.println(error);
    }
  }
}
