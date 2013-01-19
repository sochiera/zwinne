package mulletsoft.greed.model;

import org.hibernate.Session;

public class SourceMappingTest extends MappingTest {

  protected void setUp() throws Exception {
    super.setUp();
    clearTable("Source");
  }

  public void testMapping() throws Exception {
    
    Source source = new Source();
    source.setAddress("www.google.pl");
    source.setProtocol("http");
    source.setPath("a/b/c");
    source.setLogin("test");
    source.setPassword("pass");
    
    Session s = getSessionFactory().openSession();
    s.save(source);
    s.close();
    
    assertNotNull(source.getId());
    
    // load
    s = getSessionFactory().openSession();
    Source fromDB = (Source) s.load(Source.class, source.getId());
    
    assertEquals(source.getAddress(), fromDB.getAddress());
    assertEquals(source.getLogin(), fromDB.getLogin());
    assertEquals(source.getPassword(), fromDB.getPassword());
    assertEquals(source.getPath(), fromDB.getPath());
    assertEquals(source.getProtocol(), fromDB.getProtocol());
    s.close();
  }

}
