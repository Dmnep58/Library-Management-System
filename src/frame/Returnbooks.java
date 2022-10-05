package frame;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Cursor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Returnbooks extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3826174741473749385L;
	private JPanel contentPane;
	
	private JPanel BookPanel;
	private JPanel BookLinePanel;
	private JPanel ExitPanel;
	private JPanel BackPanel;
	private JPanel DesignBackPanel;
	private JPanel issueLinePanel;
	
	private JLabel booklabel;
	private JLabel issueid;
	private JLabel bookname;
	private JLabel issuedate;
	private JLabel returndate;
	private JLabel studentname;
	private JLabel ExitLabel;
	private JLabel ISSUEDETAILS;
	private JLabel bookid_1;
	private JLabel studentid_1;
	private JLabel BackLabel ;
	private JLabel DesignLabel ;
	
	
	private JTextField bookidfield;
	private JTextField booknamefield;
	private JTextField issuedatefield;
	private JTextField returndatefield;
	private JTextField bookidtext;
	private JTextField studentidtext;
	private JTextField studentnamefield;
	private JTextField noresult;
	
	
	private JButton returnbutton;
	private JButton btnFind;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Returnbooks frame = new Returnbooks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	//to fetch issue book details
	public boolean getissuebookdetails() {
		boolean success = false;
		int bookid = Integer.parseInt(bookidtext.getText());
		int studentid = Integer.parseInt(studentidtext.getText());
		
		try {
			
			Connection connection = DBconnection.getConnection();
			String string =( " select * from issue_book where book_id=? and student_id=? and status=?");
			PreparedStatement pst = connection.prepareStatement(string);
			
			pst.setInt(1, bookid);
			pst.setInt(2, studentid);
			pst.setString(3, "pending");
			
			
			ResultSet resultSet = pst.executeQuery();
			
			if (resultSet.next()) {
				success =true;
				bookidfield.setText(resultSet.getString("id"));
				booknamefield.setText(resultSet.getString("book_name"));
				studentnamefield.setText(resultSet.getString("student_name"));
				issuedatefield.setText(resultSet.getString("issue_date"));
				returndatefield.setText(resultSet.getString("due_date"));
				noresult.setText("");
			}
			else {
				success=false;
				noresult.setText("No Record Found");
				bookidfield.setText("");
				booknamefield.setText("");
				studentnamefield.setText("");
				issuedatefield.setText("");
				returndatefield.setText("");
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;	
	}
	
	// return the book
	
	public boolean bookreturned() {
		boolean isreturned =false;
		int bookid = Integer.parseInt(bookidtext.getText());
		int studentid = Integer.parseInt(studentidtext.getText());
		
		try {
			
			Connection connection = DBconnection.getConnection();
			String string =( "update issue_book set status= ? where book_id=? and student_id=? and status=?");
			PreparedStatement pst = connection.prepareStatement(string);
			
			pst.setString(1, "returned");
			pst.setInt(2, bookid);
			pst.setInt(3, studentid);
			pst.setString(4, "pending");
			
			
			int resultSet = pst.executeUpdate();
			
			if(resultSet>0)
			{
				isreturned= true;
			}
			else {
				isreturned=false;
			}
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return isreturned;
	}
	
	//update the number of books.
	public void updatebook() {
		int bookid = Integer.parseInt(bookidtext.getText());
		
try {
			
			Connection connection = DBconnection.getConnection();
			String query = ("update book_details set Quantity = Quantity+1  where bookId =?");
			PreparedStatement pStatement = connection.prepareStatement(query);
			
			pStatement.setInt(1, bookid);
			int rowcount = pStatement.executeUpdate();
			
			if (rowcount > 0) {
				JOptionPane.showMessageDialog(this,"book count updated");
				
			}
			else {
				JOptionPane.showMessageDialog(this,"can't update book count");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	

	/**
	 * Create the frame.
	 */
	public Returnbooks() {
		setBackground(Color.LIGHT_GRAY);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1169, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BookPanel = new JPanel();
		BookPanel.setBackground(new Color(255, 0, 0));
		BookPanel.setBounds(471, 0, 390, 602);
		contentPane.add(BookPanel);
		BookPanel.setLayout(null);
		
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
		
		issueid = new JLabel("Issue\r\nId:\r\n");
		issueid.setForeground(new Color(255, 255, 255));
		issueid.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		issueid.setBounds(10, 203, 111, 63);
		BookPanel.add(issueid);
		
		bookname = new JLabel("Book Name:");
		bookname.setForeground(new Color(255, 255, 255));
		bookname.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		bookname.setBounds(10, 277, 139, 40);
		BookPanel.add(bookname);
		
		issuedate = new JLabel("Issue Date :\r\n");
		issuedate.setForeground(Color.WHITE);
		issuedate.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		issuedate.setBounds(10, 409, 166, 40);
		BookPanel.add(issuedate);
		
		returndate = new JLabel("Due Date:\r\n");
		returndate.setForeground(Color.WHITE);
		returndate.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		returndate.setBounds(10, 479, 111, 40);
		BookPanel.add(returndate);
		
		bookidfield = new JTextField();
		bookidfield.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		bookidfield.setEditable(false);
		bookidfield.setForeground(new Color(255, 255, 255));
		bookidfield.setBackground(new Color(255, 0, 0));
		bookidfield.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		bookidfield.setBounds(114, 218, 245, 30);
		BookPanel.add(bookidfield);
		bookidfield.setColumns(10);
		
		booknamefield = new JTextField();
		booknamefield.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		booknamefield.setForeground(Color.WHITE);
		booknamefield.setEditable(false);
		booknamefield.setColumns(10);
		booknamefield.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		booknamefield.setBackground(Color.RED);
		booknamefield.setBounds(132, 282, 234, 30);
		BookPanel.add(booknamefield);
		
		issuedatefield = new JTextField();
		issuedatefield.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		issuedatefield.setForeground(Color.WHITE);
		issuedatefield.setEditable(false);
		issuedatefield.setColumns(10);
		issuedatefield.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		issuedatefield.setBackground(Color.RED);
		issuedatefield.setBounds(142, 413, 224, 40);
		BookPanel.add(issuedatefield);
		
		returndatefield = new JTextField();
		returndatefield.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		returndatefield.setForeground(Color.WHITE);
		returndatefield.setEditable(false);
		returndatefield.setColumns(10);
		returndatefield.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		returndatefield.setBackground(Color.RED);
		returndatefield.setBounds(121, 479, 245, 40);
		BookPanel.add(returndatefield);
		
		studentname = new JLabel("Student Name:\r\n");
		studentname.setForeground(new Color(255, 255, 255));
		studentname.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		studentname.setBounds(10, 348, 149, 32);
		BookPanel.add(studentname);
		
		studentnamefield = new JTextField();
		studentnamefield.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		studentnamefield.setForeground(Color.WHITE);
		studentnamefield.setEditable(false);
		studentnamefield.setColumns(10);
		studentnamefield.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		studentnamefield.setBackground(Color.RED);
		studentnamefield.setBounds(169, 342, 207, 38);
		BookPanel.add(studentnamefield);
		
		noresult = new JTextField();
		noresult.setForeground(Color.WHITE);
		noresult.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		noresult.setEditable(false);
		noresult.setColumns(10);
		noresult.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		noresult.setBackground(Color.RED);
		noresult.setBounds(57, 530, 245, 40);
		BookPanel.add(noresult);
		
		ExitPanel = new JPanel();
		ExitPanel.setLayout(null);
		ExitPanel.setBackground(new Color(255, 0, 51));
		ExitPanel.setBounds(1109, 1, 59, 64);
		contentPane.add(ExitPanel);
		
		ExitLabel = new JLabel("  X");
		ExitLabel.setBounds(0, 0, 47, 64);
		ExitPanel.add(ExitLabel);
		ExitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ExitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			 System.exit(ABORT);
			}
		});
		ExitLabel.setForeground(Color.WHITE);
		ExitLabel.setFont(new Font("Ubuntu", Font.BOLD, 35));
		
		ISSUEDETAILS = new JLabel("  ISSUE BOOK\r\n");
		ISSUEDETAILS.setBackground(new Color(255, 255, 255));
		ISSUEDETAILS.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Books_52px_1.png"));
		ISSUEDETAILS.setForeground(new Color(255, 51, 0));
		ISSUEDETAILS.setFont(new Font("Ubuntu", Font.BOLD, 30));
		ISSUEDETAILS.setBounds(869, 76, 262, 71);
		contentPane.add(ISSUEDETAILS);
		
		issueLinePanel = new JPanel();
		issueLinePanel.setForeground(new Color(255, 51, 0));
		issueLinePanel.setBackground(new Color(255, 51, 0));
		issueLinePanel.setBounds(871, 153, 274, 5);
		contentPane.add(issueLinePanel);
		
		bookid_1 = new JLabel("BookId:\r\n");
		bookid_1.setForeground(new Color(255, 51, 0));
		bookid_1.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		bookid_1.setBounds(871, 209, 111, 63);
		contentPane.add(bookid_1);
		
		bookidtext = new JTextField();
		bookidtext.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		bookidtext.setForeground(new Color(51, 0, 204));
		bookidtext.setColumns(10);
		bookidtext.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 51, 0)));
		bookidtext.setBackground(new Color(255, 255, 255));
		bookidtext.setBounds(979, 212, 166, 40);
		contentPane.add(bookidtext);
		
		studentid_1 = new JLabel("studentId:\r\n");
		studentid_1.setForeground(new Color(255, 51, 0));
		studentid_1.setFont(new Font("Ubuntu", Font.PLAIN, 22));
		studentid_1.setBounds(871, 283, 111, 63);
		contentPane.add(studentid_1);
		
		studentidtext = new JTextField();
		studentidtext.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		studentidtext.setForeground(new Color(51, 0, 204));
		studentidtext.setColumns(10);
		studentidtext.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 51, 0)));
		studentidtext.setBackground(new Color(255, 255, 255));
		studentidtext.setBounds(979, 283, 166, 44);
		contentPane.add(studentidtext);
		
		BackPanel = new JPanel();
		BackPanel.setBounds(0, 0, 139, 40);
		contentPane.add(BackPanel);
		BackPanel.setBackground(new Color(51, 51, 153));
		BackPanel.setLayout(null);
		
		BackLabel = new JLabel("  BACK\r\n");
		BackLabel.setBounds(10, 0, 129, 40);
		BackPanel.add(BackLabel);
		BackLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage homePage = new HomePage();
				homePage.setVisible(true);
				dispose();
			}
		});
		BackLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
		BackLabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Rewind_48px.png"));
		BackLabel.setForeground(new Color(255, 255, 255));
		
		DesignBackPanel = new JPanel();
		DesignBackPanel.setBounds(0, 0, 469, 602);
		contentPane.add(DesignBackPanel);
		DesignBackPanel.setLayout(null);
		
		DesignLabel = new JLabel("");
		DesignLabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\library-3.png.png"));
		DesignLabel.setBounds(0, 0, 469, 602);
		DesignBackPanel.add(DesignLabel);
		
		
		returnbutton = new JButton("Return Book\r\n");
		returnbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bookreturned() ==true) {
					JOptionPane.showMessageDialog(returnbutton, "Book returned successful");
					updatebook();
				}
				else {
					JOptionPane.showMessageDialog(returnbutton, "Book returned unsuccessful");
				}
			}
		});
		returnbutton.setForeground(new Color(255, 255, 255));
		returnbutton.setBounds(922, 467, 223, 38);
		contentPane.add(returnbutton);
		
		returnbutton.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		returnbutton.setBorderPainted(false);
		returnbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		returnbutton.setBackground(new Color(255, 0, 51));
		returnbutton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		returnbutton.setFocusTraversalKeysEnabled(false);
		returnbutton.setFocusable(false);
		returnbutton.setFocusPainted(false);
		returnbutton.setFont(new Font("Viner Hand ITC", Font.PLAIN, 22));
		
		btnFind = new JButton("find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getissuebookdetails();
			}
		});
		btnFind.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFind.setForeground(new Color(255, 255, 255));
		btnFind.setFont(new Font("Viner Hand ITC", Font.PLAIN, 22));
		btnFind.setFocusable(false);
		btnFind.setFocusTraversalKeysEnabled(false);
		btnFind.setFocusPainted(false);
		btnFind.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		btnFind.setBorderPainted(false);
		btnFind.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnFind.setBackground(new Color(51, 0, 204));
		btnFind.setBounds(922, 387, 223, 38);
		contentPane.add(btnFind);
		
		
	}
}
