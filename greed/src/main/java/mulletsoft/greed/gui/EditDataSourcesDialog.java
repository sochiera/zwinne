package mulletsoft.greed.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import mulletsoft.greed.model.Source;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class EditDataSourcesDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private java.util.List<Source> sources;
	private JList list;
	public DataSourceDialog dsDialog;
	private DefaultListModel listModel = new DefaultListModel();  

	private ApplicationContext appContext;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditDataSourcesDialog dialog = new EditDataSourcesDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refreshList(){
	  appContext.openSession();
		sources = appContext.getAll(Source.class);
		this.listModel.clear();
		int size = sources.size();
		for(int i = 0; i < size; i++)
		{
			Source s = (Source) sources.get(i);
			String el = s.getPath() + " at " + s.getAddress();
			this.listModel.addElement(el);
		}
		appContext.closeSession();
	}

	public void hideWindow(){
		this.setVisible(false);
	}
	
	public void editSource(int index){
		if(index == -1) {
			JOptionPane.showMessageDialog(this,
				    "Select data source.",
				    "No data source selected",
				    JOptionPane.ERROR_MESSAGE);
		}
		else{
			Source s = (Source) sources.get(index);
			dsDialog.setId(s.getId().intValue());
			dsDialog.setAddress(s.getAddress());
			dsDialog.textField.setText(s.getLogin());
			dsDialog.textField_1.setText(s.getPassword());
			dsDialog.textField_2.setText(s.getPath());
			dsDialog.comboBox.setSelectedItem(s.getProtocol());
			dsDialog.setVisible(true);
			dsDialog.refreshTitle();
		}
	}
	
	public void deleteSource(int index){
		if(index == -1) {
			JOptionPane.showMessageDialog(this,
				    "Select data source.",
				    "No data source selected",
				    JOptionPane.ERROR_MESSAGE);
		}
		else{
			Source s = (Source) sources.get(index);
			String ad = s.getAddress();
			int s_id = s.getId().intValue();
			int n = JOptionPane.showConfirmDialog(
				    this,
				    "Do you want to delete: \n" + ad + "?",
				    "Confirm delete",
				    JOptionPane.YES_NO_OPTION);
			if(n == 0){
			  appContext.openSession();
			  appContext.delete(s);
			  appContext.closeSession();
				this.refreshList();
			}
		}
	}
	
	public void addSource(String s){
		if(s.equals(""))
		{
			JOptionPane.showMessageDialog(this,
				    "Enter data source address.",
				    "No address",
				    JOptionPane.ERROR_MESSAGE);
		}
		else{
			dsDialog.setAddress(s);
			dsDialog.setId(-1);
			dsDialog.textField.setText("");
			dsDialog.textField_1.setText("");
			dsDialog.textField_2.setText("");
			dsDialog.comboBox.setSelectedIndex(0);
			dsDialog.setVisible(true);
			dsDialog.refreshTitle();
		}
	}
	
	/**
	 * Create the dialog.
	 * @param appContext 
	 */
	public EditDataSourcesDialog(ApplicationContext appContext) {
	  this.appContext = appContext;
		setTitle("Edit data sources");
		setBounds(100, 100, 450, 300);
		sources = new ArrayList<Source>();
		dsDialog = new DataSourceDialog(appContext);
		dsDialog.setLocationRelativeTo(this);
		dsDialog.setVisible(false);
		dsDialog.setParent(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			textField = new JTextField();
			contentPanel.add(textField, "2, 2, fill, default");
			textField.setColumns(10);
		}
		{
			JButton btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					addSource(textField.getText());
					textField.setText("");
				}
			});
			contentPanel.add(btnAdd, "4, 2");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "2, 4, 3, 1, fill, fill");
			{
				listModel = new DefaultListModel();
				list = new JList(listModel);
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(list);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnEdit = new JButton("Edit");
				btnEdit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						editSource(list.getSelectedIndex());
					}
				});
				buttonPane.add(btnEdit);
			}
			{
				JButton btnDelete = new JButton("Delete");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						deleteSource(list.getSelectedIndex());
					}
				});
				buttonPane.add(btnDelete);
			}
			{
				final JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						hideWindow();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
