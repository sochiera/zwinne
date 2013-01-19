package mulletsoft.greed.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import mulletsoft.greed.model.Download;
import mulletsoft.greed.model.Source;
import mulletsoft.greed.net.HTTPSDownloader;

import org.apache.commons.io.IOUtils;

import junit.framework.TestCase;

public class HTTPSDownloaderTest extends TestCase {
  
  HTTPSDownloader downloader;
  String data;
  
  
  public void setUp(){
    InputStream local = this.getClass().getResourceAsStream("/test_data.csv");
    StringWriter sw = new StringWriter();
    try{
      IOUtils.copy(local, sw);
    }
    catch(IOException e){
      e.printStackTrace();
    }
    data = sw.toString();
  }
  
  
  public void testDownloadingWorks(){
    Source s = new Source();
    s.setProtocol("https");
    s.setAddress("www.google.pl");
    s.setPath("");
    
    Download d = new Download();
    d.setSource(s);
    
    downloader = new HTTPSDownloader(d);
    downloader.run();
    assertTrue(downloader.wasSuccessful());
  }
  
  
  public void testReportsBadURL(){
    Source s = new Source();
    s.setProtocol("https");
    s.setAddress("www.a;dfjk;askldfj;aklsjdf.pk");
    s.setPath("");
    
    Download d = new Download();
    d.setSource(s);
    
    downloader = new HTTPSDownloader(d);
    downloader.run();
    assertFalse(downloader.wasSuccessful());
  }
  
  
  
}
