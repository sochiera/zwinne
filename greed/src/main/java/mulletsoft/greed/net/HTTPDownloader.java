package mulletsoft.greed.net;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class HTTPDownloader implements Downloader{

  private String address;
  private String file;
  
  private Exception error = null;
  private String result = null;
   
  public HTTPDownloader(String address, String file) {
    this.address = "http://" + address;
    this.file = file;
  }

  public void run() {
    try{
      URL url = new URL(address + "/" + file);
      InputStream is = url.openConnection().getInputStream();
      StringWriter writer = new StringWriter();
      IOUtils.copy(is, writer);
      result = writer.toString();
    }
    catch(Exception e){
      error = e;
      result = null;
    }
  }

  public String getResult() {
    return result;
  }

  public Exception getError() {
    return error;
  }

  public boolean wasSuccessful() {
    return result != null;
  }

}