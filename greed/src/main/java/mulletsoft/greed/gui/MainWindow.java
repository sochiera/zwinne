package mulletsoft.greed.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainWindow extends JFrame {

  private JMenuBar mainMenu = new JMenuBar();
  private JMenu greedMenu = new JMenu("Greed");
  private JMenu downloadsMenu = new JMenu("Downloads");
  
  private JTable downloadsTable = new JTable();
  
  private JButton newDownloadButton;
  private JButton editDataSourcesButton;
  
  private QuitAction quitAction;
  private DownloadAction downloadAction;
  private EditDataSourcesAction editDataSourcesAction;
  
  
  
  public MainWindow(){
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    makeActions();
    makeUi();
    pack();
    setVisible(true);
  }
  
  
  private void makeActions() {
    downloadAction = new DownloadAction();
    editDataSourcesAction = new EditDataSourcesAction();
    quitAction = new QuitAction();
  }
  

  private void makeUi(){
    setPreferredSize(new Dimension(800, 600));
    setTitle("Greed");
    setJMenuBar(mainMenu);
    
    mainMenu.add(greedMenu);
    greedMenu.add(quitAction);
    
    mainMenu.add(downloadsMenu);
    downloadsMenu.add(downloadAction);
    downloadsMenu.add(editDataSourcesAction);
  
    setLayout(new BorderLayout());
    JScrollPane tableScroll = new JScrollPane(downloadsTable);
    add(tableScroll, BorderLayout.CENTER);
    
    JPanel buttonsBottom = new JPanel(new FlowLayout());
    newDownloadButton = new JButton(downloadAction);
    editDataSourcesButton = new JButton(editDataSourcesAction);
    buttonsBottom.add(newDownloadButton);
    buttonsBottom.add(editDataSourcesButton);
    add(buttonsBottom, BorderLayout.PAGE_END);
  }
}
