package mulletsoft.greed.net;

import java.io.StringWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import mulletsoft.greed.model.Download;
import mulletsoft.greed.model.Source;

import org.apache.commons.io.IOUtils;

public class HTTPSDownloader extends Downloader {
  
  public HTTPSDownloader(Download download) {
    super(download);
  }

  public void run() {
    Source s = getDownload().getSource();
    String path = "https://" + s.getAddress() + "/" + s.getPath();
    try{
      URL url = new URL(path);
      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
    
      StringWriter sw = new StringWriter();
      IOUtils.copy(conn.getInputStream(), sw);
      setResult(sw.toString());
    }
    catch(Exception e){
      setError(e);
    }
  }
}