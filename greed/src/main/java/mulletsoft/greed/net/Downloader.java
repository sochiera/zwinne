package mulletsoft.greed.net;

public interface Downloader extends Runnable{
  public String getResult();
  public Exception getError();
  public boolean wasSuccessful();
}
