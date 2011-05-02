package com.copernic.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.copernic.core.CopernicContext;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OptionWindow extends JFrame {


	private static final long serialVersionUID = 1L;
	private static OptionWindow singleton;
	private JPanel contentPane;
	private CopernicContext copernicContext;
	private JLabel label_rootPath;
	private JLabel label_nbBlocs;
	private JLabel label_nbStructures;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionWindow frame = new OptionWindow();
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
	public OptionWindow() {
		setTitle("Copernic Options");
		setBounds(100, 100, 713, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tabbedPane.addTab("General", null, scrollPane, null);
		
		JPanel panel = new JPanel();
		scrollPane.setColumnHeaderView(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblGeneralOptions = new JLabel("General Options");
		lblGeneralOptions.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(lblGeneralOptions);
		
		JLabel label_1 = new JLabel("   ");
		panel.add(label_1);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				reload() ;
			}
		});
		label.setIcon(new ImageIcon(OptionWindow.class.getResource("/com/copernic/ui/res/Fwdw_icons/standart/png/24x24/001_39.png")));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setRowHeaderView(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblStructuresAndBlocs = new JLabel("Standard Blocs & Strutures Library :");
		lblStructuresAndBlocs.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblStructuresAndBlocs.setBounds(12, 12, 369, 15);
		panel_2.add(lblStructuresAndBlocs);
		
		label_rootPath = new JLabel("ROOT PATH");
		label_rootPath.setBounds(32, 39, 649, 15);
		panel_2.add(label_rootPath);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(UIManager.getColor("ScrollBar.shadow"));
		panel_3.setBounds(32, 69, 260, 76);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel button = new JLabel("");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (copernicContext!=null) {
					final JFileChooser fc = new JFileChooser();
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					fc.setCurrentDirectory(new File(copernicContext.getRootPath())) ;
			        int returnVal = fc.showOpenDialog(OptionWindow.getSingleton());
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile() ;
			            copernicContext.setRootPath(file.getAbsolutePath()) ;
			        }
				}
			}
		});
		button.setBounds(12, 5, 35, 34);
		panel_3.add(button);
		button.setToolTipText("edit root path");
		button.setIcon(new ImageIcon(OptionWindow.class.getResource("/com/copernic/ui/res/Fwdw_icons/standart/png/24x24/001_45.png")));
		
		JLabel button_1 = new JLabel("");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				reloadBlocsAndStructures() ;
			}
		});
		button_1.setBounds(51, 5, 39, 34);
		panel_3.add(button_1);
		button_1.setToolTipText("reload structures and blocs");
		button_1.setIcon(new ImageIcon(OptionWindow.class.getResource("/com/copernic/ui/res/Fwdw_icons/standart/png/24x24/001_39.png")));
		
		label_nbBlocs = new JLabel("# blocs");
		label_nbBlocs.setFont(new Font("Dialog", Font.BOLD, 14));
		label_nbBlocs.setBounds(101, 5, 147, 24);
		panel_3.add(label_nbBlocs);
		
		label_nbStructures = new JLabel("# structures");
		label_nbStructures.setFont(new Font("Dialog", Font.BOLD, 14));
		label_nbStructures.setBounds(101, 41, 147, 24);
		panel_3.add(label_nbStructures);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Blocs", null, panel_4, null);
		panel_4.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(0, 0, 696, 34);
		panel_4.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblBlocksView = new JLabel("Blocks View");
		lblBlocksView.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_7.add(lblBlocksView);
		
		textField = new JTextField();
		textField.setBounds(70, 80, 114, 19);
		panel_4.add(textField);
		textField.setColumns(10);
		
		JLabel lblFilter = new JLabel("Filter :");
		lblFilter.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblFilter.setBounds(27, 53, 369, 15);
		panel_4.add(lblFilter);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				
			}
		));
		table.setBounds(113, 121, 369, 184);
		panel_4.add(table);
		
		JLabel label_2 = new JLabel("");
		label_2.setToolTipText("reload structures and blocs");
		label_2.setBounds(202, 69, 39, 34);
		label_2.setIcon(new ImageIcon(OptionWindow.class.getResource("/com/copernic/ui/res/Fwdw_icons/standart/png/24x24/001_39.png")));
		panel_4.add(label_2);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Structures", null, panel_5, null);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Project", null, panel_6, null);
		panel_6.setLayout(null);
	}

	public static OptionWindow getSingleton() {
		if (OptionWindow.singleton==null) {
			OptionWindow.singleton = new OptionWindow() ;
		}
		return OptionWindow.singleton ;
	}

	public void setCopernicContext(CopernicContext copernicContext) {
		this.copernicContext = copernicContext ;
		reload() ;
	}
	
	public void reload() {
		if (copernicContext!=null) {
			label_rootPath    .setText(copernicContext.getRootPath()) ;	
			label_nbBlocs     .setText(copernicContext.getCountBlocs()+" blocs") ;
			label_nbStructures.setText(copernicContext.getCountStructs()+" structures") ;
		}
	}
	
	protected void reloadBlocsAndStructures() {
		if (copernicContext!=null) {
			copernicContext.reloadBlocsAndStructures() ;		
		}
		reload() ;
	}
}
