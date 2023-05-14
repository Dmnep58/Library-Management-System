package frame;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;


import function.DBconnection;
import function.PasswordEncodingDecoding;
import function.StudentPanelFunctions;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import javax.swing.DebugGraphics;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class LoginPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 134203147940197996L;
	
	private JPanel contentPane;
	private JPanel AboutPanel;
	private JLabel ProgrammerName; 
	private JLabel titlelabel;
	private JLabel titlelabel_1;
	private JLabel designlabel;
	private JTabbedPane tabbedPane_1;
	private JPanel loginPanel;
	private JLabel loginlabel;
	private JLabel narrationlabel;
	private JLabel passwordlabel;
	private JLabel UserNameLabel;
	public static JTextField userTeacherFiled;
	private JLabel passwordsymbollabel;
	private JLabel emailsymbollabel;
	private JButton btnLogin;
	private JButton btnSignup;
	private JPasswordField passwordField;
	private JButton exitbtn;
	
	
	
	private JPanel StudentLoginPanel;
	private JLabel loginlabel_1;
	private JLabel narrationlabel_1;
	private JLabel StudentPasswordLabel;
	private JLabel StudentRollNoLabel;
	public static JTextField StudentRollNo_txt;
	private JLabel passwordsymbollabel_1;
	private JLabel RollNumberSymbol;
	private JButton StudentLoginBtn;
	private JPasswordField StudentPassword_Txt;
	private JButton exitbtn_1;
	private JLabel lblNewLabel;
	private JButton DetailsSubmitBtn;
	private JLabel frontImg;
	
	PasswordEncodingDecoding psDecoding = new PasswordEncodingDecoding();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//method to connect database;
	public void logindata() {
		@SuppressWarnings("deprecation")
		String pasString= passwordField.getText();
		String passwordString = psDecoding.encrypt(pasString);
		String userString =userTeacherFiled.getText();
	
		
		try {
			Connection connection = DBconnection.getConnection();
			String queryString=("select * from signup where name =? and password =?");
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			
			preparedStatement.setString(1, userString);
			preparedStatement.setString(2, passwordString);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				JOptionPane.showMessageDialog(this, "login success");
				HomePage home = new HomePage();
				home.setVisible(true);
				
				
			}
			else {
				JOptionPane.showMessageDialog(this, "Invalid username or password");
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	
	// validation of the signup 
	@SuppressWarnings("unlikely-arg-type")
	public boolean validation() {
		
		@SuppressWarnings({ "deprecation", "unused" })
		String passswordString =  passwordField.getText();
		
		String usernameString = userTeacherFiled.getText();
	
		if(passwordField.equals("")) {
			JOptionPane.showConfirmDialog(this,"Please enter Password");
			return false;
		}
		if(usernameString.equals("")) {
			JOptionPane.showConfirmDialog(this,"Please enter a valid username");
			return false;
		}
		
		return true;
		
	}
	
	
	// login record in login table
	public void login() {
		String userString=userTeacherFiled.getText();
		
		try {
			Connection connection = DBconnection.getConnection();
			String queryString="Insert into login(Email,logtime) values(?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			
			preparedStatement.setString(1,userString);
			preparedStatement.setTimestamp(2,new java.sql.Timestamp(new java.util.Date().getTime()));
			
			 preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1131, 645);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		AboutPanel = new JPanel();
		AboutPanel.setBackground(new Color(255, 255, 255));
		AboutPanel.setBounds(0, 0, 758, 645);
		contentPane.add(AboutPanel);
		AboutPanel.setLayout(null);
		
		ProgrammerName = new JLabel("8th Semester Project");
		ProgrammerName.setForeground(new Color(255, 0, 0));
		ProgrammerName.setFont(new Font("Ubuntu", Font.BOLD, 19));
		ProgrammerName.setBounds(10, 11, 244, 27);
		AboutPanel.add(ProgrammerName);
		
		titlelabel = new JLabel("Welcome To\r\n");
		titlelabel.setForeground(new Color(255, 51, 102));
		titlelabel.setFont(new Font("Ubuntu", Font.BOLD, 29));
		titlelabel.setBounds(152, 59, 200, 51);
		AboutPanel.add(titlelabel);
		
		titlelabel_1 = new JLabel("Advance Library\r\n");
		titlelabel_1.setForeground(new Color(255, 51, 51));
		titlelabel_1.setFont(new Font("Ubuntu", Font.BOLD, 29));
		titlelabel_1.setBounds(337, 91, 227, 37);
		AboutPanel.add(titlelabel_1);
		
		designlabel = new JLabel("");
		designlabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\library-3.png.png"));
		designlabel.setBounds(0, 59, 758, 586);
		AboutPanel.add(designlabel);
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(745, -27, 396, 686);
		contentPane.add(tabbedPane_1);
		
		JPanel OptionPanel = new JPanel();
		OptionPanel.setBackground(new Color(102, 102, 255));
		tabbedPane_1.addTab("New tab", null, OptionPanel, null);
		OptionPanel.setLayout(null);
		
		JLabel LoginLabelStarting = new JLabel("Welcome,");
		LoginLabelStarting.setForeground(Color.WHITE);
		LoginLabelStarting.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		LoginLabelStarting.setBackground(Color.WHITE);
		LoginLabelStarting.setBounds(21, 25, 198, 55);
		OptionPanel.add(LoginLabelStarting);
		
		JLabel narrationlabel_2 = new JLabel(" Be a Part of Advance Learning");
		narrationlabel_2.setForeground(Color.WHITE);
		narrationlabel_2.setFont(new Font("Ubuntu", Font.ITALIC, 19));
		narrationlabel_2.setBounds(72, 77, 293, 31);
		OptionPanel.add(narrationlabel_2);
		
		JButton exitbtn_2 = new JButton("");
		exitbtn_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitbtn_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(ABORT);
			}
		});
		exitbtn_2.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Exit_26px_2.png"));
		exitbtn_2.setFocusable(false);
		exitbtn_2.setFocusTraversalKeysEnabled(false);
		exitbtn_2.setFocusPainted(false);
		exitbtn_2.setBorderPainted(false);
		exitbtn_2.setBackground(new Color(102, 102, 255));
		exitbtn_2.setBounds(321, 0, 44, 46);
		OptionPanel.add(exitbtn_2);
		
		lblNewLabel = new JLabel("Choose your Designation");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNewLabel.setBounds(21, 370, 214, 22);
		OptionPanel.add(lblNewLabel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Ubuntu", Font.BOLD, 18));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Student", "Teacher"}));
		comboBox.setBounds(21, 403, 293, 31);
		OptionPanel.add(comboBox);
		
		DetailsSubmitBtn = new JButton("Submit");
		DetailsSubmitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DetailsSubmitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = comboBox.getSelectedItem().toString();
				
				if(str.equalsIgnoreCase("student")) {
					tabbedPane_1.setSelectedIndex(2);
				}
				
				if(str.equalsIgnoreCase("teacher")) {
					tabbedPane_1.setSelectedIndex(1);
				}
				
				if(str.equalsIgnoreCase("None")) {
					JOptionPane.showMessageDialog(titlelabel, "Please select An Option");
				}
				
			}
		});
		DetailsSubmitBtn.setForeground(new Color(255, 255, 255));
		DetailsSubmitBtn.setFont(new Font("Ubuntu", Font.BOLD, 22));
		DetailsSubmitBtn.setFocusable(false);
		DetailsSubmitBtn.setFocusTraversalKeysEnabled(false);
		DetailsSubmitBtn.setFocusPainted(false);
		DetailsSubmitBtn.setBorderPainted(false);
		DetailsSubmitBtn.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
		DetailsSubmitBtn.setBackground(new Color(51, 51, 255));
		DetailsSubmitBtn.setAlignmentX(1.0f);
		DetailsSubmitBtn.setBounds(54, 475, 237, 46);
		OptionPanel.add(DetailsSubmitBtn);
		
		frontImg = new JLabel("");
		frontImg.setBounds(41, 129, 314, 210);
		ImageIcon icon = new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\front.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(frontImg.getWidth(), frontImg.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        frontImg.setIcon(image);
		OptionPanel.add(frontImg);
		
		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setBackground(new Color(102, 102, 255));
		tabbedPane_1.addTab("New tab", null, loginPanel, null);
		
		loginlabel = new JLabel("Login Page\r\n");
		loginlabel.setForeground(Color.WHITE);
		loginlabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		loginlabel.setBackground(Color.WHITE);
		loginlabel.setBounds(104, 21, 198, 55);
		loginPanel.add(loginlabel);
		
		narrationlabel = new JLabel("Welcome, Login To Your Account");
		narrationlabel.setForeground(Color.WHITE);
		narrationlabel.setFont(new Font("Ubuntu", Font.ITALIC, 19));
		narrationlabel.setBounds(48, 71, 294, 31);
		loginPanel.add(narrationlabel);
		
		passwordlabel = new JLabel("Password\r\n");
		passwordlabel.setForeground(Color.WHITE);
		passwordlabel.setFont(new Font("Ubuntu", Font.BOLD, 19));
		passwordlabel.setBounds(90, 258, 121, 31);
		loginPanel.add(passwordlabel);
		
		UserNameLabel = new JLabel("User Name");
		UserNameLabel.setForeground(Color.WHITE);
		UserNameLabel.setFont(new Font("Ubuntu", Font.BOLD, 19));
		UserNameLabel.setBounds(90, 148, 121, 31);
		loginPanel.add(UserNameLabel);
		
		userTeacherFiled = new JTextField();
		userTeacherFiled.setFont(new Font("Ubuntu", Font.BOLD, 16));
		userTeacherFiled.setColumns(10);
		userTeacherFiled.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		userTeacherFiled.setBackground(new Color(102, 102, 255));
		userTeacherFiled.setBounds(90, 185, 237, 31);
		loginPanel.add(userTeacherFiled);
		
		passwordsymbollabel = new JLabel("");
		passwordsymbollabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\icons8_Secure_50px.png"));
		passwordsymbollabel.setBounds(31, 274, 49, 46);
		loginPanel.add(passwordsymbollabel);
		
		emailsymbollabel = new JLabel("");
		emailsymbollabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\icons8_Account_50px.png"));
		emailsymbollabel.setBounds(31, 166, 49, 46);
		loginPanel.add(emailsymbollabel);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validation()==true) {
					login();
					logindata();
				}
			}
		});
		
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Ubuntu", Font.BOLD, 22));
		btnLogin.setFocusable(false);
		btnLogin.setFocusTraversalKeysEnabled(false);
		btnLogin.setFocusPainted(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
		btnLogin.setBackground(new Color(51, 51, 255));
		btnLogin.setAlignmentX(1.0f);
		btnLogin.setBounds(76, 361, 237, 46);
		loginPanel.add(btnLogin);
		
		btnSignup = new JButton("Sign Up");
		btnSignup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Signup signup = new Signup();
				signup.setVisible(true);
				
			}
		});
		btnSignup.setForeground(new Color(255, 255, 255));
		btnSignup.setFont(new Font("Ubuntu", Font.BOLD, 22));
		btnSignup.setFocusable(false);
		btnSignup.setFocusTraversalKeysEnabled(false);
		btnSignup.setFocusPainted(false);
		btnSignup.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		btnSignup.setBorderPainted(false);
		btnSignup.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSignup.setBackground(new Color(255, 0, 51));
		btnSignup.setBounds(76, 434, 237, 46);
		loginPanel.add(btnSignup);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		passwordField.setBackground(new Color(102, 102, 255));
		passwordField.setBounds(90, 289, 237, 31);
		loginPanel.add(passwordField);
		
		exitbtn = new JButton("");
		exitbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane_1.setSelectedIndex(0);
			}
		});
		exitbtn.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Exit_26px_2.png"));
		exitbtn.setFocusable(false);
		exitbtn.setFocusTraversalKeysEnabled(false);
		exitbtn.setFocusPainted(false);
		exitbtn.setBorderPainted(false);
		exitbtn.setBackground(new Color(102, 102, 255));
		exitbtn.setBounds(312, 11, 44, 46);
		loginPanel.add(exitbtn);
		
		StudentLoginPanel = new JPanel();
		StudentLoginPanel.setLayout(null);
		StudentLoginPanel.setBackground(new Color(102, 102, 255));
		tabbedPane_1.addTab("New tab", null, StudentLoginPanel, null);
		
		loginlabel_1 = new JLabel("Login Page\r\n");
		loginlabel_1.setForeground(Color.WHITE);
		loginlabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		loginlabel_1.setBackground(Color.WHITE);
		loginlabel_1.setBounds(104, 21, 198, 55);
		StudentLoginPanel.add(loginlabel_1);
		
		narrationlabel_1 = new JLabel("Welcome, Login To Your Account");
		narrationlabel_1.setForeground(Color.WHITE);
		narrationlabel_1.setFont(new Font("Ubuntu", Font.ITALIC, 19));
		narrationlabel_1.setBounds(48, 71, 294, 31);
		StudentLoginPanel.add(narrationlabel_1);
		
		StudentPasswordLabel = new JLabel("Password\r\n");
		StudentPasswordLabel.setForeground(Color.WHITE);
		StudentPasswordLabel.setFont(new Font("Ubuntu", Font.BOLD, 19));
		StudentPasswordLabel.setBounds(90, 258, 121, 31);
		StudentLoginPanel.add(StudentPasswordLabel);
		
		StudentRollNoLabel = new JLabel("Roll Number");
		StudentRollNoLabel.setForeground(Color.WHITE);
		StudentRollNoLabel.setFont(new Font("Ubuntu", Font.BOLD, 19));
		StudentRollNoLabel.setBounds(90, 148, 121, 31);
		StudentLoginPanel.add(StudentRollNoLabel);
		
		StudentRollNo_txt = new JTextField();
		StudentRollNo_txt.setFont(new Font("Ubuntu", Font.BOLD, 16));
		StudentRollNo_txt.setColumns(10);
		StudentRollNo_txt.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		StudentRollNo_txt.setBackground(new Color(102, 102, 255));
		StudentRollNo_txt.setBounds(90, 185, 237, 31);
		StudentLoginPanel.add(StudentRollNo_txt);
		
		passwordsymbollabel_1 = new JLabel("");
		passwordsymbollabel_1.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\icons8_Secure_50px.png"));
		passwordsymbollabel_1.setBounds(31, 274, 49, 46);
		StudentLoginPanel.add(passwordsymbollabel_1);
		
		RollNumberSymbol = new JLabel("");
		RollNumberSymbol.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\AddNewBookIcons\\icons8_Contact_26px.png"));
		RollNumberSymbol.setBounds(45, 165, 35, 38);
		StudentLoginPanel.add(RollNumberSymbol);
		
		StudentLoginBtn = new JButton("Login");
		StudentLoginBtn.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				StudentPanelFunctions studentPanelFunctions  = new StudentPanelFunctions();
				if(studentPanelFunctions.Studentvalidation(StudentRollNo_txt.getText(),StudentPassword_Txt.getText())==true) {
					studentPanelFunctions.Studentlogin(StudentRollNo_txt.getText());
					studentPanelFunctions.Studentlogindata(StudentRollNo_txt.getText(), StudentPassword_Txt.getText());
				}
				
			}
		});
		StudentLoginBtn.setForeground(new Color(255, 255, 255));
		StudentLoginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		StudentLoginBtn.setFont(new Font("Ubuntu", Font.BOLD, 22));
		StudentLoginBtn.setFocusable(false);
		StudentLoginBtn.setFocusTraversalKeysEnabled(false);
		StudentLoginBtn.setFocusPainted(false);
		StudentLoginBtn.setBorderPainted(false);
		StudentLoginBtn.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
		StudentLoginBtn.setBackground(new Color(51, 51, 255));
		StudentLoginBtn.setAlignmentX(1.0f);
		StudentLoginBtn.setBounds(78, 405, 237, 46);
		StudentLoginPanel.add(StudentLoginBtn);
		
		StudentPassword_Txt = new JPasswordField();
		StudentPassword_Txt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		StudentPassword_Txt.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		StudentPassword_Txt.setBackground(new Color(102, 102, 255));
		StudentPassword_Txt.setBounds(90, 289, 237, 46);
		StudentLoginPanel.add(StudentPassword_Txt);
		
		exitbtn_1 = new JButton("");
		exitbtn_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitbtn_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane_1.setSelectedIndex(0);
			}
		});
		exitbtn_1.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Exit_26px_2.png"));
		exitbtn_1.setFocusable(false);
		exitbtn_1.setFocusTraversalKeysEnabled(false);
		exitbtn_1.setFocusPainted(false);
		exitbtn_1.setBorderPainted(false);
		exitbtn_1.setBackground(new Color(102, 102, 255));
		exitbtn_1.setBounds(312, 11, 44, 46);
		StudentLoginPanel.add(exitbtn_1);
	}
}
