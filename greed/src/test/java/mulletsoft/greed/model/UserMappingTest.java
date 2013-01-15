package mulletsoft.greed.model;

import org.hibernate.classic.Session;

public class UserMappingTest extends MappingTest {

 
  protected void setUp() throws Exception {
    super.setUp();
    clearTable("User");
  }

  public void testMapping() throws Exception{

    User u = new User();
    u.setName("me");
    u.setPasswordHash("abcd");
    u.setSalt("efgh");
  
    Session s = getSessionFactory().openSession();
    s.save(u);
    s.close();
    
    s = getSessionFactory().openSession();
    User found = (User) s.load(User.class, u.getId());
    
    assertEquals(u.getId(), found.getId());
    assertEquals(u.getName(), found.getName());
    assertEquals(u.getPasswordHash(), found.getPasswordHash());
    assertEquals(u.getSalt(), found.getSalt());
    s.close();
  }
}
  