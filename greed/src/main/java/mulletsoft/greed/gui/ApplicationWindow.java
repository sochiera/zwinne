package mulletsoft.greed.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTable;

public class ApplicationWindow {

	private JFrame frmGreed;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationWindow window = new ApplicationWindow();
					window.frmGreed.setVisible(true);
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGreed = new JFrame();
		frmGreed.setTitle("Greed");
		frmGreed.setBounds(100, 100, 450, 300);
		frmGreed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmGreed.setJMenuBar(menuBar);
		
		JMenu mnGreed = new JMenu("Greed");
		menuBar.add(mnGreed);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmGreed.dispose();
			}
		});
		mnGreed.add(mntmQuit);
		
		JMenu mnDownloads = new JMenu("Downloads");
		menuBar.add(mnDownloads);
		
		JMenuItem mntmDownloadData = new JMenuItem("Download data");
		mnDownloads.add(mntmDownloadData);
		
		JMenuItem mntmEditDataSources = new JMenuItem("Edit data sources");
		mnDownloads.add(mntmEditDataSources);
		frmGreed.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		frmGreed.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		frmGreed.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnDownloadData = new JButton("Download data");
		panel.add(btnDownloadData);
		
		JButton btnEditDataSources = new JButton("Edit data sources");
		panel.add(btnEditDataSources);
	}

}
