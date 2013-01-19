package mulletsoft.greed.net;

import mulletsoft.greed.model.Download;
import mulletsoft.greed.model.Source;

public class DownloaderFactory {
  
  public static Downloader makeDownloader(Download download){
    Source source = download.getSource();
    
    if(source.getProtocol().equals("HTTP")){
      return new HTTPDownloader(download);
    } 
    else if(source.getProtocol().equals("HTTPS")){
      return new HTTPSDownloader(download);
    }
    else if(source.getProtocol().equals("FTP")){
      return new FTPDownloader(download);
    }
    
    return null;
  }
}
