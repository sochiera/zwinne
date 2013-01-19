package mulletsoft.greed.net;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import mulletsoft.greed.model.Download;
import mulletsoft.greed.model.Source;

import org.apache.commons.io.IOUtils;

public class HTTPDownloader extends Downloader{
   
  public HTTPDownloader(Download d) {
    super(d);
  }

  public void run() {
    try{
      Source s = getDownload().getSource();
      URL url = new URL("http://" + s.getAddress() + "/" + s.getPath());
      InputStream is = url.openConnection().getInputStream();
      StringWriter writer = new StringWriter();
      IOUtils.copy(is, writer);
      setResult(writer.toString());
    }
    catch(Exception e){
      setError(e);
      setResult(null);
    }
  }
}