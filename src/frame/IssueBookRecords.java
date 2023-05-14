package frame;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Cursor;

import javax.management.modelmbean.ModelMBean;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.ComponentOrientation;
import rojeru_san.complementos.RSTableMetro;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import function.DBconnection;

public class IssueBookRecords extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7174098363435958164L;

	/**
	 * 
	 */

	private JPanel contentPane;
	
	private JPanel MainPanel;
	private JPanel panelBack;
	private JPanel PanelExit;
	private JPanel IssueLinePanel;
	
	private JLabel Back;
	private JLabel exit;
	private JLabel IssueBookLabel;
	
	
	private JScrollPane scrollPaneTabel;
	private DefaultTableModel model;
	private RSTableMetro table;
	
	
	
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBookRecords frame = new IssueBookRecords();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public void setRecordTable() {
		try {
			Connection connection =DBconnection.getConnection();
			String query= ("select * from issue_book where status = '"+"pending"+"' order by due_date Asc");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
					String issue_id = resultSet.getString("id");
					String book_names = resultSet.getString("book_name");
					String student_name = resultSet.getString("student_name");
					String issue_date = resultSet.getString("issue_date");
					String due_dates = resultSet.getString("due_date");
					String status = resultSet.getString("status");
					
					Object[] objects = {issue_id, book_names, student_name, issue_date, due_dates, status};
					
					model = (DefaultTableModel) table.getModel();
					model.addRow(objects);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	


	
	/**
	 * Create the frame.
	 */
	public IssueBookRecords() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1244, 643);
		contentPane = new JPanel();
		contentPane.setEnabled(false);
		contentPane.setFocusTraversalKeysEnabled(false);
		contentPane.setRequestFocusEnabled(false);
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MainPanel = new JPanel();
		MainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		MainPanel.setBackground(Color.WHITE);
		MainPanel.setBounds(0, 0, 1244, 643);
		contentPane.add(MainPanel);
		MainPanel.setLayout(null);
		
		panelBack = new JPanel();
		panelBack.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panelBack.setBackground(new Color(255, 51, 51));
		panelBack.setBounds(0, 0, 117, 54);
		MainPanel.add(panelBack);
		panelBack.setLayout(null);
		
		Back = new JLabel("  <<Back");
		 Back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		 Back.setBounds(0, 0, 117, 57);
		panelBack.add(Back);
		
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage homePage = new HomePage();
				homePage.setVisible(true);
				dispose();
			}
		});
		Back.setBackground(new Color(255, 51, 0));
		Back.setForeground(new Color(255, 255, 255));
		Back.setFont(new Font("Ubuntu", Font.BOLD, 22));
		
		PanelExit = new JPanel();
		PanelExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		PanelExit.setBackground(new Color(255, 0, 0));
		PanelExit.setBounds(1173, 0, 71, 54);
		MainPanel.add(PanelExit);
		PanelExit.setLayout(null);
		
		exit = new JLabel("   X");
		exit.setBounds(0, 0, 68, 52);
		PanelExit.add(exit);
		exit.setFocusCycleRoot(true);
		exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(ABORT);
			}
		});
		exit.setFont(new Font("Ubuntu", Font.PLAIN, 25));
		exit.setForeground(new Color(255, 255, 255));
		
		IssueBookLabel = new JLabel("Issued Book Records\r\n");
		IssueBookLabel.setFont(new Font("Ubuntu", Font.PLAIN, 25));
		IssueBookLabel.setForeground(Color.RED);
		IssueBookLabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Books_52px_1.png"));
		IssueBookLabel.setBounds(432, 11, 379, 100);
		MainPanel.add(IssueBookLabel);
		
		IssueLinePanel = new JPanel();
		IssueLinePanel.setForeground(Color.RED);
		IssueLinePanel.setBackground(Color.RED);
		IssueLinePanel.setBounds(432, 107, 347, 4);
		MainPanel.add(IssueLinePanel);
		
		scrollPaneTabel = new JScrollPane();
		scrollPaneTabel.setBounds(21, 210, 1193, 345);
		MainPanel.add(scrollPaneTabel);
		scrollPaneTabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		

		table = new RSTableMetro();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setMultipleSeleccion(false);
		scrollPaneTabel.setViewportView(table);
		table.setGridColor(Color.BLUE);
		table.setRowHeight(34);
		table.setFont(new Font("Ubuntu", Font.BOLD, 20));
		table.setAutoCreateRowSorter(true);
		table.setBackground(Color.MAGENTA);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Status ID", "Book Name", "Student Name", "Issue Date", "Due Date", "status"
			}
		));
		
	  
		
		setRecordTable();
	}
}
