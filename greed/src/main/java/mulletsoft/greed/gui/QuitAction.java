package mulletsoft.greed.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class QuitAction extends AbstractAction {

  public QuitAction(){
    super("Quit");
  }
  
  public void actionPerformed(ActionEvent e) {
    System.out.println("quit clicked");
  }
}
