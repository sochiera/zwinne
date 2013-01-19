package mulletsoft.greed.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import mulletsoft.greed.model.Download;
import mulletsoft.greed.model.User;
import mulletsoft.greed.net.DownloadManager;

import org.hibernate.cfg.Configuration;

public class ApplicationWindow {

	private JFrame frmGreed;
	private JList list;
	private java.util.List<Download> downloads;
	private EditDataSourcesDialog edsDialog;
	private DataSourcesDialog dsDialog;
	public ActiveDownloadsDialog adDialog;
	private DefaultListModel listModel = new DefaultListModel();  

	private ApplicationContext appContext;
  private Thread downloadManagerThread;
	
	
	public void refreshList()
	{
	  appContext.openSession();
	  downloads = appContext.getAll(Download.class);
		this.listModel.clear();
		
		int size = downloads.size();
		for(int i = 0; i < size; i++)
		{
			Download d = (Download) downloads.get(i);
			String el = "";
			if(d.getSource() != null){
			  el = d.getDownloadTime() + " : " + d.getSource().getPath() + " from " + 
			    d.getSource().getAddress() + " (to " + d.getPath() + ")";
			}
			else{
	       el = d.getDownloadTime() + " : (to " + d.getPath() + ")";
			}
			
			this.listModel.addElement(el);
		}
		
		appContext.closeSession();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationWindow window = new ApplicationWindow();
					window.frmGreed.setVisible(true);
					window.frmGreed.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationWindow() {
		initialize();
		
    appContext = new ApplicationContext(new DownloadManager(null));
    appContext.getDownloadManager().setContext(appContext);
    
    downloadManagerThread = new Thread(appContext.getDownloadManager());
    downloadManagerThread.start();
    
    edsDialog = new EditDataSourcesDialog(appContext);
		edsDialog.setLocationRelativeTo(this.frmGreed);
		dsDialog = new DataSourcesDialog(appContext);
		dsDialog.setParent(this);
		dsDialog.setLocationRelativeTo(this.frmGreed);
		adDialog = new ActiveDownloadsDialog(appContext);
		adDialog.setLocationRelativeTo(this.frmGreed);
		
		frmGreed.setVisible(true);
		frmGreed.setLocationRelativeTo(null);
    refreshList();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGreed = new JFrame();
		frmGreed.setTitle("Greed");
		frmGreed.setBounds(100, 100, 450, 300);
		frmGreed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		downloads = new ArrayList<Download>();
		JMenuBar menuBar = new JMenuBar();
		frmGreed.setJMenuBar(menuBar);
		
		JMenu mnGreed = new JMenu("Greed");
		menuBar.add(mnGreed);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dsDialog.dispose();
				edsDialog.dsDialog.dispose();
				edsDialog.dispose();
				frmGreed.dispose();
			}
		});
		mnGreed.add(mntmQuit);
		
		JMenu mnDownloads = new JMenu("Downloads");
		menuBar.add(mnDownloads);
		
		JMenuItem mntmDownloadData = new JMenuItem("Download data");
		mntmDownloadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dsDialog.refreshList();
				dsDialog.setVisible(true);
			}
		});
		
		JMenuItem mntmActiveDownloads = new JMenuItem("Active downloads");
		mntmActiveDownloads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adDialog.refreshList();
				adDialog.setVisible(true);
			}
		});
		mnDownloads.add(mntmActiveDownloads);
		mnDownloads.add(mntmDownloadData);
		
		JMenuItem mntmEditDataSources = new JMenuItem("Edit data sources");
		mntmEditDataSources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				edsDialog.refreshList();
				edsDialog.setVisible(true);
			}
		});
		mnDownloads.add(mntmEditDataSources);
		frmGreed.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		frmGreed.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.listModel = new DefaultListModel();
		list = new JList(this.listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JPanel panel = new JPanel();
		frmGreed.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnDownloadData = new JButton("Download data");
		btnDownloadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dsDialog.refreshList();
				dsDialog.setVisible(true);
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshList();
			}
		});
		panel.add(btnRefresh);
		panel.add(btnDownloadData);
		
		JButton btnEditDataSources = new JButton("Edit data sources");
		btnEditDataSources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				edsDialog.refreshList();
				edsDialog.setVisible(true);
			}
		});
		panel.add(btnEditDataSources);
	}

}
