package mulletsoft.greed.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;

import org.hibernate.Session;

import mulletsoft.greed.model.Download;
import mulletsoft.greed.model.Source;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class DataSourcesDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFileChooser chooser;
	private JList list;
	private java.util.List<Source> sources;
	private ApplicationWindow parent;
	private DefaultListModel listModel = new DefaultListModel(); 

	private ApplicationContext appContext;
	
	public void setParent(ApplicationWindow parent){
		this.parent = parent;
	}
	
	public void refreshList()
	{
	  appContext.openSession();
	  sources = appContext.getAll(Source.class);
	  
		System.out.println("Pobieranie listy zrodel danych");
		this.listModel.clear();
		int size = sources.size();
		for(int i = 0; i < size; i++)
		{
			Source s = (Source) sources.get(i);
			this.listModel.addElement(s.getPath() + " at " + s.getAddress());
		}
		
		appContext.closeSession();
	}
	
	public void downloadData(){
		int selected[] = list.getSelectedIndices();
		if(selected.length == 0){
			JOptionPane.showMessageDialog(this,
				    "Select data sources.",
				    "No data source selected",
				    JOptionPane.ERROR_MESSAGE);
		}
		else{
			int returnVal = chooser.showOpenDialog(this);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       String path = chooser.getSelectedFile().getPath();
		       for(int i : selected){
		    	   //Pobieranie danych
		    	   Source s = sources.get(i);
		    	   String address = s.getAddress();
		    	   String login = s.getLogin();
		    	   String password = s.getPassword();
		    	   String sourcePath = s.getPath();
		    	   System.out.println("Pobierz dane z " + address + " i zapisz w " + path + ", dodaj informacje do bazy");
		    	   
		    	   Download newDownload = new Download();
		    	   newDownload.setUser(appContext.getCurrentUser());
		    	   newDownload.setCurrentTime();
		    	   newDownload.setSource(s);
		    	   newDownload.setPath(path);
		    	   
		    	   appContext.getDownloadManager().startDownload(newDownload);
		       }
		       this.parent.adDialog.refreshList();
		       this.parent.adDialog.setVisible(true);
		    }
		}
	}
	
	public void hideDialog(){
		this.setVisible(false);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DataSourcesDialog dialog = new DataSourcesDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DataSourcesDialog(ApplicationContext appContext) {
	  this.appContext = appContext;
		sources = new ArrayList<Source>();
		setTitle("Download data");
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Choose directory");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			JLabel lblChoose = new JLabel("Choose data sources:");
			contentPanel.add(lblChoose, "2, 2");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "2, 4, fill, fill");
			{
				listModel = new DefaultListModel();
				list = new JList(listModel);
				scrollPane.setViewportView(list);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Download");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						downloadData();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						hideDialog();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
