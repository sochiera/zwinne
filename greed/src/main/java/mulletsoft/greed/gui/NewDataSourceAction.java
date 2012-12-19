package mulletsoft.greed.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class NewDataSourceAction extends AbstractAction {

  public NewDataSourceAction(){
    super("New data source");
  }
  
  public void actionPerformed(ActionEvent e) {
    System.out.println("edit data sources clicked");
  }
}
