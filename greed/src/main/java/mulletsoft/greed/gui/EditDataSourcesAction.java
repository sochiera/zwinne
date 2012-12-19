package mulletsoft.greed.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class EditDataSourcesAction extends AbstractAction {

  public EditDataSourcesAction(){
    super("Edit data sources");
  }
  
  public void actionPerformed(ActionEvent e) {
    DataSourcesDialog dsd = new DataSourcesDialog();
    dsd.setVisible(true);
  }

}
