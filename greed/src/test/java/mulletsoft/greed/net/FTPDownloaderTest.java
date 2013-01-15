package mulletsoft.greed.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import mulletsoft.greed.net.FTPDownloader;

import org.apache.commons.io.IOUtils;

import junit.framework.TestCase;

public class FTPDownloaderTest extends TestCase {

  public void testRun() throws IOException {

    FTPDownloader downloader = new FTPDownloader("localhost", "zwinne/data.csv");
    downloader.run();
    assertTrue(downloader.wasSuccessful());
    
    String f = downloader.getResult();
    
    InputStream local = this.getClass().getResourceAsStream("/test_data.csv");

    StringWriter sw = new StringWriter();
    IOUtils.copy(local, sw);
   
    assertEquals(sw.toString(), f);
  }
  
  
  public void testReportsBadURL(){
    FTPDownloader downloader = new FTPDownloader("not_existing_host", "zwinne/data.csv");
    downloader.run();
    
    assertFalse(downloader.wasSuccessful());
    assertEquals(java.net.UnknownHostException.class, downloader.getError().getClass());
  }
}
