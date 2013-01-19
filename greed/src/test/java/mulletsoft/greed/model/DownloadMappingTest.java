package mulletsoft.greed.model;

import org.hibernate.classic.Session;

public class DownloadMappingTest extends MappingTest {

  protected void setUp() throws Exception {
    super.setUp();
    
    clearTable("Download");
    clearTable("User");
    clearTable("Source");
  }

  public void testMapping(){
    Source source = new Source();
    source.setAddress(" http://www.google.pl");
    
    User user = new User();
    user.setName("name");
    user.setPasswordHash("abc");
    user.setSalt("abcd");
    
    Download download = new Download();
    download.setUser(user);
    download.setSource(source);
    download.setPath("/home/krzychu/");
    download.setCurrentTime();

    Session s = getSessionFactory().openSession();
    s.save(user);
    s.save(source);
    s.save(download);
    s.close();
    
    s = getSessionFactory().openSession();
    Download fromDB = (Download) s.load(Download.class, download.getId());
    assertEquals(user.getId(), fromDB.getUser().getId());
    assertEquals(source.getId(), fromDB.getSource().getId());
    s.close();
  }
}
