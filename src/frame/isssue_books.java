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
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import function.DBconnection;
import rojeru_san.complementos.RSTableMetro;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import com.toedter.calendar.JDateChooser;

public class isssue_books extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8469380060366589208L;
	
		private JPanel contentPane;
	    private JPanel BookPanel;
		private JPanel BackPanel;
		private JPanel BookLinePanel;
		private JPanel issueLinePanel;
		private JPanel StudentPanel;
		private JPanel StudentLinePanel;
		private JPanel ExitPanel;
		
		private JLabel Back;
		private JLabel booklabel; 
		private JLabel bookid ;
		private JLabel bookname;
		private JLabel authorname;
		private JLabel quantity;
		private JLabel lblStudentsDetails;
		private JLabel studentid;
		private JLabel studentname; 
		private JLabel course;
		private JLabel branch;
		private JLabel Exit;
		private JLabel ISSUEDETAILS;
		private JLabel bookid_1 ;
		private JLabel studentid_1;
		private JLabel issuedate;
		private JLabel deudate;
		
	   private  JButton btnissueBook;
	
	private JTextField bookidfield;
	private JTextField booknamefield;
	private JTextField authornamefield;
	private JTextField quantityfield;
	private JTextField studentidfield;
	private JTextField studentnamefield;
	private JTextField coursefield;
	private JTextField branchfield;
	private JTextField getbookfield;
	private JTextField studentfield;
    private JFormattedTextField today;
    
    JDateChooser dateChooser;
    private JLabel bookimg;
    
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					isssue_books frame = new isssue_books();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


