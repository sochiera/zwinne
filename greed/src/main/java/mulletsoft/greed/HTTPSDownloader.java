package mulletsoft.greed;

import java.io.StringWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

public class HTTPSDownloader implements Downloader {
  
  private String address;
  private String file;
  
  private Exception error = null;
  private String result;
  
  public HTTPSDownloader(String address, String file) {
    this.address = address;
    this.file = file;
  }

  public void run() {
    String path = address + "/" + file;
    try{
      URL url = new URL(path);
      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
    
      StringWriter sw = new StringWriter();
      IOUtils.copy(conn.getInputStream(), sw);
      result = sw.toString();
    }
    catch(Exception e){
      error = e;
    }
  }

  public String getResult() {
    return result;
  }

  public Exception getError() {
    return error;
  }

  public boolean wasSuccessful() {
    return error == null;
  }
}