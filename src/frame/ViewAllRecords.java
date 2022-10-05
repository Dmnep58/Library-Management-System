package frame;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.ComponentOrientation;
import rojeru_san.complementos.RSTableMetro;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewAllRecords extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6956377887408942366L;
	private JPanel contentPane;
	
	private JPanel SearchDescPanel;
	private JPanel BackPanel ;
	private JPanel ExitPanel;
	private JPanel TitleLinePanel; 
	private JPanel TablePanel;
	
	private JLabel Back ;
	private JLabel exit ;
	private JLabel TitleLabel; 
	private JLabel issuedate ;
	private JLabel duedate;
	
	
	private JScrollPane scrollPane; 
	
	private JTextField issuedatefield;
	private JTextField duedatefield;
	
	private JButton searchBtn;
	private JButton btnAll;
	
	private DefaultTableModel model;
	private RSTableMetro table;
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllRecords frame = new ViewAllRecords();
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
			String query= ("select * from issue_book");
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
	
	// method to clear table
	public void clearTable() {
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
		 model.setRowCount(0);
	}
	
	
	//to search data in table
	public void searchissuedetails() throws ParseException {
		String issueString = issuedatefield.getText();
		String dueString = duedatefield.getText();
		
 	
		Date date1=new SimpleDateFormat("dd MMM yyyy").parse(issueString); 
		 
		 long l1 = date1.getTime();
		 
        Date date=new SimpleDateFormat("dd MMM yyyy").parse(dueString); 
		 
		 long l2 = date.getTime();
			
	
		 java.sql.Date issueddate =new java.sql.Date(l1);
			java .sql.Date duueddate = new java.sql.Date(l2);
		 
		
		try {

	          
				
			Connection connection = DBconnection.getConnection();
			String queryString =("select * from issue_book where issue_date between ? and ?");
		   PreparedStatement preparedStatement = connection.prepareStatement(queryString);
		   
		   preparedStatement.setDate(1, issueddate);
		   preparedStatement.setDate(2, duueddate);
		   
		   
		   
		   ResultSet resultSet = preparedStatement.executeQuery();
		   if(resultSet.next()==false) {
			   JOptionPane.showMessageDialog(contentPane, "No Record Found");
		   }
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
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//to check filled dates
	public boolean datesfilled() {
		boolean filled = false;
		
		String issuString = issuedatefield.getText();
		String dueString = duedatefield.getText();
		
		if(issuString.equals("") && dueString.equals("")) {
			filled = true;
			JOptionPane.showMessageDialog(contentPane, "Please Enter Both Dates");
		}
		else {
			filled = false;
		}
				
				return filled;
	}
	
	/**
	 * Create the frame.
	 */
	public ViewAllRecords() {
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
		
		SearchDescPanel = new JPanel();
		SearchDescPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		SearchDescPanel.setBackground(new Color(0, 102, 255));
		SearchDescPanel.setBounds(0, 0, 1244, 239);
		contentPane.add(SearchDescPanel);
		SearchDescPanel.setLayout(null);
		
		BackPanel = new JPanel();
		BackPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		BackPanel.setBackground(new Color(255, 51, 51));
		BackPanel.setBounds(0, 0, 117, 54);
		SearchDescPanel.add(BackPanel);
		BackPanel.setLayout(null);
		
		Back = new JLabel("  <<Back");
		 Back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		 Back.setBounds(0, 0, 117, 57);
		BackPanel.add(Back);
		
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
		
		ExitPanel = new JPanel();
		ExitPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ExitPanel.setBackground(new Color(255, 0, 0));
		ExitPanel.setBounds(1173, 0, 71, 54);
		SearchDescPanel.add(ExitPanel);
		ExitPanel.setLayout(null);
		
		exit = new JLabel("   X");
		exit.setBounds(0, 0, 68, 52);
		ExitPanel.add(exit);
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
		
		TitleLabel = new JLabel("View All Records\r\n");
		TitleLabel.setFont(new Font("Ubuntu", Font.PLAIN, 25));
		TitleLabel.setForeground(new Color(255, 255, 255));
		TitleLabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Literature_100px_1.png"));
		TitleLabel.setBounds(432, 11, 379, 100);
		SearchDescPanel.add(TitleLabel);
		
		TitleLinePanel = new JPanel();
		TitleLinePanel.setBackground(new Color(255, 255, 255));
		TitleLinePanel.setBounds(426, 122, 347, 4);
		SearchDescPanel.add(TitleLinePanel);
		
		issuedate = new JLabel("Issue Date\r\n:");
		issuedate.setForeground(new Color(255, 255, 255));
		issuedate.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		issuedate.setBounds(24, 171, 127, 33);
		SearchDescPanel.add(issuedate);
		
		issuedatefield = new JTextField();
		issuedatefield.setBounds(161, 172, 168, 32);
		SearchDescPanel.add(issuedatefield);
		issuedatefield.setColumns(10);
		
		duedate = new JLabel("Due\r\n Date\r\n:");
		duedate.setForeground(Color.WHITE);
		duedate.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		duedate.setBounds(432, 171, 127, 33);
		SearchDescPanel.add(duedate);
		
		duedatefield = new JTextField();
		duedatefield.setColumns(10);
		duedatefield.setBounds(569, 171, 168, 32);
		SearchDescPanel.add(duedatefield);
		
		searchBtn = new JButton("search\r\n");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		if(datesfilled()==false) {
			clearTable();
			try {
				searchissuedetails();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
			}
		});
		searchBtn.setFocusPainted(false);
		searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		searchBtn.setBackground(new Color(255, 51, 51));
		searchBtn.setForeground(new Color(255, 255, 255));
		searchBtn.setFont(new Font("Ubuntu", Font.BOLD, 25));
		searchBtn.setBounds(833, 159, 168, 54);
		SearchDescPanel.add(searchBtn);
		
		btnAll = new JButton("All");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				setRecordTable();
			}
		});
		btnAll.setForeground(Color.WHITE);
		btnAll.setFont(new Font("Ubuntu", Font.BOLD, 25));
		btnAll.setFocusPainted(false);
		btnAll.setBackground(new Color(255, 51, 51));
		btnAll.setBounds(1041, 159, 127, 54);
		SearchDescPanel.add(btnAll);
		
		TablePanel = new JPanel();
		TablePanel.setBounds(10, 252, 1203, 356);
		contentPane.add(TablePanel);
		TablePanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrollPane.setBounds(10, 0, 1193, 345);
		TablePanel.add(scrollPane);
		
		

		table = new RSTableMetro();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setMultipleSeleccion(false);
		scrollPane.setViewportView(table);
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
