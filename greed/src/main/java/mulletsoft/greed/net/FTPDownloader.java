package mulletsoft.greed.net;

import java.io.InputStream;
import java.io.StringWriter;

import mulletsoft.greed.model.Download;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;


public class FTPDownloader extends Downloader{

  public FTPDownloader(Download download){
    super(download);
  }
  
  public void run() {
    // prepare username and password
    String username = getDownload().getSource().getLogin();
    if(username == null || username.equals(""))
      username = "anonymous";
    
    String password = getDownload().getSource().getPassword();
    if(password == null)
      password = "";
    
    FTPClient client = new FTPClient();
    try{
      client.connect(getDownload().getSource().getAddress());
      client.login(username, password);
      
      InputStream is = client.retrieveFileStream(getDownload().getSource().getPath());
      if(is == null){
        throw new Exception(client.getReplyString());
      }
      
      StringWriter sw = new StringWriter();
      IOUtils.copy(is, sw);
      setResult(sw.toString());
      
      client.disconnect();
    }
    catch(Exception e){
      setError(e);
    }
  }
}
