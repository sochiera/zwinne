package mulletsoft.greed.net;

import mulletsoft.greed.model.Download;

public abstract class Downloader implements Runnable{
  
  private String result = null;
  private Exception error = null;
  private Download download = null;
  
  public Downloader(Download d){
    download = d;
  }
  
  public String getResult() {
    return result;
  }
  
  public Exception getError() {
    return error;
  }
  
  public Download getDownload(){
    return download;
  }

  protected void setResult(String result) {
    this.result = result;
  }

  protected void setError(Exception error) {
    this.error = error;
  }

  protected void setDownload(Download download) {
    this.download = download;
  }
  
  public boolean wasSuccessful(){
    return error == null && result != null;
  }
  
  public void saveResult(){
    
  }
}
