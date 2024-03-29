package mulletsoft.greed.net;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import mulletsoft.greed.gui.ApplicationContext;
import mulletsoft.greed.model.Download;


public class DownloadManager implements Runnable{
 
  private HashMap<Thread, Downloader> threadToDownloader = new HashMap<Thread, Downloader>();
  private LinkedList<Thread> activeDownloaders = new LinkedList<Thread>();
  private ReentrantLock activeDownloadsLock = new ReentrantLock();
  
  private ApplicationContext appContext;
  
  public DownloadManager(ApplicationContext appContext){
    this.appContext = appContext;
  }
  
  
  public synchronized void startDownload(Download download){
    Downloader downloader = DownloaderFactory.makeDownloader(download);
    Thread thread = new Thread(downloader);
    
    activeDownloadsLock.lock();
    activeDownloaders.add(thread);
    threadToDownloader.put(thread, downloader);
    activeDownloadsLock.unlock();
    thread.start();
  }
  
  public void finishDownload(Downloader downloader){
    downloader.saveResult();
    Download download = downloader.getDownload();
    
    // save download to database
    appContext.openSession();
    appContext.save(download);
    appContext.closeSession();
  }
  
  public void run(){
    while(true){
      
      // check active downloaders
      activeDownloadsLock.lock();
      Iterator<Thread> itr = activeDownloaders.iterator();
      while(itr.hasNext()){
        Thread current = itr.next();
        if(!current.isAlive()){
          Downloader finishedDownloader = threadToDownloader.get(current);
          finishDownload(finishedDownloader);
          threadToDownloader.remove(current);
          itr.remove();
        }
      }
      activeDownloadsLock.unlock();
      
      
      try{
        Thread.sleep(100);
      }
      catch(InterruptedException e){
        return;
      }
    }
  }
  
  public List<Download> getActiveDownloads(){
    List<Download> active = new LinkedList<Download>();
    activeDownloadsLock.lock();
    for(Thread d : activeDownloaders){
      active.add(threadToDownloader.get(d).getDownload());
    } 
    activeDownloadsLock.unlock();
    return active;
  }


  public void setContext(ApplicationContext appContext) {
    this.appContext = appContext;
  }
}