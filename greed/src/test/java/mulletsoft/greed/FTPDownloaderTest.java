package mulletsoft.greed;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

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
}
