package mulletsoft.greed;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

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
    downloader = new HTTPSDownloader("localhost", "data.csv");
    downloader.run();
    assertTrue(downloader.wasSuccessful());
    assertEquals(data, downloader.getResult());
  }
  
  
  public void testReportsBadURL(){
    downloader = new HTTPSDownloader("not_existing_host", "data.csv");
    downloader.run();
    assertFalse(downloader.wasSuccessful());
    assertEquals(java.net.UnknownHostException.class, downloader.getError().getClass());
  }
  
  
  
}
