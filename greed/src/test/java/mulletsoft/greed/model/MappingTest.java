package mulletsoft.greed.model;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import junit.framework.TestCase;

public class MappingTest extends TestCase {

  private Configuration configuration;
  private SessionFactory sessionFactory;
  
  public Configuration getConfiguration() {
    return configuration;
  }
  
  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }
  
  protected void setUp() throws Exception {
    configuration = new Configuration();
    configuration.configure("/hibernate.cfg.xml");
    sessionFactory = configuration.buildSessionFactory(); 
  }
  
  protected void clearTable(String name) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Query q = session.createQuery("delete from " + name);
    q.executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
}
