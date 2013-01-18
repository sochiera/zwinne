package mulletsoft.greed.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

public class DataSourceDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int ds_id;
	private String address;
	private EditDataSourcesDialog parent;
	public JTextField textField;
	public JTextField textField_1;
	
	public void setParent(EditDataSourcesDialog p)
	{
		parent = p;
	}
	
	public void setId(int id){
		this.ds_id = id;
	}
	
	public void refreshTitle(){
		this.setTitle("Editing " + address);
	}
	
	public void setAddress(String s){
		this.address = s;
	}
	
	public void hideDialog(){
		this.setVisible(false);
	}

	public void updateDataSource(){
		if(ds_id == -1){ //dodawanie nowego
			String address = this.address;
			String login = this.textField.getText();
			String password = this.textField_1.getText();
			// dodaj do bazy (address, login, password);
			System.out.println("Dodaj do bazy (" + address + ", " + login + ", " + password + ")");
			this.parent.refreshList();
			this.setVisible(false);
		}
		else{ //edycja starego
			String address = this.address;
			String login = this.textField.getText();
			String password = this.textField_1.getText();
			// ustaw w bazie (address, login, password), gdzie id = ds_id
			System.out.println("Ustaw w bazie (" + address + ", " + login + ", " + password + ") gdzie id = " + this.ds_id);
			this.parent.refreshList();
			this.setVisible(false);
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DataSourceDialog dialog = new DataSourceDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DataSourceDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblLogin = new JLabel("Login:");
			contentPanel.add(lblLogin, "2, 2, right, default");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "4, 2, fill, default");
			textField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			contentPanel.add(lblPassword, "2, 4, right, default");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "4, 4, fill, default");
			textField_1.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						updateDataSource();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						hideDialog();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
