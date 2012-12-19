package mulletsoft.greed.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class DataSourcesDialog extends JDialog {
  
  private JTextField sourceAddress = new JTextField();
  private JButton addButton;
  private JButton editButton;
  private JButton okButton;
  private JButton deleteButton;
  private JTable sourcesTable;
  
  
  class OKAction extends AbstractAction{
    private JDialog parent;

    OKAction(JDialog parent) {
      super("OK");
      this.parent = parent;
    }
    
    public void actionPerformed(ActionEvent evt) {
      parent.setVisible(false);
    }    
  }
  
  
  
  public DataSourcesDialog(){
    setTitle("Data sources");
    setModal(true);
    setPreferredSize(new Dimension(640, 480));
    makeUi();
    pack();
  }

  private void makeUi() {
    setLayout(new MigLayout("fill", "[grow, fill][shrink]", "[shrink][grow][shrink]"));
    add(sourceAddress, "grow");
    
    addButton = new JButton("Add");
    editButton = new JButton("Edit");
    deleteButton = new JButton("Delete");
    okButton = new JButton(new OKAction(this));
    
    add(addButton, "wrap");
    sourcesTable = new JTable();
    JScrollPane sp = new JScrollPane(sourcesTable);
    add(sp, "span 2, wrap");
    add(editButton, "split 2");
    add(deleteButton);
    add(okButton, "growx");
  }
  
}
