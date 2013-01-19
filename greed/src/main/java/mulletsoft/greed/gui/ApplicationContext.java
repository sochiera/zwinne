package mulletsoft.greed.gui;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import mulletsoft.greed.model.User;
import mulletsoft.greed.net.DownloadManager;

public class ApplicationContext {
  private User currentUser;
  private DownloadManager downloadManager;
  private SessionFactory sessionFactory;
  
  private ReentrantLock dbLock = new ReentrantLock();
  private Session session = null;

  public ApplicationContext(DownloadManager downloadManager) {
    this.downloadManager = downloadManager;
    
    Configuration configuration = new Configuration();
    configuration.configure("/hibernate.cfg.xml");
    this.sessionFactory = configuration.buildSessionFactory();
    
    Session session = sessionFactory.openSession();
    Query q = session.createQuery("from User where id = 1");
    this.currentUser = (User) q.list().get(0);
    session.update(this.currentUser);
    session.close();
  }
  
  public User getCurrentUser() {
    return currentUser;
  }
  
  public DownloadManager getDownloadManager() {
    return downloadManager;
  }
  
  public void openSession(){
    dbLock.lock();
    session = sessionFactory.openSession();
    session.beginTransaction();
  }
  
  public void closeSession(){
    session.getTransaction().commit();
    session.close();
    dbLock.unlock();
  }
  
  public void save(Object o){
    session.save(o);
  }
  
  public void update(Object o){
    session.update(o);
  }
  
  public void delete(Object o){
    session.delete(o);
  }
  
  public List getAll(@SuppressWarnings("rawtypes") Class c){
    return session.createCriteria(c).list();
  }

  public void setCurrentUser(User current) {
    this.currentUser = current;
  }
}
