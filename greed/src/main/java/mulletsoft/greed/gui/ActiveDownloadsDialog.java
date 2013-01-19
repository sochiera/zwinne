package mulletsoft.greed.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JScrollPane;
import javax.swing.JList;

import mulletsoft.greed.model.Download;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ActiveDownloadsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList list;
	private java.util.List<Download> downloads;
	private DefaultListModel listModel = new DefaultListModel();
	private ApplicationContext appContext;
	
	public void refreshList(){
	  appContext.openSession();
	  downloads = appContext.getDownloadManager().getActiveDownloads();
		System.out.println("Pobieranie listy aktywnych downloadow");
		this.listModel.clear();
		int size = downloads.size();
		for(int i = 0; i < size; i++)
		{
			Download d = (Download) downloads.get(i);
			String el = d.getSource().getPath() + " from " + d.getSource().getAddress() + " (to " +
				d.getPath() + ")";
			this.listModel.addElement(el);
		}
		appContext.closeSession();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ActiveDownloadsDialog dialog = new ActiveDownloadsDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ActiveDownloadsDialog(ApplicationContext appContext) {
	  this.appContext = appContext;
		setTitle("Active downloads");
		setBounds(100, 100, 450, 300);
		downloads = new ArrayList<Download>();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "2, 2, fill, fill");
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
				JButton okButton = new JButton("Refresh");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						refreshList();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
