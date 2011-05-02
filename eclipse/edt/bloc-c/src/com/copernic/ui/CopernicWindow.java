package com.copernic.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import com.copernic.core.CopernicContext;
import javax.swing.JLabel;
import java.awt.Font;

public class CopernicWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	CopernicContext copernicContext = new CopernicContext() ;
	String iconFolder = "res/Fwdw_icons/standart/png/24x24" ;
	String iconFolder2 = "res/ubuntu" ;
	
	private JPanel contentPane;

	private ArchitecturePanel architecturePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		final CopernicWindow frame = new CopernicWindow();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CopernicWindow() {
		setTitle("Copernic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 879, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton newproject = new JButton("");
		newproject.setToolTipText("new project");
		newproject.setIcon(new ImageIcon(CopernicWindow.class.getResource(iconFolder+"/001_01.png")));
		toolBar.add(newproject);
		
		JButton openproject = new JButton("");
		openproject.setToolTipText("open project");
		openproject.setIcon(new ImageIcon(CopernicWindow.class.getResource(iconFolder+"/001_43.png")));
		toolBar.add(openproject);
		
		JButton settingproject = new JButton("");
		settingproject.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				OptionWindow.getSingleton().setCopernicContext(copernicContext) ; 
				OptionWindow.getSingleton().setVisible(true) ;
			}
		});
		settingproject.setToolTipText("settings");
		settingproject.setIcon(new ImageIcon(CopernicWindow.class.getResource(iconFolder+"/001_44.png")));
		toolBar.add(settingproject);
		
		JButton movebutton = new JButton("");
		movebutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				architecturePanel.modeGlobalMove() ;
			}
		});
		movebutton.setToolTipText("settings");
		movebutton.setIcon(new ImageIcon(CopernicWindow.class.getResource(iconFolder2+"/move.png")));
		toolBar.add(movebutton);
		
		
		architecturePanel = new ArchitecturePanel();
		contentPane.add(architecturePanel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("no current job");
		lblNewLabel.setFont(new Font("Dialog", Font.ITALIC, 10));
		panel_1.add(lblNewLabel);
		
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
//		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("new");
		mntmNewMenuItem.setIcon(new ImageIcon(CopernicWindow.class.getResource(iconFolder+"/001_01.png")));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmOpen = new JMenuItem("open");
		mntmOpen.setIcon(new ImageIcon(CopernicWindow.class.getResource(iconFolder+"/001_43.png")));
		mnNewMenu.add(mntmOpen);
	}

}
