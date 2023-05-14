package frame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.border.MatteBorder;


import java.awt.ComponentOrientation;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import function.DBconnection;
import function.MarqueeLabel;
import function.StudentPanelFunctions;
import rojeru_san.complementos.RSTableMetro;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;


public class StudentPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	private JPanel BookPanel;
	private JPanel  Issuepanel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel;
	private JPanel Studentpage;
	private JPanel lmsdashboard;
	private JPanel managebook;
	private JPanel manage_student;
	private JPanel issue_book;
	private JPanel logout;
	private JPanel StudentDashboardPanel;
	
	private JLabel menubarlabel ;
	private JLabel projectlabel; 
	private JLabel StudentRoll;
	private JLabel exit;
	private JLabel Homepagelabel;
	private JLabel lmslabel;
	private JLabel feature;
	private JLabel Availablebooklabel;
	private JLabel ReserveBookPanel;
	private JLabel IssuedBookPanel; 
	private JLabel logoutlabel;
	private JLabel NoOfBooks;
	private JLabel noIssuedBook;
	private JLabel NoOfBookslabel;
	private JLabel IssuedBookscount;
	private JLabel studenttabellabel;
	private JLabel booktabellabel;
	private JScrollPane scrollPane_1,AvailableScrollPane,issuedJScrollPane;
	private JLabel ReverseBookId;
	private JLabel ReserveBookAuthor;
	
	private RSTableMetro table_1,table , ReserveTable , IssuedTable;
	DefaultTableModel model;
	private JLabel lblNewLabel;
	private JPanel AvailablePanel;
	private JPanel StudentReservePanel;
	private JPanel StudentIssuedPanel;
	private JLabel StudentDetailsLabel;
	JTabbedPane tabbedPane ;
	private JScrollPane ReserveScrollPane;
	private JTextField BookSearchName;
	
	private String name;
	private JLabel ReserveBookName;
	private JLabel BookQuantity;
	private JLabel BookDetails;
	
	

	//reservation details
	int reservestudentid;
	int reservebookid;
	private JLabel BackgroundImg;
	private JLabel lblNewLabel_3;
	private JPasswordField NewPasswordField;
	private JPasswordField ConfirmPasswordField;
	
	LoginPage loginPage = new LoginPage();
	static String rollnumberString  =LoginPage.StudentRollNo_txt.getText();
	
	StudentPanelFunctions studentPanelFunctions  = new StudentPanelFunctions();
	String sdata = studentPanelFunctions.studentData(rollnumberString);
	int book = studentPanelFunctions.TwoDaysLeft(Integer.valueOf(rollnumberString));
	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentPanel frame = new StudentPanel();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	// to get the book count
	public void bookcount() {
		int count = 0;
		try {
			Connection connection = DBconnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from book_details");
			
			while(resultSet.next()) {
				
				count++;
			}
			NoOfBooks.setText(Integer.toString(count));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//to get issued book count
	public void IssuedBookcount() {
		int studentid = Integer.valueOf(rollnumberString);
		int count = 0;
		try {
			Connection connection = DBconnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from issue_book where status = '"+"pending"+"' and student_id = '"+studentid+"' order by due_date ASC");
			
			while(resultSet.next()) {
				count++;
			}
			noIssuedBook.setText(Integer.toString(count));
			
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	public void IssuedBookStudent() {
		int studentid = Integer.valueOf(rollnumberString);
		
		try {
			Connection connection = DBconnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from issue_book where status = '"+"pending"+"' and student_id = '"+studentid+"' order by due_date ASC");
			
			while(resultSet.next()) {
				String issue_id = resultSet.getString("id");
				String book_names = resultSet.getString("book_name");
				String issue_date = resultSet.getString("issue_date");
				String due_dates = resultSet.getString("due_date");
				String status = resultSet.getString("status");
				
				Object[] objects = {
						issue_id, book_names, issue_date, due_dates, status
						};
				
				model = (DefaultTableModel) IssuedTable.getModel();
				model.addRow(objects);
				
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	

	
	// to set the records in tables
			public void setBookDetailsStudentPanel() {
				try {
					Connection connection = DBconnection.getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from book_details");
					
					while(resultSet.next()) {
						String bookIdString = resultSet.getString("bookId");
						String bookNameString = resultSet.getString("Book_Name");
						String authornameString = resultSet.getString("author_name");
						String bookQuantString = resultSet.getString("quantity");
						
						Object[] objects= {bookIdString, bookNameString, authornameString, bookQuantString};
						model= (DefaultTableModel)table_1.getModel();
						model.addRow(objects);
						
					}
					
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		
		// to set the available book starting from the letter
		public void setBookDetailsName() {
			name = BookSearchName.getText()+"%";
			
			try {
				Connection connection = DBconnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from book_details where book_Name LIKE '"+name+"'");
				
				while(resultSet.next()) {
					String bookIdString = resultSet.getString("bookId");
					String bookNameString = resultSet.getString("Book_Name");
					String authornameString = resultSet.getString("author_name");
					String bookQuantString = resultSet.getString("quantity");
					
					Object[] objects= {bookIdString, bookNameString, authornameString, bookQuantString};
					model= (DefaultTableModel)ReserveTable.getModel();
					model.addRow(objects);
				}
				
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		// to set the records in tables
				public void setAvailableBookDetails() {
				
					
					try {
						Connection connection = DBconnection.getConnection();
						Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from book_details where Quantity > 0 ");
						
						while(resultSet.next()) {
							String bookIdString = resultSet.getString("bookId");
							String bookNameString = resultSet.getString("Book_Name");
							String authornameString = resultSet.getString("author_name");
							String bookQuantString = resultSet.getString("quantity");
							
							Object[] objects= {bookIdString, bookNameString, authornameString, bookQuantString};
							model= (DefaultTableModel)table.getModel();
							model.addRow(objects);
						}
						
						
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				
				
				// to insert the values in the table of reservation
				public boolean reservation() {
					boolean isreserved = false;
					reservestudentid = Integer.valueOf(rollnumberString);
					reservebookid = Integer.valueOf(ReverseBookId.getText());
										
					
					try {
						
						Connection connection = DBconnection.getConnection();
						String query = ("insert into reservation(student_id,book_id) values(?,?)");
						PreparedStatement preparedStatement = connection.prepareStatement(query);
						
						preparedStatement.setInt(1, reservestudentid);
						preparedStatement.setInt(2, reservebookid);
						
						
						int rowcount = preparedStatement.executeUpdate();
						 if(rowcount >0) {
							 isreserved = true;
						 }
						 else {
							isreserved = false;
						}
						
						
					}
					catch (Exception e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(ReserveTable, e.getCause());
					}
					
					
					return isreserved;
				}
		
		
	/**
	 * Create the frame.
	 */
	public StudentPanel() {
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1220, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1220, 73);
		panel_1.setBackground(new Color(51, 102, 255));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
	    menubarlabel = new JLabel("");
		menubarlabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_menu_48px_1.png"));
		menubarlabel.setBounds(10, 11, 55, 51);
		panel_1.add(menubarlabel);
		
		
		projectlabel = new JLabel("Library Management System ( Student Panel )\r\n");
		projectlabel.setForeground(new Color(255, 255, 255));
		projectlabel.setFont(new Font("Ubuntu", Font.BOLD, 25));
		projectlabel.setBounds(123, 11, 630, 51);
		panel_1.add(projectlabel);
		
		
		
		StudentRoll = new JLabel("Welcome "+rollnumberString);
		StudentRoll.setHorizontalAlignment(SwingConstants.LEFT);
		StudentRoll.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\male_user_50px.png"));
		StudentRoll.setForeground(new Color(255, 255, 255));
		StudentRoll.setFont(new Font("Ubuntu", Font.BOLD, 18));
		StudentRoll.setBounds(879, 11, 262, 51);
		panel_1.add(StudentRoll);
		
		
		exit = new JLabel("X\r\n");
		exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(ABORT);
			}
		});
		exit.setForeground(new Color(255, 255, 255));
		exit.setFont(new Font("Cambria", Font.BOLD, 32));
		exit.setBounds(1180, 11, 30, 51);
		panel_1.add(exit);
		
		
		panel_2 = new JPanel();
		panel_2.setMinimumSize(new Dimension(5, 5));
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(67, 11, 4, 51);
		panel_1.add(panel_2);
		
		
		panel = new JPanel();
		panel.setBounds(0, 59, 288, 542);
		panel.setBackground(new Color(51, 51, 51));
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		Studentpage = new JPanel();
		Studentpage.setBackground(new Color(255, 0, 0));
		Studentpage.setBounds(0, 28, 288, 41);
		panel.add(Studentpage);
		Studentpage.setLayout(null);
		
		Homepagelabel = new JLabel("    Home Page\r\n");
		Homepagelabel.setBounds(34, 0, 178, 41);
		Homepagelabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Home_26px_2.png"));
		Homepagelabel.setForeground(Color.WHITE);
		Homepagelabel.setFont(new Font("Ubuntu", Font.BOLD, 21));
		Studentpage.add(Homepagelabel);
		
		
		lmsdashboard = new JPanel();
		lmsdashboard.setBackground(new Color(51, 51, 51));
		lmsdashboard.setBounds(0, 79, 288, 41);
		panel.add(lmsdashboard);
		lmsdashboard.setLayout(null);
		
		
		lmslabel = new JLabel("    Student Dashboard");
		lmslabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lmslabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		lmslabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Library_26px_1.png"));
		lmslabel.setBounds(31, 0, 258, 41);
		lmslabel.setForeground(Color.WHITE);
		lmslabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
		lmsdashboard.add(lmslabel);
		
		feature = new JLabel("Features\r\n");
		feature.setForeground(new Color(255, 255, 255));
		feature.setFont(new Font("Ubuntu", Font.BOLD, 25));
		feature.setBounds(10, 131, 170, 41);
		panel.add(feature);
		
		managebook = new JPanel();
		managebook.setLayout(null);
		managebook.setBackground(new Color(51, 51, 51));
		managebook.setBounds(0, 171, 288, 41);
		panel.add(managebook);
		
		Availablebooklabel = new JLabel("   Available Books\r\n");
		Availablebooklabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Availablebooklabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(1);
		}
		});
		Availablebooklabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Book_26px.png"));
		Availablebooklabel.setForeground(Color.WHITE);
		Availablebooklabel.setFont(new Font("Ubuntu", Font.BOLD, 21));
		Availablebooklabel.setBounds(34, 0, 252, 41);
		managebook.add(Availablebooklabel);
		
		manage_student = new JPanel();
		manage_student.setLayout(null);
		manage_student.setBackground(new Color(51, 51, 51));
		manage_student.setBounds(0, 242, 288, 41);
		panel.add(manage_student);
		
		ReserveBookPanel = new JLabel("   Reserve Books\r\n");
		ReserveBookPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		ReserveBookPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ReserveBookPanel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Read_Online_26px.png"));
		ReserveBookPanel.setForeground(Color.WHITE);
		ReserveBookPanel.setFont(new Font("Ubuntu", Font.BOLD, 21));
		ReserveBookPanel.setBounds(31, 0, 256, 41);
		manage_student.add(ReserveBookPanel);
		
		issue_book = new JPanel();
		issue_book.setLayout(null);
		issue_book.setBackground(new Color(51, 51, 51));
		issue_book.setBounds(0, 309, 288, 41);
		panel.add(issue_book);
		
		IssuedBookPanel = new JLabel("   Issued Book");
		IssuedBookPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		IssuedBookPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		IssuedBookPanel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Sell_26px.png"));
		IssuedBookPanel.setForeground(Color.WHITE);
		IssuedBookPanel.setFont(new Font("Ubuntu", Font.BOLD, 21));
		IssuedBookPanel.setBounds(31, 0, 256, 41);
		issue_book.add(IssuedBookPanel);
		
		logout = new JPanel();
		logout.setLayout(null);
		logout.setBackground(new Color(51, 102, 255));
		logout.setBounds(0, 397, 288, 41);
		panel.add(logout);
		
		logoutlabel = new JLabel("   Logout\r\n");
		logoutlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoutlabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			LoginPage loginPage = new LoginPage();
			loginPage.setVisible(true);
			dispose();
				
			}
		});
		logoutlabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Exit_26px_2.png"));
		logoutlabel.setForeground(Color.WHITE);
		logoutlabel.setFont(new Font("Ubuntu", Font.BOLD, 21));
		logoutlabel.setBounds(22, 0, 256, 41);
		logout.add(logoutlabel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(287, 38, 933, 563);
		contentPane.add(tabbedPane);
		  		  
		  		  StudentDashboardPanel = new JPanel();
		  		  StudentDashboardPanel.setBackground(new Color(153, 255, 255));
		  		  tabbedPane.addTab("New tab", null, StudentDashboardPanel, null);
		  		  StudentDashboardPanel.setLayout(null);
		  		  
		  		  BookPanel = new JPanel();
		  		  BookPanel.setBackground(new Color(204, 204, 204));
		  		  BookPanel.setBorder(new MatteBorder(17, 1, 1, 1, (Color) new Color(51, 102, 255)));
		  		  BookPanel.setBounds(757, 146, 139, 92);
		  		  StudentDashboardPanel.add(BookPanel);
		  		  BookPanel.setLayout(null);
		  		  
		  		  NoOfBooks = new JLabel();
		  		  NoOfBooks.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Book_Shelf_50px.png"));
		  		  NoOfBooks.setFont(new Font("Ubuntu", Font.BOLD, 30));
		  		  NoOfBooks.setBounds(10, 26, 119, 55);
		  		  BookPanel.add(NoOfBooks);
		  		  
		  		   Issuepanel = new JPanel();
		  		   Issuepanel.setBackground(new Color(204, 204, 204));
		  		   Issuepanel.setBorder(new MatteBorder(17, 1, 1, 1, (Color) new Color(51, 102, 255)));
		  		   Issuepanel.setLayout(null);
		  		   Issuepanel.setBounds(757, 353, 139, 92);
		  		   StudentDashboardPanel.add(Issuepanel);
		  		   
		  		    noIssuedBook = new JLabel();
		  		    noIssuedBook.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Sell_50px.png"));
		  		    noIssuedBook.setFont(new Font("Ubuntu", Font.BOLD, 30));
		  		    noIssuedBook.setBounds(10, 24, 119, 57);
		  		    Issuepanel.add(noIssuedBook);
		  		    
		  		    NoOfBookslabel = new JLabel("No. Of Books\r\n");
		  		    NoOfBookslabel.setFont(new Font("Ubuntu", Font.BOLD, 19));
		  		    NoOfBookslabel.setBounds(757, 85, 139, 20);
		  		    StudentDashboardPanel.add(NoOfBookslabel);
		  		    
		  		    IssuedBookscount = new JLabel("Issued Books");
		  		    IssuedBookscount.setFont(new Font("Ubuntu", Font.BOLD, 19));
		  		    IssuedBookscount.setBounds(757, 322, 139, 20);
		  		    StudentDashboardPanel.add(IssuedBookscount);
		  		    
		  		    scrollPane_1 = new JScrollPane();
		  		    scrollPane_1.setBounds(20, 353, 523, 141);
		  		    StudentDashboardPanel.add(scrollPane_1);
		  		    
		  		    
		  		    table_1 = new RSTableMetro();
		  		    table_1.setShowGrid(false);
		  		    table_1.setAutoscrolls(false);
		  		    scrollPane_1.setViewportView(table_1);
		  		    table_1.setRowHeight(25);
		  		    table_1.setGridColor(Color.BLUE);
		  		    table_1.setFont(new Font("Ubuntu", Font.BOLD, 20));
		  		    table_1.setFocusable(false);
		  		    table_1.setFocusTraversalKeysEnabled(false);
		  		    table_1.setEnabled(false);
		  		    table_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		  		    table_1.setBorder(null);
		  		    table_1.setBackground(Color.MAGENTA);
		  		    table_1.setAutoCreateRowSorter(true);
		  		    table_1.setAlignmentY(1.0f);
		  		    table_1.setAlignmentX(1.0f);
		  		    table_1.setModel(new DefaultTableModel(
		  		    	new Object[][] {
		  		    	},
		  		    	new String[] {
		  		    		"book_id", "Name", "author", "quantity"
		  		    	}
		  		    ));
		  		    table_1.getColumnModel().getColumn(0).setResizable(false);
		  		    
		  		    studenttabellabel = new JLabel("Student Details");
		  		    studenttabellabel.setFont(new Font("Ubuntu", Font.BOLD, 25));
		  		    studenttabellabel.setHorizontalAlignment(SwingConstants.CENTER);
		  		    studenttabellabel.setBounds(20, 35, 213, 28);
		  		    StudentDashboardPanel.add(studenttabellabel);
		  		    
		  		    booktabellabel = new JLabel("Books Details");
		  		    booktabellabel.setHorizontalAlignment(SwingConstants.CENTER);
		  		    booktabellabel.setFont(new Font("Ubuntu", Font.BOLD, 25));
		  		    booktabellabel.setBounds(20, 314, 213, 28);
		  		    StudentDashboardPanel.add(booktabellabel);
		  		    
		  		    lblNewLabel = new JLabel("Available\r\n");
		  		    lblNewLabel.setFont(new Font("Ubuntu", Font.BOLD, 19));
		  		    lblNewLabel.setBounds(757, 116, 105, 20);
		  		    StudentDashboardPanel.add(lblNewLabel);
		  		    
		  		    StudentDetailsLabel = new JLabel("<html>\r\n<style>\r\nhr{\r\nwidth:10px;\r\n}\r\n</style>\r\n<body>\r\n"+sdata+"\r\n</body>\r\n</html>");
		  		    StudentDetailsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		  		    StudentDetailsLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 26));
		  		    StudentDetailsLabel.setBounds(41, 71, 473, 222);
		  		    StudentDashboardPanel.add(StudentDetailsLabel);
		  		    
		  		    MarqueeLabel noticeLabel = new MarqueeLabel("You have "+book+" books to return in 2 days",MarqueeLabel.RIGHT_TO_LEFT, 10);
		  		    noticeLabel.setForeground(new Color(255, 51, 51));
		  		    noticeLabel.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		  		    noticeLabel.setBounds(327, 21, 591, 28);
		  		    StudentDashboardPanel.add(noticeLabel);
		  		    
		  		    
		  		    AvailablePanel = new JPanel();
		  		    AvailablePanel.setBackground(new Color(153, 255, 255));
		  		    
		  		    AvailableScrollPane = new JScrollPane();
		  		    AvailableScrollPane.setBounds(62, 84, 794, 377);
		  		    AvailablePanel.add(AvailableScrollPane);
		  		    
		  		    table = new RSTableMetro();
		  		    table.setShowGrid(false);
		  		    table.setAutoscrolls(false);
		  		    AvailableScrollPane.setViewportView(table);
		  		    table.setRowHeight(25);
		  		    table.setGridColor(Color.BLUE);
		  		    table.setFont(new Font("Ubuntu", Font.BOLD, 20));
		  		    table.setFocusable(false);
		  		    table.setFocusTraversalKeysEnabled(false);
		  		    table.setEnabled(false);
		  		    table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		  		    table.setBorder(null);
		  		    table.setBackground(Color.MAGENTA);
		  		    table.setAutoCreateRowSorter(true);
		  		    table.setAlignmentY(1.0f);
		  		    table.setAlignmentX(1.0f);
		  		    table.setModel(new DefaultTableModel(
		  		    	new Object[][] {
		  		    	},
		  		    	new String[] {
		  		    		"book_id", "Name", "author", "quantity"
		  		    	}
		  		    ));
		  		    tabbedPane.addTab("New tab", null, AvailablePanel, null);
		  		    AvailablePanel.setLayout(null); 
		  		    tabbedPane.addTab("New tab", null, AvailablePanel, null);
		  		    
		  		    
		  		    
		  		    StudentReservePanel = new JPanel();
		  		    StudentReservePanel.setBackground(new Color(153, 255, 255));
		  		    tabbedPane.addTab("New tab", null, StudentReservePanel, null);
		  		    StudentReservePanel.setLayout(null);
		  		    
		  		    ReverseBookId = new JLabel();
		  		    ReverseBookId.setFont(new Font("Verdana", Font.PLAIN, 14));
		  		    ReverseBookId.setBounds(633, 148, 253, 41);
		  		    StudentReservePanel.add(ReverseBookId);
		  		    
		  		    ReserveBookAuthor = new JLabel();
		  		    ReserveBookAuthor.setFont(new Font("Verdana", Font.PLAIN, 14));
		  		    ReserveBookAuthor.setBounds(633, 287, 253, 41);
		  		    StudentReservePanel.add(ReserveBookAuthor);
		  		    
		  		    ReserveBookName = new JLabel();
		  		    ReserveBookName.setFont(new Font("Verdana", Font.PLAIN, 14));
		  		    ReserveBookName.setBounds(633, 218, 253, 41);
		  		    StudentReservePanel.add(ReserveBookName);
		  		    
		  		    BookQuantity = new JLabel();
		  		    BookQuantity.setFont(new Font("Verdana", Font.PLAIN, 14));
		  		    BookQuantity.setBounds(633, 356, 253, 37);
		  		    StudentReservePanel.add(BookQuantity);
		  		    
		  		    ReserveScrollPane = new JScrollPane();
		  		    ReserveScrollPane.setBounds(10, 184, 572, 321);
		  		    ReserveScrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		  		    StudentReservePanel.add(ReserveScrollPane);
		  		    
		  		    ReserveTable = new RSTableMetro();
		  		    ReserveTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		  		    ReserveTable.setMultipleSeleccion(false);
		  		    ReserveTable.addMouseListener(new MouseAdapter() {
		  		    	@Override
		  		    	public void mouseClicked(MouseEvent e) {
		  		    	int rowno = ReserveTable.getSelectedRow();
		  		    	//	System.out.println(rowno);
		  				TableModel model = (DefaultTableModel)ReserveTable.getModel();
		  					
		  				ReverseBookId.setText(model.getValueAt(rowno,0).toString());
		  				ReserveBookName.setText(model.getValueAt( rowno,1).toString());
		  				ReserveBookAuthor.setText(model.getValueAt( rowno,2).toString());
		  				BookQuantity.setText(model.getValueAt( rowno,3).toString());
		  		    	}
		  		    });
		  		    ReserveScrollPane.setViewportView(ReserveTable);
		  		    ReserveTable.setRowHeight(25);
		  		    ReserveTable.setGridColor(Color.BLUE);
		  		    ReserveTable.setFont(new Font("Ubuntu", Font.BOLD, 20));
		  		    ReserveTable.setBorder(null);
		  		    ReserveTable.setBackground(Color.MAGENTA);
		  		    ReserveTable.setAutoCreateRowSorter(true);
		  		    ReserveTable.setAlignmentY(1.0f);
		  		    ReserveTable.setAlignmentX(1.0f);
		  		    ReserveTable.setModel(new DefaultTableModel(
		  		    	new Object[][] {
		  		    	},
		  		    	new String[] {
		  		    		"book_id", "Name", "author", "quantity"
		  		    	}
		  		    ));
		  		    
		  		    BookSearchName = new JTextField();
		  		    BookSearchName.setBounds(48, 101, 226, 37);
		  		    StudentReservePanel.add(BookSearchName);
		  		    BookSearchName.setColumns(10);
		  		    
		  		    JLabel BookNameLabels = new JLabel("Book Name");
		  		    BookNameLabels.setFont(new Font("Ubuntu", Font.PLAIN, 19));
		  		    BookNameLabels.setBounds(48, 67, 198, 23);
		  		    StudentReservePanel.add(BookNameLabels);
		  		    
		  		    JButton btnNewButton = new JButton("Search Book");
		  		    btnNewButton.setFocusable(false);
		  		    btnNewButton.addMouseListener(new MouseAdapter() {
		  		    	@Override
		  		    	public void mouseClicked(MouseEvent e) {
		  		    			model.setRowCount(0);
			  		setBookDetailsName();
				
		  		    		
		  		    	}
		  		    });
		  		    btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  		    btnNewButton.setBorderPainted(false);
		  		    btnNewButton.setFocusTraversalKeysEnabled(false);
		  		    btnNewButton.setFocusPainted(false);
		  		    btnNewButton.setBackground(new Color(51, 204, 0));
		  		    btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 19));
		  		    btnNewButton.setBounds(304, 102, 168, 36);
		  		    StudentReservePanel.add(btnNewButton);
		  		    
		  		    
		  		    
		  		    JButton ReserveBtn = new JButton("ReserveBook");
		  		    ReserveBtn.addMouseListener(new MouseAdapter() {
		  		    	@Override
		  		    	public void mouseClicked(MouseEvent e) {
		  		    		if(reservation()==true) {
		  		    			JOptionPane.showMessageDialog(exit,"reservation of completed");
		  		    		}
		  		    		else {
		  		    			JOptionPane.showMessageDialog(exit,"reservation of unsuccess");
							}
		  		    	}
		  		    });
		  		    ReserveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  		    ReserveBtn.setBorderPainted(false);
		  		    ReserveBtn.setFocusable(false);
		  		    ReserveBtn.setFocusTraversalKeysEnabled(false);
		  		    ReserveBtn.setFocusPainted(false);
		  		    ReserveBtn.setBackground(new Color(51, 204, 0));
		  		    ReserveBtn.setFont(new Font("Ubuntu", Font.PLAIN, 19));
		  		    ReserveBtn.setBounds(634, 442, 252, 37);
		  		    StudentReservePanel.add(ReserveBtn);
		  		    
		  		    
		  		    
		  		    BookDetails = new JLabel("Book Details");
		  		    BookDetails.setFont(new Font("Verdana", Font.PLAIN, 19));
		  		    BookDetails.setBounds(633, 100, 135, 37);
		  		    StudentReservePanel.add(BookDetails);
		  		    //		  panel_5.add(ReserveTable);
		  		    		  
		  		    		  StudentIssuedPanel = new JPanel();
		  		    		  StudentIssuedPanel.setBackground(new Color(153, 255, 255));
		  		    		  tabbedPane.addTab("New tab", null, StudentIssuedPanel, null);
		  		    		  StudentIssuedPanel.setLayout(null);
		  		    		  
		  		    		issuedJScrollPane = new JScrollPane();
		  		    		issuedJScrollPane.setBounds(10, 71, 562, 423);
		  		    		issuedJScrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				  		    StudentIssuedPanel.add(issuedJScrollPane);
				  		    
				  		    IssuedTable = new RSTableMetro();
				  		    IssuedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				  		    IssuedTable.setMultipleSeleccion(false);
				  		    issuedJScrollPane.setViewportView(IssuedTable);
				  		    IssuedTable.setRowHeight(25);
				  		    IssuedTable.setGridColor(Color.BLUE);
				  		    IssuedTable.setFont(new Font("Ubuntu", Font.BOLD, 20));
				  		    IssuedTable.setBorder(null);
				  		    IssuedTable.setBackground(Color.MAGENTA);
				  		    IssuedTable.setAutoCreateRowSorter(true);
				  		    IssuedTable.setAlignmentY(1.0f);
				  		    IssuedTable.setAlignmentX(1.0f);
				  		    IssuedTable.setModel(new DefaultTableModel(
				  		    	new Object[][] {
				  		    	},
				  		    	new String[] {
				  		    			"Status ID", "Book Name",  "Issue Date", "Due Date", "status"
				  		    	}
				  		    ));
				  		    
		  		    		  
		  		    		  JPanel panel_4 = new JPanel();
		  		    		  panel_4.setBounds(623, 71, 295, 423);
		  		    		  StudentIssuedPanel.add(panel_4);
		  		    		  panel_4.setLayout(null);
		  		    		  
		  		    		  ConfirmPasswordField = new JPasswordField();
		  		    		  ConfirmPasswordField.setBounds(10, 215, 262, 33);
		  		    		  panel_4.add(ConfirmPasswordField);
		  		    		  
		  		    		  JLabel lblNewLabel_2 = new JLabel("Reset Password");
		  		    		  lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\reset logo (1).png"));
		  		    		  lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 20));
		  		    		  lblNewLabel_2.setBounds(23, 11, 249, 54);
		  		    		  panel_4.add(lblNewLabel_2);
		  		    		  
		  		    		  JLabel ResetPasswordLable = new JLabel("Enter the new Password");
		  		    		  ResetPasswordLable.setForeground(new Color(0, 0, 0));
		  		    		  ResetPasswordLable.setBackground(new Color(0, 0, 0));
		  		    		  ResetPasswordLable.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		  		    		  ResetPasswordLable.setFont(new Font("Verdana", Font.BOLD, 11));
		  		    		  ResetPasswordLable.setBounds(10, 76, 243, 21);
		  		    		  panel_4.add(ResetPasswordLable);
		  		    		  
		  		    		  JLabel ConfirmPasswordLabel = new JLabel("Confirm your Password");
		  		    		  ConfirmPasswordLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		  		    		  ConfirmPasswordLabel.setBackground(new Color(0, 0, 0));
		  		    		  ConfirmPasswordLabel.setFont(new Font("Verdana", Font.BOLD, 11));
		  		    		  ConfirmPasswordLabel.setBounds(10, 172, 242, 21);
		  		    		  panel_4.add(ConfirmPasswordLabel);
		  		    		  
		  		    		  JButton PaswwordresetBtn = new JButton("Submit");
		  		    		  PaswwordresetBtn.addMouseListener(new MouseAdapter() {
		  		    		  	@SuppressWarnings("deprecation")
								@Override
		  		    		  	public void mouseClicked(MouseEvent e) {
		  		    		  		StudentPanelFunctions studentPanelFunctions  = new StudentPanelFunctions();
		  		    		  		
									String cString = ConfirmPasswordField.getText();
		  		    		  		String nString = NewPasswordField.getText();
		  		    		  		if(studentPanelFunctions.checkstring(cString, nString)) {
		  		    		  			studentPanelFunctions.updatePassword(Integer.valueOf(rollnumberString),cString);
		  		    		  			ConfirmPasswordField.setText("");
		  		    		  			NewPasswordField.setText("");
		  		    		  		}
		  		    		  		else {
										JOptionPane.showMessageDialog(ConfirmPasswordLabel, "Password Not Matched");
									}
		  		    		  	}
		  		    		  });
		  		    		  PaswwordresetBtn.setBackground(new Color(0, 0, 255));
		  		    		  PaswwordresetBtn.setForeground(new Color(255, 255, 255));
		  		    		  PaswwordresetBtn.setFocusable(false);
		  		    		  PaswwordresetBtn.setFocusPainted(false);
		  		    		  PaswwordresetBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  		    		  PaswwordresetBtn.setBorderPainted(false);
		  		    		  PaswwordresetBtn.setFont(new Font("Ubuntu", Font.BOLD, 16));
		  		    		  PaswwordresetBtn.setBounds(71, 292, 139, 39);
		  		    		  panel_4.add(PaswwordresetBtn);
		  		    		  
		  		    		  
		  		    		  NewPasswordField = new JPasswordField();
		  		    		  NewPasswordField.setBounds(10, 123, 262, 28);
		  		    		  panel_4.add(NewPasswordField);
		  		    		  
		  		    		  BackgroundImg = new JLabel("");
		  		    		  BackgroundImg.setBounds(0, 0, 295, 423);
		  		    		ImageIcon icon = new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\reset password.png");
		  		              Image img = icon.getImage();
		  		              
		  		    		  Image newImg = img.getScaledInstance(BackgroundImg.getWidth(), BackgroundImg.getHeight(), Image.SCALE_SMOOTH);
		  		    		ImageIcon image = new ImageIcon(newImg);
		  		    		  BackgroundImg.setIcon(image);
		  		    		  panel_4.add(BackgroundImg);
		  		    		  
		  		    		  lblNewLabel_3 = new JLabel("Issued Books By You");
		  		    		  lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 20));
		  		    		  lblNewLabel_3.setBounds(10, 23, 355, 31);
		  		    		  StudentIssuedPanel.add(lblNewLabel_3);
		  		              table_1.getColumnModel().getColumn(0).setResizable(false);
		  		              table_1.getColumnModel().getColumn(0).setResizable(false);
		  
		     setBookDetailsStudentPanel();
		     bookcount();
		     setAvailableBookDetails();
	         IssuedBookcount();
	         IssuedBookStudent();
	        
	         
	}
}
