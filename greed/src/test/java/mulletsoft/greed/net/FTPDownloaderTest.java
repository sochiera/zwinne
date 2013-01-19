package mulletsoft.greed.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import mulletsoft.greed.model.Download;
import mulletsoft.greed.model.Source;
import mulletsoft.greed.net.FTPDownloader;

import org.apache.commons.io.IOUtils;

import junit.framework.TestCase;

public class FTPDownloaderTest extends TestCase {

  public void testRun() throws IOException {

    Source s = new Source();
    s.setProtocol("ftp");
    s.setAddress("localhost");
    s.setPath("zwinne/data.csv");
    Download download = new Download();
    download.setSource(s);
    
    FTPDownloader downloader = new FTPDownloader(download);
    downloader.run();
    if(!downloader.wasSuccessful())
      System.out.println(downloader.getError());
    
    assertTrue(downloader.wasSuccessful());
    
    String f = downloader.getResult();
    
    InputStream local = this.getClass().getResourceAsStream("/test_data.csv");

    StringWriter sw = new StringWriter();
    IOUtils.copy(local, sw);
   
    assertEquals(sw.toString(), f);
  }
  
  
  public void testReportsBadURL(){
    Source s = new Source();
    s.setProtocol("ftp");
    s.setAddress("hogwart");
    s.setPath("zwinne/data.csv");
    Download download = new Download();
    download.setSource(s);
    
    FTPDownloader downloader = new FTPDownloader(download);
    downloader.run();
    
    assertFalse(downloader.wasSuccessful());
    assertEquals(java.net.UnknownHostException.class, downloader.getError().getClass());
  }
}
