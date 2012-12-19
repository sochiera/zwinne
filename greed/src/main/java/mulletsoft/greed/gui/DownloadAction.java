package mulletsoft.greed.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class DownloadAction extends AbstractAction {

  public DownloadAction(){
    super("Download data");
  }
  
  public void actionPerformed(ActionEvent arg0) {
    System.out.println("download clicked");
  }
}
