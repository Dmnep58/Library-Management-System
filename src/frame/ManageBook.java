package frame;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import function.DBconnection;

import java.awt.Color;
import java.awt.ComponentOrientation;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Cursor;
import rojeru_san.complementos.RSTableMetro;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;



public class ManageBook extends JFrame {


	private static final long serialVersionUID = -2652204162098445491L;

	private JPanel contentPane;
	private JPanel StudentDetailPanel;
	private JPanel StudentRecordPanel;
	private JPanel BackPanel;
	private JPanel EXIT;
	private JPanel TitleLinePanel;
	private JPanel TabelPanel;

	
	private JLabel Back;
	private JLabel BOOKID ;
	private JLabel BOOKIDLOGO;
	private JLabel BOOKNAME;
	private JLabel BOOKLOGO;
	private JLabel AUTHORNAME;
	private JLabel AUTHORNAMEICON;
	private JLabel QUANTITY;
	private JLabel QUANTITYLOGO;
	private JLabel ExitLabel; 
	private JLabel TitleLabel;
	
	private JButton ADD;
	private JButton UPDATE;
	private JButton DELETE;
	
	
	

	
	private JTextField AUTHORNAMEFIELD;
	private JTextField  BOOKNAMEFIELD;
	private JTextField QUANTITYFIELD;
	private JTextField BOOKFIELD;
	
	private DefaultTableModel model;
	private RSTableMetro table;
	
	private JScrollPane scrollPane;
	