// fetch book details
	public void  getBookDetails() {
		int bookid = Integer.parseInt(getbookfield.getText());
		
		try {
			Connection connection = DBconnection.getConnection();
			String query = ("select * from book_details where bookId = ?");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, bookid);
			
			ResultSet resultSet =preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				bookidfield.setText(resultSet.getString("bookid"));
				booknamefield.setText(resultSet.getString("book_Name"));
				authornamefield.setText(resultSet.getString("Author_Name"));
				quantityfield.setText(resultSet.getString("Quantity"));
			}
			else {
				JOptionPane.showMessageDialog(authornamefield, "book details mismatched");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	
	//fetch book img
	public void fetchimg() {
		String bookname = booknamefield.getText();
		
		try {
			Connection connection = DBconnection.getConnection();
			String query = ("select * from bookimg where name = '"+bookname+"'");
			Statement statement = connection.createStatement();
			ResultSet resultSet =statement.executeQuery(query);
			
			if(resultSet.next()) {
				byte[] img = resultSet.getBytes("image");
				ImageIcon imgIcon = new ImageIcon(img);
				Image img1 = imgIcon.getImage();
				Image myimgImage = img1.getScaledInstance(bookimg.getWidth(), bookimg.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon newimgIcon = new ImageIcon(myimgImage);
				bookimg.setIcon(newimgIcon);
						}
			else {
				JOptionPane.showMessageDialog(authornamefield, "book details mismatched");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	//fetch student details
	
	public void getstudentdetails() {
		int studentid = Integer.parseInt(studentfield.getText());
		
		try {
			Connection connection = DBconnection.getConnection();
			String query = ("select * from manage_students where student_id = ?");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, studentid);
			
			ResultSet resultSet =preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				studentidfield.setText(resultSet.getString("student_Id"));
				studentnamefield.setText(resultSet.getString("Name"));
			    coursefield.setText(resultSet.getString("course"));
				branchfield.setText(resultSet.getString("branch"));
			}
			else {
				JOptionPane.showMessageDialog(authornamefield, "student details mismatched");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
	//get all details required to issue book
	public boolean issued() throws ParseException {
		boolean isissued = false;
		int bookid = Integer.parseInt(getbookfield.getText());
		int studentid = Integer.parseInt(studentfield.getText());
		String bookString = booknamefield.getText();
		String studentString =studentnamefield.getText();
		
		 String issueDate = today.getText();
		 Date date1=new SimpleDateFormat("dd MMM yyyy").parse(issueDate); 
		 
		 long l1 = date1.getTime();
		 
		 

		 
		SimpleDateFormat date2=new SimpleDateFormat("dd MMM yyyy"); 
		String duedateString = date2.format(dateChooser.getDate());
		
		Date date21=new SimpleDateFormat("dd MMM yyyy").parse(duedateString); 
		long l2 = date21.getTime();
		
		java.sql.Date issueddate =new java.sql.Date(l1);
		java .sql.Date duedate = new java.sql.Date(l2);
		
		try {
			
			Connection connection = DBconnection.getConnection();
			String query = ("insert into issue_book(book_id,book_name,student_id,student_name,issue_date,due_date,status) values(? , ? , ? , ? ,? ,?,?)");
			PreparedStatement pStatement = connection.prepareStatement(query);
			
			
			pStatement.setInt(1, bookid);
			pStatement.setString(2, bookString);
			pStatement.setInt(3, studentid);
			pStatement.setString(4, studentString);
			pStatement.setDate(5, issueddate);
			pStatement.setDate(6, duedate);
			pStatement.setString(7, "pending");
			
			
			int rowcount = pStatement.executeUpdate();
			
			if (rowcount > 0) {
				isissued = true;
			}
			else {
				isissued = false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isissued;
		
	}
	
	
	//update the number of books.
	public void updatebook() {
		int bookid = Integer.parseInt(getbookfield.getText());
		
try {
			
			Connection connection = DBconnection.getConnection();
			String query = ("update book_details set Quantity = Quantity-1  where bookId =?");
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, bookid);
			int rowcount = pStatement.executeUpdate();
			
			if (rowcount > 0) {
				JOptionPane.showMessageDialog(this,"book count updated");
				int intialcount = Integer.parseInt(quantityfield.getText());
				quantityfield.setText(Integer.toString(intialcount-1));
				
			}
			else {
				JOptionPane.showMessageDialog(this,"can't update book count");
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
// check book is already issued to that student or not
	public boolean isalreadyisuued() {
		boolean IsAlreadyIssued=false;
		
		int bookId = Integer.parseInt(bookidfield.getText());
		int StudentId = Integer.parseInt(studentidfield.getText());
		
		
		try {
			Connection connection = DBconnection.getConnection();
			String query = ("select * from issue_book where book_id=? and student_id =? and status =?");
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, bookId);
			pStatement.setInt(2, StudentId);
			pStatement.setString(3, "pending");
			
		 ResultSet rowcount = pStatement.executeQuery();
			
			if(rowcount.next()) {
				IsAlreadyIssued=true;
			}
			else {
				IsAlreadyIssued=false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return IsAlreadyIssued;
	}
	
	
	// fetch the book image and the author name from the table.
	public void FetchBookImg() {
		
	}
	
	/**
	 * Create the frame.
	 */
	public isssue_books() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1226, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BookPanel = new JPanel();
		BookPanel.setBackground(new Color(255, 0, 0));
		BookPanel.setBounds(0, 0, 390, 602);
		contentPane.add(BookPanel);
		BookPanel.setLayout(null);
		
		bookimg = new JLabel("");
		bookimg.setBounds(272, 162, 108, 119);
		BookPanel.add(bookimg);
		
		BackPanel = new JPanel();
		BackPanel.setBackground(new Color(51, 51, 153));
		BackPanel.setBounds(0, 0, 139, 40);
		BookPanel.add(BackPanel);
		BackPanel.setLayout(null);
		
		Back = new JLabel("  BACK\r\n");
		Back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage homePage = new HomePage();
				homePage.setVisible(true);
				dispose();
			}
		});
		Back.setFont(new Font("Ubuntu", Font.BOLD, 20));
		Back.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Rewind_48px.png"));
		Back.setForeground(new Color(255, 255, 255));
		Back.setBounds(0, 0, 129, 40);
		BackPanel.add(Back);
		
		booklabel = new JLabel("  Book Details");
		booklabel.setForeground(new Color(255, 255, 255));
		booklabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Literature_100px_1.png"));
		booklabel.setFont(new Font("Ubuntu", Font.BOLD, 30));
		booklabel.setBounds(28, 51, 307, 100);
		BookPanel.add(booklabel);
		
		 BookLinePanel = new JPanel();
		BookLinePanel.setBackground(new Color(255, 255, 255));
		BookLinePanel.setBounds(28, 156, 307, 3);
		BookPanel.add(BookLinePanel);
		
		bookid = new JLabel("BookId:\r\n");
		bookid.setForeground(new Color(255, 255, 255));
		bookid.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		bookid.setBounds(10, 203, 111, 63);
		BookPanel.add(bookid);
		
		bookname = new JLabel("Book Name:");
		bookname.setForeground(new Color(255, 255, 255));
		bookname.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		bookname.setBounds(10, 292, 139, 40);
		BookPanel.add(bookname);
		
		authorname = new JLabel("Author Name :");
		authorname.setForeground(Color.WHITE);
		authorname.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		authorname.setBounds(10, 391, 166, 40);
		BookPanel.add(authorname);
		
		quantity = new JLabel("Quantity :");
		quantity.setForeground(Color.WHITE);
		quantity.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		quantity.setBounds(10, 479, 111, 40);
		BookPanel.add(quantity);
		
		bookidfield = new JTextField();
		bookidfield.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		bookidfield.setEditable(false);
		bookidfield.setForeground(new Color(255, 255, 255));
		bookidfield.setBackground(new Color(255, 0, 0));
		bookidfield.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		bookidfield.setBounds(125, 208, 111, 40);
		BookPanel.add(bookidfield);
		bookidfield.setColumns(10);
		
		booknamefield = new JTextField();
		booknamefield.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		booknamefield.setForeground(Color.WHITE);
		booknamefield.setEditable(false);
		booknamefield.setColumns(10);
		booknamefield.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		booknamefield.setBackground(Color.RED);
		booknamefield.setBounds(141, 292, 225, 38);
		BookPanel.add(booknamefield);
		
		authornamefield = new JTextField();
		authornamefield.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		authornamefield.setForeground(Color.WHITE);
		authornamefield.setEditable(false);
		authornamefield.setColumns(10);
		authornamefield.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		authornamefield.setBackground(Color.RED);
		authornamefield.setBounds(164, 391, 216, 40);
		BookPanel.add(authornamefield);
		
		quantityfield = new JTextField();
		quantityfield.setFont(new Font("Ubuntu", Font.PLAIN, 30));
		quantityfield.setForeground(Color.WHITE);
		quantityfield.setEditable(false);
		quantityfield.setColumns(10);
		quantityfield.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		quantityfield.setBackground(Color.RED);
		quantityfield.setBounds(132, 479, 234, 40);
		BookPanel.add(quantityfield);
		
		StudentPanel = new JPanel();
		StudentPanel.setBackground(new Color(51, 51, 204));
		StudentPanel.setBounds(395, 0, 407, 602);
		contentPane.add(StudentPanel);
		StudentPanel.setLayout(null);
		
		lblStudentsDetails = new JLabel("  Students Details");
		lblStudentsDetails.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Student_Registration_100px_2.png"));
		lblStudentsDetails.setForeground(Color.WHITE);
		lblStudentsDetails.setFont(new Font("Ubuntu", Font.BOLD, 30));
		lblStudentsDetails.setBounds(29, 50, 356, 100);
		StudentPanel.add(lblStudentsDetails);
		
		studentid = new JLabel("studentId:\r\n");
		studentid.setForeground(Color.WHITE);
		studentid.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		studentid.setBounds(10, 192, 120, 63);
		StudentPanel.add(studentid);
		
		studentidfield = new JTextField();
		studentidfield.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		
		studentidfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter !='\b')) {
					e.consume();
				}
				
				
			}
		});
		studentidfield.setForeground(Color.WHITE);
		studentidfield.setEditable(false);
		studentidfield.setColumns(10);
		studentidfield.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		studentidfield.setBackground(new Color(51, 51, 204));
		studentidfield.setBounds(140, 198, 245, 39);
		StudentPanel.add(studentidfield);
		
		studentname = new JLabel("studentName:\r\n");
		studentname.setForeground(Color.WHITE);
		studentname.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		studentname.setBounds(10, 277, 163, 63);
		StudentPanel.add(studentname);
		
		course = new JLabel("course:");
		course.setForeground(Color.WHITE);
		course.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		course.setBounds(10, 368, 120, 63);
		StudentPanel.add(course);
		
		 branch = new JLabel("Branch:");
		branch.setForeground(Color.WHITE);
		branch.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		branch.setBounds(10, 453, 120, 63);
		StudentPanel.add(branch);
		
		studentnamefield = new JTextField();
		studentnamefield.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		studentnamefield.setForeground(Color.WHITE);
		studentnamefield.setEditable(false);
		studentnamefield.setColumns(10);
		studentnamefield.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		studentnamefield.setBackground(new Color(51, 51, 204));
		studentnamefield.setBounds(172, 288, 213, 39);
		StudentPanel.add(studentnamefield);
		
		coursefield = new JTextField();
		coursefield.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		coursefield.setForeground(Color.WHITE);
		coursefield.setEditable(false);
		coursefield.setColumns(10);
		coursefield.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		coursefield.setBackground(new Color(51, 51, 204));
		coursefield.setBounds(140, 378, 245, 39);
		StudentPanel.add(coursefield);
		
		branchfield = new JTextField();
		branchfield.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		branchfield.setForeground(Color.WHITE);
		branchfield.setEditable(false);
		branchfield.setColumns(10);
		branchfield.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		branchfield.setBackground(new Color(51, 51, 204));
		branchfield.setBounds(140, 464, 245, 39);
		StudentPanel.add(branchfield);
		
		StudentLinePanel = new JPanel();
		StudentLinePanel.setForeground(new Color(255, 255, 255));
		StudentLinePanel.setBackground(Color.WHITE);
		StudentLinePanel.setBounds(29, 150, 356, 4);
		StudentPanel.add(StudentLinePanel);
		
		ExitPanel = new JPanel();
		ExitPanel.setLayout(null);
		ExitPanel.setBackground(new Color(255, 0, 51));
		ExitPanel.setBounds(1167, 0, 59, 64);
		contentPane.add(ExitPanel);
		
		Exit = new JLabel("  X");
		Exit.setBounds(0, 0, 47, 64);
		ExitPanel.add(Exit);
		Exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			 System.exit(ABORT);
			}
		});
		Exit.setForeground(Color.WHITE);
		Exit.setFont(new Font("Ubuntu", Font.BOLD, 35));
		
		ISSUEDETAILS = new JLabel("  ISSUE BOOK\r\n");
		ISSUEDETAILS.setBackground(new Color(255, 255, 255));
		ISSUEDETAILS.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Books_52px_1.png"));
		ISSUEDETAILS.setForeground(new Color(255, 51, 0));
		ISSUEDETAILS.setFont(new Font("Ubuntu", Font.BOLD, 30));
		ISSUEDETAILS.setBounds(883, 55, 274, 100);
		contentPane.add(ISSUEDETAILS);
		
		issueLinePanel = new JPanel();
		issueLinePanel.setForeground(new Color(255, 51, 0));
		issueLinePanel.setBackground(new Color(255, 51, 0));
		issueLinePanel.setBounds(883, 150, 274, 5);
		contentPane.add(issueLinePanel);
		
		bookid_1 = new JLabel("BookId:\r\n");
		bookid_1.setForeground(new Color(255, 51, 0));
		bookid_1.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		bookid_1.setBounds(812, 191, 111, 63);
		contentPane.add(bookid_1);
		
		getbookfield = new JTextField();
		getbookfield.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!getbookfield.getText().equals("")) {
				getBookDetails();
			}
			}
		});
		getbookfield.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		getbookfield.setForeground(new Color(51, 0, 204));
		getbookfield.setColumns(10);
		getbookfield.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 51, 0)));
		getbookfield.setBackground(new Color(255, 255, 255));
		getbookfield.setBounds(950, 191, 253, 40);
		contentPane.add(getbookfield);
		
		studentid_1 = new JLabel("studentId:\r\n");
		studentid_1.setForeground(new Color(255, 51, 0));
		studentid_1.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		studentid_1.setBounds(812, 265, 111, 63);
		contentPane.add(studentid_1);
		
		studentfield = new JTextField();
		studentfield.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!studentfield.getText().equals("")) {
				getstudentdetails();
				}
			}
		});
		studentfield.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		studentfield.setForeground(new Color(51, 0, 204));
		studentfield.setColumns(10);
		studentfield.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 51, 0)));
		studentfield.setBackground(new Color(255, 255, 255));
		studentfield.setBounds(950, 265, 253, 43);
		contentPane.add(studentfield);
		
		issuedate = new JLabel("Issue Date:\r\n");
		issuedate.setForeground(new Color(255, 51, 0));
		issuedate.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		issuedate.setBounds(812, 371, 122, 30);
		contentPane.add(issuedate);
		
		deudate = new JLabel("Due Date:");
		deudate.setForeground(new Color(255, 51, 0));
		deudate.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		deudate.setBounds(812, 448, 111, 30);
		contentPane.add(deudate);
		

		  DateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY");
	      today = new JFormattedTextField(dateFormat);
	      today.setEditable(false);
	      today.setFont(new Font("Ubuntu", Font.PLAIN, 20));
	      today.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 0, 51)));
	      today.setColumns(10);
	      today.setValue(new Date());
	      today.setBounds(950, 352, 253, 49);
	      contentPane.add(today);
		
		btnissueBook = new JButton("Issue Book");
		btnissueBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnissueBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(quantityfield.getText().equals(0)) {
					JOptionPane.showMessageDialog(btnissueBook, "No stock");
				}
				else {
					if(isalreadyisuued()==false) {
						try {
							if(issued() == true) {
								JOptionPane.showMessageDialog(btnissueBook, "issued success");
								updatebook();
							}
							else {
								JOptionPane.showMessageDialog(btnissueBook, "book issue unsuccess");
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(btnissueBook, "book already issued ");
					}	
				}
					
				
			}
		});
		btnissueBook.setFont(new Font("Ubuntu", Font.BOLD, 25));
		btnissueBook.setBounds(883, 515, 274, 55);
		contentPane.add(btnissueBook);
		
		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		dateChooser.setDateFormatString("dd MMM yyyy");
		dateChooser.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 0, 102)));
		dateChooser.setBounds(950, 429, 240, 49);
		contentPane.add(dateChooser);
		

		
	}
}
