package mulletsoft.greed.net;

import mulletsoft.greed.model.Source;

public class DownloaderFactory {
  public static Downloader makeDownloader(Source source){
    if(source.getProtocol().equals("http")){
      return new HTTPDownloader(source.getAddress(), source.getPath());
    } 
    else if(source.getProtocol().equals("https")){
      return new HTTPSDownloader(source.getAddress(), source.getPath());
    }
    else if(source.getProtocol().equals("ftp")){
      if(source.getLogin() == null || source.getLogin().equals(""))
        return new FTPDownloader(source.getAddress(), source.getPath());
      
      return new FTPDownloader(source.getAddress(), source.getPath(), 
          source.getLogin(), source.getPassword());
    }
    return null;
  }
}