	private String BookName;
	private String authorname;
	private int bookid;
	private int quantity;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageBook frame = new ManageBook();
					frame.setVisible(true);
				} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getCause());
				}
			}
		});
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
				model= (DefaultTableModel)table.getModel();
				model.addRow(objects);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	
	// to add books
	public boolean addBook() {
		boolean isadd = false;
		bookid = Integer.parseInt(BOOKFIELD.getText());
		BookName = BOOKNAMEFIELD.getText();
		authorname= AUTHORNAMEFIELD.getText();
		quantity = Integer.parseInt(QUANTITYFIELD.getText());
		
		
		try {
			
			Connection connection = DBconnection.getConnection();
			String query = ("insert into book_details values(?,?,?,?)");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, bookid);
			preparedStatement.setString(2, BookName);
			preparedStatement.setString(3, authorname);
			preparedStatement.setInt(4, quantity);
			
			int rowcount = preparedStatement.executeUpdate();
			 if(rowcount >0) {
				 isadd = true;
			 }
			 else {
				isadd = false;
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return isadd;
		
		
	}
	
	
	/// update table
	public boolean update_table() {
		boolean update =false;
		bookid = Integer.parseInt(BOOKFIELD.getText());
		BookName = BOOKNAMEFIELD.getText();
		authorname= AUTHORNAMEFIELD.getText();
		quantity = Integer.parseInt(QUANTITYFIELD.getText());
		
		
		try {
			
			Connection connection = DBconnection.getConnection();
			String query = ("update book_details set book_Name=?,author_Name=?,Quantity=? where bookId=?");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			
			preparedStatement.setString(1, BookName);
			preparedStatement.setString(2, authorname);
			preparedStatement.setInt(3, quantity);
			preparedStatement.setInt(4, bookid);
			
			int rowcount = preparedStatement.executeUpdate();
			 if(rowcount >0) {
				 update = true;
			 }
			 else {
			 update = false;
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return update;
		
	}
	
	
	// to delete data
	
	public boolean delete_details() {
		boolean delete =false;
		bookid = Integer.parseInt(BOOKFIELD.getText());
		
		
		
		try {
			
			Connection connection = DBconnection.getConnection();
			String query = ("delete from book_details  where bookId=?");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			
		  preparedStatement.setInt(1, bookid);
			
			int rowcount = preparedStatement.executeUpdate();
			 if(rowcount >0) {
				 delete = true;
			 }
			 else {
			 delete = false;
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return delete;
		
	}
	
	
	
	// clear table
	
	public void cleartable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
	}
	
	
	//insert the image in the table
	public void BookImg() {
		bookid = Integer.parseInt(BOOKFIELD.getText());
		
		try {
			
			Connection connection = DBconnection.getConnection();
			String query = ("Insert into BookImg values(?,?)");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, bookid);
			
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	

	/**
	 * Create the frame.
	 */
	public ManageBook() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 957, 659);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		StudentDetailPanel = new JPanel();
		StudentDetailPanel.setForeground(new Color(255, 255, 255));
		StudentDetailPanel.setBackground(new Color(0, 102, 255));
		StudentDetailPanel.setBounds(0, 0, 363, 659);
		contentPane.add(StudentDetailPanel);
		StudentDetailPanel.setLayout(null);
		
		BOOKID = new JLabel("Enter Book ID\r\n\r\n");
		BOOKID.setForeground(new Color(255, 255, 255));
		BOOKID.setFont(new Font("Ubuntu", Font.BOLD, 19));
		BOOKID.setBounds(104, 91, 171, 31);
		StudentDetailPanel.add(BOOKID);
		
		BOOKFIELD = new JTextField();
		BOOKFIELD.setFont(new Font("Ubuntu", Font.BOLD, 16));
		BOOKFIELD.setColumns(10);
		BOOKFIELD.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		BOOKFIELD.setBackground(new Color(0, 102, 255));
		BOOKFIELD.setBounds(104, 147, 237, 31);
		StudentDetailPanel.add(BOOKFIELD);
		
		BOOKIDLOGO = new JLabel("");
		BOOKIDLOGO.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Contact_26px.png"));
		BOOKIDLOGO.setBounds(45, 122, 49, 46);
		StudentDetailPanel.add(BOOKIDLOGO);
		
		
		BOOKNAME= new JLabel("Enter Book Name\r\n");
		BOOKNAME.setForeground(new Color(255, 255, 255));
		BOOKNAME.setFont(new Font("Ubuntu", Font.BOLD, 19));
		BOOKNAME.setBounds(104, 209, 171, 31);
		StudentDetailPanel.add(BOOKNAME);
		
		 BOOKNAMEFIELD = new JTextField();
		 BOOKNAMEFIELD.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		 BOOKNAMEFIELD.setBackground(new Color(0, 102, 255));
		 BOOKNAMEFIELD.setFont(new Font("Ubuntu", Font.BOLD, 16));
		 BOOKNAMEFIELD.setBounds(104, 252, 237, 31);
		 StudentDetailPanel.add(BOOKNAMEFIELD);
		 BOOKNAMEFIELD.setColumns(10);
		
		BOOKLOGO = new JLabel("");
		BOOKLOGO.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Moleskine_26px.png"));
		BOOKLOGO.setBounds(45, 227, 49, 46);
		StudentDetailPanel.add(BOOKLOGO);
		
		
		AUTHORNAME= new JLabel("Author Name");
		AUTHORNAME.setForeground(new Color(255, 255, 255));
	   AUTHORNAME.setFont(new Font("Ubuntu", Font.BOLD, 19));
		AUTHORNAME.setBounds(104, 324, 158, 31);
		StudentDetailPanel.add(AUTHORNAME);
		
		 AUTHORNAMEFIELD = new JTextField();
		AUTHORNAMEFIELD.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		AUTHORNAMEFIELD.setBackground(new Color(0, 102, 255));
		AUTHORNAMEFIELD.setFont(new Font("Ubuntu", Font.BOLD, 16));
		AUTHORNAMEFIELD.setBounds(107, 366, 237, 31);
		StudentDetailPanel.add(AUTHORNAMEFIELD);
		AUTHORNAMEFIELD.setColumns(10);
		
		AUTHORNAMEICON = new JLabel("");
		AUTHORNAMEICON.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Collaborator_Male_26px.png"));
		AUTHORNAMEICON.setBounds(45, 349, 49, 46);
		StudentDetailPanel.add(AUTHORNAMEICON);
		
		QUANTITY= new JLabel("Book Quantity");
		QUANTITY.setForeground(new Color(255, 255, 255));
		QUANTITY.setFont(new Font("Ubuntu", Font.BOLD, 19));
		QUANTITY.setBounds(107, 447, 158, 31);
		StudentDetailPanel.add(QUANTITY);
		
		 QUANTITYFIELD = new JTextField();
		 QUANTITYFIELD.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		QUANTITYFIELD.setBackground(new Color(0, 102, 255));
		QUANTITYFIELD.setFont(new Font("Ubuntu", Font.BOLD, 16));
		QUANTITYFIELD.setBounds(107, 489, 237, 31);
		StudentDetailPanel.add(QUANTITYFIELD);
		QUANTITYFIELD.setColumns(10);
		
		QUANTITYLOGO = new JLabel("");
		QUANTITYLOGO.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Unit_26px.png"));
		QUANTITYLOGO.setBounds(45, 459, 49, 46);
		StudentDetailPanel.add(QUANTITYLOGO);
		
		
		
		
		BackPanel = new JPanel();
		BackPanel.setBackground(new Color(255, 0, 102));
		BackPanel.setBounds(0, 0, 165, 52);
		StudentDetailPanel.add(BackPanel);
		BackPanel.setLayout(null);
		
		
		ADD = new JButton("ADD");
		ADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addBook()==true) {
					JOptionPane.showMessageDialog(ADD, "Book Added");
					cleartable();
					setBookDetails();
				}
				else {
					JOptionPane.showMessageDialog(ADD, "Book Addition failed");

				}
			}
		});
		ADD.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		ADD.setBorderPainted(false);
		ADD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ADD.setBackground(new Color(255, 0, 51));
		ADD.setBorder (new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ADD.setFocusTraversalKeysEnabled(false);
		ADD.setFocusable(false);
		ADD.setFocusPainted(false);
		ADD.setFont(new Font("Viner Hand ITC", Font.PLAIN, 22));
		ADD.setBounds(28, 571, 80, 38);
		StudentDetailPanel.add(ADD);
		
		Back = new JLabel(" Back");
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				HomePage homePage= new HomePage();
				homePage.setVisible(true);
				dispose();
			}
		});
		Back.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Rewind_48px.png"));
		Back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Back.setForeground(Color.WHITE);
		Back.setFont(new Font("Ubuntu", Font.BOLD, 30));
		Back.setBackground(new Color(0, 102, 255));
		Back.setBounds(10, 0, 152, 50);
		BackPanel.add(Back);
		
		UPDATE = new JButton("UPDATE");
		UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(update_table()==true) {
					JOptionPane.showMessageDialog(UPDATE, "Book updated");
					cleartable();
					setBookDetails();
					
				}
				else {
					JOptionPane.showMessageDialog(UPDATE, "Book UPDATION failed");

				}
				
			}
		});
		UPDATE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		UPDATE.setFont(new Font("Viner Hand ITC", Font.PLAIN, 22));
		UPDATE.setFocusable(false);
		UPDATE.setFocusTraversalKeysEnabled(false);
		UPDATE.setFocusPainted(false);
		UPDATE.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		UPDATE.setBorderPainted(false);
		UPDATE.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		UPDATE.setBackground(new Color(255, 0, 51));
		UPDATE.setBounds(118, 571, 110, 38);
		StudentDetailPanel.add(UPDATE);
		
		DELETE = new JButton("DELETE");
		DELETE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				if(delete_details()==true) {
					JOptionPane.showMessageDialog(DELETE, "Book DELETE");
					cleartable();
					setBookDetails();
					
				}
				else {
					JOptionPane.showMessageDialog(DELETE, "Book DELETION failed");

				}
				
			}
		});
		DELETE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DELETE.setFont(new Font("Viner Hand ITC", Font.PLAIN, 22));
		DELETE.setFocusable(false);
		DELETE.setFocusTraversalKeysEnabled(false);
		DELETE.setFocusPainted(false);
		DELETE.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		DELETE.setBorderPainted(false);
		DELETE.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		DELETE.setBackground(new Color(255, 0, 51));
		DELETE.setBounds(238, 571, 106, 38);
		StudentDetailPanel.add(DELETE);
		
		
		
		
		
		StudentRecordPanel = new JPanel();
		StudentRecordPanel.setAutoscrolls(true);
		StudentRecordPanel.setBackground(new Color(255, 255, 255));
		StudentRecordPanel.setBounds(353, 0, 604, 659);
		contentPane.add(StudentRecordPanel);
		StudentRecordPanel.setLayout(null);
		
		EXIT = new JPanel();
		EXIT.setLayout(null);
		EXIT.setBackground(new Color(102, 153, 255));
		EXIT.setBounds(541, 0, 63, 52);
		StudentRecordPanel.add(EXIT);
		
		ExitLabel = new JLabel("  x");
		ExitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ExitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(ABORT);
			}
		});
		ExitLabel.setForeground(Color.WHITE);
		ExitLabel.setFont(new Font("Ubuntu", Font.BOLD, 40));
		ExitLabel.setBackground(new Color(255, 0, 51));
		ExitLabel.setBounds(0, 0, 63, 50);
		EXIT.add(ExitLabel);
		
		TitleLabel = new JLabel("Manage Books");
		TitleLabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Books_52px_1.png"));
		TitleLabel.setForeground(new Color(255, 102, 51));
		TitleLabel.setFont(new Font("Ubuntu", Font.BOLD, 40));
		TitleLabel.setBounds(120, 76, 357, 52);
		StudentRecordPanel.add(TitleLabel);
		
		TitleLinePanel = new JPanel();
		TitleLinePanel.setBackground(new Color(255, 102, 0));
		TitleLinePanel.setBounds(130, 134, 338, 4);
		StudentRecordPanel.add(TitleLinePanel);
		
		TabelPanel = new JPanel();
		TabelPanel.setBounds(27, 238, 548, 303);
		StudentRecordPanel.add(TabelPanel);
		TabelPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrollPane.setFocusable(false);
		scrollPane.setFocusTraversalKeysEnabled(false);
		scrollPane.setBounds(0, 0, 548, 303);
		TabelPanel.add(scrollPane);
		
		table = new RSTableMetro();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowno = table.getSelectedRow();
				TableModel model = (DefaultTableModel)table.getModel();
				
				
				BOOKFIELD.setText(model.getValueAt(rowno,0).toString());
				BOOKNAMEFIELD.setText(model.getValueAt(rowno, 1).toString());
				AUTHORNAMEFIELD.setText(model.getValueAt(rowno, 2).toString());
				QUANTITYFIELD.setText(model.getValueAt(rowno, 3).toString());
			}
		});
		
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
				"BookID", "BookName", "Authorname", "Quantity"
			}
		));
		
		
		
		
		setBookDetails();
		
	}
}
