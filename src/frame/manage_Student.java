	package frame;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import function.App;
import function.DBconnection;
import function.PasswordEncodingDecoding;
import function.StudentPanelFunctions;

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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;

public class manage_Student extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7153418965208042637L;

	private JPanel contentPane;
	private JPanel StudentDataPanel;
	private JPanel BackPanel;
	private JPanel ViewRecordPanel;
	private JPanel EXIT;
	private JPanel TitleLinePanel;

	
	private JLabel STUDENTID ;
	private JLabel STUDENTIDLOGO; 
	private JLabel STUDENTNAME;
    private JLabel STUDENTLOGO;
	private JLabel COURSELABEL;
	private JLabel COURSEICON;
	private JLabel BRANCHNAME;
	private JLabel BRANCHICON;
	private JLabel BackLabel; 
	private JLabel ExitLabel;
	private JLabel TitleLabel;
	
	
	
	private JTextField STUDENTNAMEFIELD;
	private JTextField studentFIELD;
	
	@SuppressWarnings("rawtypes")
	private JComboBox courseselect;
	@SuppressWarnings("rawtypes")
	private JComboBox branchselect;
	
	
	private JButton ADD;
	private JButton UPDATE;
	private JButton DELETE;
	
	private DefaultTableModel model;
	private RSTableMetro table;
	
	private JScrollPane scrollPaneRecords;
	

	private String Name;
	private String course;
	private String branch;
	private int studentid;
	String string;
	
	PasswordEncodingDecoding pwDecoding = new PasswordEncodingDecoding();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manage_Student frame = new manage_Student();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	// to set the records in tables
	public void setStudentDetails() {
	
		
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
				model= (DefaultTableModel)table.getModel();
				model.addRow(objects);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	
	// to add books
	
	public boolean addStudents() {
		boolean isadd = false;
		studentid = Integer.parseInt(studentFIELD.getText());
		Name = STUDENTNAMEFIELD.getText();
		course= courseselect.getSelectedItem().toString();
		branch = branchselect.getSelectedItem().toString();
		string = StudentPanelFunctions.generatePassword(5);
		
		try {
			
			Connection connection = DBconnection.getConnection();
			String query = ("insert into manage_students values(?,?,?,?,?)");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, studentid);
			preparedStatement.setString(2, Name);
			preparedStatement.setString(3, course);
			preparedStatement.setString(4, branch);
			preparedStatement.setString(5, pwDecoding.encrypt(string));
			
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
		String emailString = studentFIELD.getText()+"@kiit.ac.in";
	    String PasswordEmail=
		 		 "Hi "
				+studentFIELD.getText()+","
		 		+ "\r\n"
		 		+ "Your Password is : "
		 		+ string;
		
		
		App.sendEmail(PasswordEmail,App.getSubject(),emailString, App.getFrom());
		JOptionPane.showMessageDialog(TitleLabel,"student password is "+ string);
		
		return isadd;
			
	}
	
	
	/// update table
	public boolean update_table() {
		boolean update =false;
		studentid = Integer.parseInt(studentFIELD.getText());
		Name = STUDENTNAMEFIELD.getText();
		course= courseselect.getSelectedItem().toString();
		branch = branchselect.getSelectedItem().toString();
		
		
		try {
			
			Connection connection = DBconnection.getConnection();
			String query = ("update manage_students set Name=?,course=?,Branch=? where student_id=?");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			
			
			preparedStatement.setString(1, Name);
			preparedStatement.setString(2, course);
			preparedStatement.setString(3, branch);
			preparedStatement.setInt(4, studentid);
			
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
		studentid = Integer.parseInt(studentFIELD.getText());
		
		
		
		try {
			
			Connection connection = DBconnection.getConnection();
			String query = ("delete from manage_students  where student_id=?");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			
		  preparedStatement.setInt(1, studentid);
			
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
	
	

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public manage_Student() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 957, 659);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		StudentDataPanel = new JPanel();
		StudentDataPanel.setBackground(new Color(0, 102, 255));
		StudentDataPanel.setBounds(0, 0, 363, 659);
		contentPane.add(StudentDataPanel);
		StudentDataPanel.setLayout(null);
		
		STUDENTID = new JLabel("Enter Student ID\r\n\r\n");
		STUDENTID.setForeground(Color.WHITE);
		STUDENTID.setFont(new Font("Ubuntu", Font.BOLD, 19));
		STUDENTID.setBounds(104, 91, 171, 31);
		StudentDataPanel.add(STUDENTID);
		
		studentFIELD = new JTextField();
		studentFIELD.setFont(new Font("Ubuntu", Font.BOLD, 16));
		studentFIELD.setColumns(10);
		studentFIELD.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		studentFIELD.setBackground(new Color(0, 102, 255));
		studentFIELD.setBounds(104, 147, 237, 31);
		StudentDataPanel.add(studentFIELD);
		
		STUDENTIDLOGO = new JLabel("");
		STUDENTIDLOGO.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Contact_26px.png"));
		STUDENTIDLOGO.setBounds(45, 122, 49, 46);
		StudentDataPanel.add(STUDENTIDLOGO);
		
		
		STUDENTNAME= new JLabel("Enter Student Name\r\n");
		STUDENTNAME.setForeground(new Color(255, 255, 255));
		STUDENTNAME.setFont(new Font("Ubuntu", Font.BOLD, 19));
		STUDENTNAME.setBounds(104, 209, 197, 31);
		StudentDataPanel.add(STUDENTNAME);
		
		 STUDENTNAMEFIELD = new JTextField();
		 STUDENTNAMEFIELD.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		 STUDENTNAMEFIELD.setBackground(new Color(0, 102, 255));
		 STUDENTNAMEFIELD.setFont(new Font("Ubuntu", Font.BOLD, 16));
		 STUDENTNAMEFIELD.setBounds(104, 252, 237, 31);
		 StudentDataPanel.add(STUDENTNAMEFIELD);
		 STUDENTNAMEFIELD.setColumns(10);
		
		STUDENTLOGO = new JLabel("");
		STUDENTLOGO.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Collaborator_Male_26px.png"));
		STUDENTLOGO.setBounds(45, 227, 49, 46);
		StudentDataPanel.add(STUDENTLOGO);
		
		
		COURSELABEL= new JLabel("Course");
		COURSELABEL.setForeground(new Color(255, 255, 255));
	    COURSELABEL.setFont(new Font("Ubuntu", Font.BOLD, 19));
		COURSELABEL.setBounds(104, 324, 158, 31);
		StudentDataPanel.add(COURSELABEL);
		
		courseselect = new JComboBox();
		courseselect.setFont(new Font("Ubuntu", Font.BOLD, 19));
		courseselect.setModel(new DefaultComboBoxModel(new String[] {"OS", "DBMS ", "Biology", "Chemistry", "ComputerNetworks", "DAA", "SocialStudies"}));
		courseselect.setBounds(104, 373, 237, 31);
		StudentDataPanel.add(courseselect);
		
		
		COURSEICON = new JLabel("");
		COURSEICON.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Moleskine_26px.png"));
		COURSEICON.setBounds(45, 349, 49, 46);
		StudentDataPanel.add(COURSEICON);
		
		BRANCHNAME= new JLabel("Branch");
		BRANCHNAME.setForeground(new Color(255, 255, 255));
		BRANCHNAME.setFont(new Font("Ubuntu", Font.BOLD, 19));
		BRANCHNAME.setBounds(107, 447, 158, 31);
		StudentDataPanel.add(BRANCHNAME);
		
		 branchselect = new JComboBox();
		branchselect.setModel(new DefaultComboBoxModel(new String[] {"CSE", "IT", "+2", "10", "Medical", "BCA"}));
		branchselect.setSelectedIndex(4);
		branchselect.setFont(new Font("Ubuntu", Font.BOLD, 19));
		branchselect.setBounds(104, 489, 237, 31);
		StudentDataPanel.add(branchselect);
		
		
		
		BRANCHICON = new JLabel("");
		BRANCHICON.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Unit_26px.png"));
		BRANCHICON.setBounds(45, 459, 49, 46);
		StudentDataPanel.add(BRANCHICON);
		
		
		
		
		BackPanel = new JPanel();
		BackPanel.setBackground(new Color(255, 0, 102));
		BackPanel.setBounds(0, 0, 165, 52);
		StudentDataPanel.add(BackPanel);
		BackPanel.setLayout(null);
		
		
		ADD = new JButton("ADD");
		ADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addStudents()==true) {
					
					JOptionPane.showMessageDialog(ADD, "student Added");
					cleartable();
					setStudentDetails();
					
						
				}
				else {
					JOptionPane.showMessageDialog(ADD, "student Addition failed");

				}
			}
		});
		ADD.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		ADD.setBorderPainted(false);
		ADD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ADD.setBackground(new Color(255, 0, 51));
		ADD.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ADD.setFocusTraversalKeysEnabled(false);
		ADD.setFocusable(false);
		ADD.setFocusPainted(false);
		ADD.setFont(new Font("Viner Hand ITC", Font.PLAIN, 22));
		ADD.setBounds(28, 571, 80, 38);
		StudentDataPanel.add(ADD);
		
		BackLabel = new JLabel(" Back");
		BackLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				HomePage homePage= new HomePage();
				homePage.setVisible(true);
				dispose();
			}
		});
		BackLabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Rewind_48px.png"));
		BackLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackLabel.setForeground(Color.WHITE);
		BackLabel.setFont(new Font("Ubuntu", Font.BOLD, 30));
		BackLabel.setBackground(new Color(255, 0, 51));
		BackLabel.setBounds(10, 0, 152, 50);
		BackPanel.add(BackLabel);
		
		UPDATE = new JButton("UPDATE");
		UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(update_table()==true) {
					JOptionPane.showMessageDialog(UPDATE, "Student details  updated");
					cleartable();
					setStudentDetails();
					
				}
				else {
					JOptionPane.showMessageDialog(UPDATE, "Student details UPDATION failed");

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
		StudentDataPanel.add(UPDATE);
		
		DELETE = new JButton("DELETE");
		DELETE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				if(delete_details()==true) {
					JOptionPane.showMessageDialog(DELETE, "Student details DELETED");
					cleartable();
					setStudentDetails();
					
				}
				else {
					JOptionPane.showMessageDialog(DELETE, "Student details DELETION failed");

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
		StudentDataPanel.add(DELETE);
		
		
		
		
		
		
		ViewRecordPanel = new JPanel();
		ViewRecordPanel.setAutoscrolls(true);
		ViewRecordPanel.setBackground(new Color(255, 255, 255));
		ViewRecordPanel.setBounds(353, 0, 604, 659);
		contentPane.add(ViewRecordPanel);
		ViewRecordPanel.setLayout(null);
		
		EXIT = new JPanel();
		EXIT.setLayout(null);
		EXIT.setBackground(new Color(102, 153, 255));
		EXIT.setBounds(541, 0, 63, 52);
		ViewRecordPanel.add(EXIT);
		
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
		
		TitleLabel = new JLabel("Manage Students");
		TitleLabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Student_Male_100px.png"));
		TitleLabel.setForeground(new Color(255, 102, 51));
		TitleLabel.setFont(new Font("Ubuntu", Font.BOLD, 40));
		TitleLabel.setBounds(72, 39, 439, 92);
		ViewRecordPanel.add(TitleLabel);
		
		TitleLinePanel = new JPanel();
		TitleLinePanel.setBackground(new Color(255, 102, 0));
		TitleLinePanel.setBounds(82, 133, 429, 3);
		ViewRecordPanel.add(TitleLinePanel);
		
		scrollPaneRecords = new JScrollPane();
		scrollPaneRecords.setBounds(27, 222, 548, 244);
		ViewRecordPanel.add(scrollPaneRecords);
		scrollPaneRecords.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		
		table = new RSTableMetro();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setMultipleSeleccion(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowno = table.getSelectedRow();
				System.out.println(rowno);
				TableModel model = (DefaultTableModel)table.getModel();
				
				
				studentFIELD.setText(model.getValueAt(rowno,0).toString());
			    STUDENTNAMEFIELD.setText(model.getValueAt(rowno,1).toString());
				courseselect.setSelectedItem(model.getValueAt( rowno,2).toString());
				branchselect.setSelectedItem(model.getValueAt(rowno,3).toString());
			}
		});
		
		scrollPaneRecords.setViewportView(table);
		table.setGridColor(Color.BLUE);
		table.setRowHeight(34);
		table.setFont(new Font("Ubuntu", Font.BOLD, 20));
		table.setAutoCreateRowSorter(true);
		table.setBackground(Color.MAGENTA);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"StudentID", "Name", "Course", "Branch"
			}
		));
		
		
		setStudentDetails();
		
	}
}

