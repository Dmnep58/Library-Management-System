package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.border.MatteBorder;
import java.awt.ComponentOrientation;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import rojeru_san.complementos.RSTableMetro;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;


public class HomePage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	private JPanel BookPanel;
	private JPanel studentPanel;
	private JPanel  Issuepanel;
	private JPanel  DefaulterPanel;
	private JPanel panelpiechart;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel;
	private JPanel homepage;
	private JPanel lmsdashboard;
	private JPanel managebook;
	private JPanel manage_student;
	private JPanel issue_book;
	private JPanel Returnbook;
	private JPanel view_record;
	private JPanel view_issue_book;
	private JPanel Defaulter_list;
	private JPanel logout;
	private JPanel panel_3;
	
	private JLabel menubarlabel ;
	private JLabel projectlabel; 
	private JLabel userlabel;
	private JLabel exit;
	private JLabel Homepagelabel;
	private JLabel lmslabel;
	private JLabel feature;
	private JLabel managebooklabel;
	private JLabel managestudentlabel;
	private JLabel issuebooklabel; 
	private JLabel return_book;
	private JLabel viewrecordlabel;
	private JLabel allissuebooklabel;
	private JLabel defaulterlabel;  
	private JLabel logoutlabel;
	private JLabel NoOfBooks;
	private JLabel noStudents;
	private JLabel noIssuedBook;
	private JLabel defaultnumbers;
	private JLabel NoOfBookslabel;
	private JLabel lblNoOfStudents;
	private JLabel IssuedBookscount;
	private JLabel lblDefaulterList;
	private JLabel studenttabellabel;
	private JLabel booktabellabel;
	
	private JScrollPane scrollPane ;
	private JScrollPane scrollPane_1;
	
	private RSTableMetro table,table_1;
	DefaultTableModel model;
	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	
	// to get the student count
	
	public void studentcount() {
		int count = 0;
		try {
			Connection connection = DBconnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from manage_students");
			
			while(resultSet.next()) {
				
				count++;
			}
			noStudents.setText(Integer.toString(count));
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//to get issued book count

	
	public void IssuedBookcount() {
		int count = 0;
		try {
			Connection connection = DBconnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from issue_book where status = '"+"pending"+"'");
			
			while(resultSet.next()) {
				
				count++;
			}
			noIssuedBook.setText(Integer.toString(count));
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	

	//to get Defaulter count
	
	public void Defaultercount() {
		int count = 0;
		long l =System.currentTimeMillis();
		Date today = new Date(l);
		try {
			Connection connection = DBconnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from issue_book where due_date < '"+today+"' and status = '"+"pending"+"'");
			
			while(resultSet.next()) {
				
				count++;
			}
			defaultnumbers.setText(Integer.toString(count));
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	
	// to set the records in tables
		public void setstudentsDetails() {
		
			
			try {
				Connection connection = DBconnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from manage_students");
				
				while(resultSet.next()) {
					String bookIdString = resultSet.getString("student_id");
					String bookNameString = resultSet.getString("Name");
					String authornameString = resultSet.getString("course");
					String bookQuantString = resultSet.getString("Branch");
					
					Object[] objects= {bookIdString, bookNameString, authornameString, bookQuantString};
					
					model=(DefaultTableModel) table.getModel();
					model.addRow(objects);
				}
				
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		
		// to set the records in tables
		public void setBookDetails() {
		
			
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

		
		
		//to show details in pie chart
		
		@SuppressWarnings("removal")
		public void piechart() {
			DefaultPieDataset barDataset = new DefaultPieDataset();
			
			try {
				Connection connection = DBconnection.getConnection();
				String sqlString =("select book_name , count(*) as issue_count from issue_book group by book_id");
				
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlString);
				
				while(resultSet.next()) {
					barDataset.setValue(resultSet.getString("book_name"), new Double(resultSet.getDouble("issue_count")));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			   
		      
		      //create chart
		       JFreeChart piechart = ChartFactory.createPieChart("Issue Book Details",barDataset, true,true,false);//explain
		      
		        PiePlot piePlot =(PiePlot) piechart.getPlot();
		      
		       //changing pie chart blocks colors
		       piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
		        piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
		        piePlot.setSectionPaint("MotoG", new Color(255,102,153));
		        piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));
		      
		       
		        piePlot.setBackgroundPaint(Color.white);
		        
		        //create chartPanel to display chart(graph)
		        ChartPanel barChartPanel = new ChartPanel(piechart);
		        barChartPanel.setBackground(Color.LIGHT_GRAY);
		        panelpiechart.removeAll();
		        panelpiechart.setLayout(new BorderLayout(0, 0));
		        panelpiechart.add(barChartPanel);
		        panelpiechart.validate();

		}
		
		
	/**
	 * Create the frame.
	 */
	public HomePage() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1220, 696);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 102, 255));
		panel_1.setBounds(0, 0, 1220, 73);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
	    menubarlabel = new JLabel("");
		menubarlabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_menu_48px_1.png"));
		menubarlabel.setBounds(10, 11, 55, 51);
		panel_1.add(menubarlabel);
		
		
		projectlabel = new JLabel("Library Management System\r\n");
		projectlabel.setForeground(new Color(255, 255, 255));
		projectlabel.setFont(new Font("Ubuntu", Font.BOLD, 25));
		projectlabel.setBounds(123, 11, 368, 51);
		panel_1.add(projectlabel);
		
		
		
		userlabel = new JLabel("Welcome User");
		userlabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\male_user_50px.png"));
		userlabel.setForeground(new Color(255, 255, 255));
		userlabel.setFont(new Font("Ubuntu", Font.BOLD, 18));
		userlabel.setBounds(934, 11, 207, 51);
		panel_1.add(userlabel);
		
		
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
		panel.setBackground(new Color(51, 51, 51));
		panel.setBounds(0, 59, 288, 637);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		homepage = new JPanel();
		homepage.setBackground(new Color(255, 0, 0));
		homepage.setBounds(0, 28, 288, 41);
		panel.add(homepage);
		homepage.setLayout(null);
		
		Homepagelabel = new JLabel("    Home Page\r\n");
		Homepagelabel.setBounds(34, 0, 178, 41);
		Homepagelabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Home_26px_2.png"));
		Homepagelabel.setForeground(Color.WHITE);
		Homepagelabel.setFont(new Font("Ubuntu", Font.BOLD, 21));
		homepage.add(Homepagelabel);
		
		
		lmsdashboard = new JPanel();
		lmsdashboard.setBackground(new Color(51, 51, 51));
		lmsdashboard.setBounds(0, 79, 288, 41);
		panel.add(lmsdashboard);
		lmsdashboard.setLayout(null);
		
		
		lmslabel = new JLabel("    LMS Dashboard");
		lmslabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Library_26px_1.png"));
		lmslabel.setBounds(31, 0, 258, 41);
		lmslabel.setForeground(Color.WHITE);
		lmslabel.setFont(new Font("Ubuntu", Font.BOLD, 21));
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
		
		managebooklabel = new JLabel("   Manage Books\r\n");
		managebooklabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		managebooklabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageBook manageBooks= new ManageBook();
				manageBooks.setVisible(true);
				dispose();
		}
		});
		managebooklabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Book_26px.png"));
		managebooklabel.setForeground(Color.WHITE);
		managebooklabel.setFont(new Font("Ubuntu", Font.BOLD, 21));
		managebooklabel.setBounds(34, 0, 252, 41);
		managebook.add(managebooklabel);
		
		manage_student = new JPanel();
		manage_student.setLayout(null);
		manage_student.setBackground(new Color(51, 51, 51));
		manage_student.setBounds(0, 223, 288, 41);
		panel.add(manage_student);
		
		managestudentlabel = new JLabel("   Manages Students");
		managestudentlabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				manage_Student students = new manage_Student();
				students.setVisible(true);
				dispose();
			}
		});
		managestudentlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		managestudentlabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Read_Online_26px.png"));
		managestudentlabel.setForeground(Color.WHITE);
		managestudentlabel.setFont(new Font("Ubuntu", Font.BOLD, 21));
		managestudentlabel.setBounds(31, 0, 256, 41);
		manage_student.add(managestudentlabel);
		
		issue_book = new JPanel();
		issue_book.setLayout(null);
		issue_book.setBackground(new Color(51, 51, 51));
		issue_book.setBounds(0, 275, 288, 41);
		panel.add(issue_book);
		
		issuebooklabel = new JLabel("   Issue Book");
		issuebooklabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				isssue_books issuebook = new isssue_books();
				issuebook.setVisible(true);
				dispose();
			}
		});
		issuebooklabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		issuebooklabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Sell_26px.png"));
		issuebooklabel.setForeground(Color.WHITE);
		issuebooklabel.setFont(new Font("Ubuntu", Font.BOLD, 21));
		issuebooklabel.setBounds(31, 0, 256, 41);
		issue_book.add(issuebooklabel);
		
		Returnbook = new JPanel();
		Returnbook.setLayout(null);
		Returnbook.setBackground(new Color(51, 51, 51));
		Returnbook.setBounds(0, 327, 288, 41);
		panel.add(Returnbook);
		
		return_book = new JLabel("   Return Book");
		return_book.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		return_book.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Returnbooks returnbooks = new Returnbooks();
				returnbooks.setVisible(true);
				dispose();
			}
		});
		return_book.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Return_Purchase_26px.png"));
		return_book.setForeground(Color.WHITE);
		return_book.setFont(new Font("Ubuntu", Font.BOLD, 21));
		return_book.setBounds(31, 0, 256, 41);
		Returnbook.add(return_book);
		
		view_record = new JPanel();
		view_record.setLayout(null);
		view_record.setBackground(new Color(51, 51, 51));
		view_record.setBounds(0, 379, 288, 41);
		panel.add(view_record);
		
		viewrecordlabel = new JLabel("   View Records");
		viewrecordlabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewAllRecords viewAllRecords = new ViewAllRecords();
				viewAllRecords.setVisible(true);
				dispose();
			}
		});
		viewrecordlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewrecordlabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_View_Details_26px.png"));
		viewrecordlabel.setForeground(Color.WHITE);
		viewrecordlabel.setFont(new Font("Ubuntu", Font.BOLD, 21));
		viewrecordlabel.setBounds(31, 0, 256, 41);
		view_record.add(viewrecordlabel);
		
		view_issue_book = new JPanel();
		view_issue_book.setLayout(null);
		view_issue_book.setBackground(new Color(51, 51, 51));
		view_issue_book.setBounds(0, 432, 288, 41);
		panel.add(view_issue_book);
		
		allissuebooklabel = new JLabel("   View Issue Books\r\n");
		allissuebooklabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IssueBookRecords issueBookRecords = new IssueBookRecords();
				issueBookRecords.setVisible(true);
				dispose();
			}
		});
		allissuebooklabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		allissuebooklabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Books_26px.png"));
		allissuebooklabel.setForeground(Color.WHITE);
		allissuebooklabel.setFont(new Font("Ubuntu", Font.BOLD, 21));
		allissuebooklabel.setBounds(31, 0, 256, 41);
		view_issue_book.add(allissuebooklabel);
		
		Defaulter_list = new JPanel();
		Defaulter_list.setLayout(null);
		Defaulter_list.setBackground(new Color(51, 51, 51));
		Defaulter_list.setBounds(0, 484, 288, 41);
		panel.add(Defaulter_list);
		
		defaulterlabel = new JLabel("   Defaulters List\r\n");
		defaulterlabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaulterList defaulterList = new DefaulterList();
				defaulterList.setVisible(true);
				dispose();
			}
		});
		defaulterlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		defaulterlabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Conference_26px.png"));
		defaulterlabel.setForeground(Color.WHITE);
		defaulterlabel.setFont(new Font("Ubuntu", Font.BOLD, 21));
		defaulterlabel.setBounds(31, 0, 256, 41);
		Defaulter_list.add(defaulterlabel);
		
		logout = new JPanel();
		logout.setLayout(null);
		logout.setBackground(new Color(51, 102, 255));
		logout.setBounds(0, 548, 288, 41);
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
		
		panel_3 = new JPanel();
		panel_3.setBounds(287, 59, 933, 637);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		BookPanel = new JPanel();
		BookPanel.setBackground(new Color(204, 204, 204));
		BookPanel.setBorder(new MatteBorder(17, 1, 1, 1, (Color) new Color(51, 102, 255)));
		BookPanel.setBounds(72, 73, 139, 92);
		panel_3.add(BookPanel);
		BookPanel.setLayout(null);
		
		NoOfBooks = new JLabel();
		NoOfBooks.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Book_Shelf_50px.png"));
		NoOfBooks.setFont(new Font("Ubuntu", Font.BOLD, 30));
		NoOfBooks.setBounds(10, 26, 119, 55);
		BookPanel.add(NoOfBooks);
		
		 studentPanel = new JPanel();
		studentPanel.setBackground(new Color(204, 204, 204));
		studentPanel.setBorder(new MatteBorder(17, 1, 1, 1, (Color) new Color(255, 0, 51)));
		studentPanel.setLayout(null);
		studentPanel.setBounds(294, 73, 139, 92);
		panel_3.add(studentPanel);
		
		
		noStudents = new JLabel();
		noStudents.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_People_50px.png"));
		noStudents.setFont(new Font("Ubuntu", Font.BOLD, 30));
		noStudents.setBounds(10, 25, 119, 56);
		studentPanel.add(noStudents);
		
		 Issuepanel = new JPanel();
		Issuepanel.setBackground(new Color(204, 204, 204));
		Issuepanel.setBorder(new MatteBorder(17, 1, 1, 1, (Color) new Color(51, 102, 255)));
		Issuepanel.setLayout(null);
		Issuepanel.setBounds(509, 73, 139, 92);
		panel_3.add(Issuepanel);
		
		 noIssuedBook = new JLabel();
		noIssuedBook.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Sell_50px.png"));
		noIssuedBook.setFont(new Font("Ubuntu", Font.BOLD, 30));
		noIssuedBook.setBounds(10, 24, 119, 57);
		Issuepanel.add(noIssuedBook);
		
	    DefaulterPanel = new JPanel();
		DefaulterPanel.setBackground(new Color(204, 204, 204));
		DefaulterPanel.setBorder(new MatteBorder(17, 1, 1, 1, (Color) new Color(255, 0, 51)));
		DefaulterPanel.setLayout(null);
		DefaulterPanel.setBounds(717, 73, 139, 92);
		panel_3.add(DefaulterPanel);
		
		 defaultnumbers = new JLabel();
		defaultnumbers.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_List_of_Thumbnails_50px.png"));
		defaultnumbers.setFont(new Font("Ubuntu", Font.BOLD, 30));
		defaultnumbers.setBounds(10, 26, 119, 55);
		DefaulterPanel.add(defaultnumbers);
		
		NoOfBookslabel = new JLabel("No. Of Books\r\n");
		NoOfBookslabel.setFont(new Font("Ubuntu", Font.BOLD, 14));
		NoOfBookslabel.setBounds(73, 42, 100, 20);
		panel_3.add(NoOfBookslabel);
		
		lblNoOfStudents = new JLabel("No. of Students");
		lblNoOfStudents.setFont(new Font("Ubuntu", Font.BOLD, 14));
		lblNoOfStudents.setBounds(298, 42, 110, 20);
		panel_3.add(lblNoOfStudents);
		
		IssuedBookscount = new JLabel("Issued Books");
		IssuedBookscount.setFont(new Font("Ubuntu", Font.BOLD, 14));
		IssuedBookscount.setBounds(512, 42, 100, 20);
		panel_3.add(IssuedBookscount);
		
		lblDefaulterList = new JLabel("Defaulter list\r\n");
		lblDefaulterList.setFont(new Font("Ubuntu", Font.BOLD, 14));
		lblDefaulterList.setBounds(709, 42, 110, 20);
		panel_3.add(lblDefaulterList);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 258, 518, 132);
		panel_3.add(scrollPane);
		
		table = new RSTableMetro();
		scrollPane.setViewportView(table);
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setGridColor(Color.BLUE);
		table.setRowHeight(25);
		table.setFont(new Font("Ubuntu", Font.BOLD, 20));
		table.setAutoCreateRowSorter(true);
		table.setBackground(Color.MAGENTA);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Student_id", "Name", "Course", "Branch"
			}
		));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 466, 523, 136);
		panel_3.add(scrollPane_1);
		
		
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
		studenttabellabel.setBounds(24, 225, 213, 28);
		panel_3.add(studenttabellabel);
		
		booktabellabel = new JLabel("Books Details");
		booktabellabel.setHorizontalAlignment(SwingConstants.CENTER);
		booktabellabel.setFont(new Font("Ubuntu", Font.BOLD, 25));
		booktabellabel.setBounds(20, 427, 213, 28);
		panel_3.add(booktabellabel);
		
		 panelpiechart = new JPanel();
		panelpiechart.setBounds(553, 264, 370, 338);
		panel_3.add(panelpiechart);
		

	        setstudentsDetails();
	        setBookDetails();
	        bookcount();
	        studentcount();
	        IssuedBookcount();
	        Defaultercount();
	        piechart();
	}
}
