package frame;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import function.App;
import function.DBconnection;
import function.PasswordEncodingDecoding;
import function.employee;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import javax.swing.DebugGraphics;
import java.awt.Component;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Signup extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 134203147940197996L;
	private JPanel contentPane;
	
	
	private JPanel AboutPanel;
	private JPanel signupPanel;
	
	private JLabel programmer;
	private JLabel titlelabel;
	private JLabel titlelabel_1;
	private JLabel designlabel;
	private JLabel Signuplabel;
	private JLabel narrationlabel;
	private JLabel username;
	private JLabel userlogolabel;
	private JLabel password;
	private JLabel email;
	private JLabel contact;
	private JLabel passwordlogolabel;
	private JLabel emaillogolabel;
	private JLabel contactlogolabel;
	
	private JButton signup ;
	private JButton btnLogin;
	private JButton btnexit;
	
	protected JTextField txt_user;
	protected JTextField txt_email;
	protected JTextField txt_contact;
	private JPasswordField passwordField;
	
	PasswordEncodingDecoding psDecoding = new PasswordEncodingDecoding();
	App app = new App();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//method to connect database;
	@SuppressWarnings("deprecation")
	public void insertsignupdata() {
		String usernameString = txt_user.getText();
	    String passwordString =  psDecoding.encrypt(passwordField.getText());
		String emailString = txt_email.getText();
		int contactString = Integer.parseInt(txt_contact.getText());
		
		try {
			Connection connection = DBconnection.getConnection();
			String queryString="Insert into signup(name,password,email,mobilenumber) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			
			preparedStatement.setString(1, usernameString);
			preparedStatement.setString(2,passwordString);
			preparedStatement.setString(3, emailString);
			preparedStatement.setInt(4, contactString);
			
			int UpdatedRowCount = preparedStatement.executeUpdate();
			
			if(UpdatedRowCount > 0) {
				JOptionPane.showMessageDialog(this, "Record Insertion Successful");
				LoginPage loginPage = new LoginPage();
				loginPage.setVisible(true);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "Record Insertion Failure");
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	
	// validation of the signup 
	@SuppressWarnings("unlikely-arg-type")
	public boolean validation() {
		String usernameString = txt_user.getText();
		@SuppressWarnings({ "deprecation", "unused" })
		String passwordString = passwordField.getText();
		String emailString = txt_email.getText();
		String contactString = txt_contact.getText();
		
		if(usernameString.equals("")) {
			JOptionPane.showConfirmDialog(this,"Please enter username");
			return false;
		}
		if(passwordField.equals("")) {
			JOptionPane.showConfirmDialog(this,"Please enter Password");
			return false;
		}
		if(emailString.equals("") || !emailString.matches("(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2,3})")) {
			JOptionPane.showConfirmDialog(this,"Please enter a valid email");
			return false;
		}
		if(contactString.equals("")) {
			JOptionPane.showConfirmDialog(this,"Please enter contact");
			return false;
		}
		return true;
		
	}
	
	
	// to check duplicate users
	public boolean checkduplicate() {
		String emaString = txt_email.getText();
		
		boolean isexists= false;
		
		try {
			
			Connection connection = DBconnection.getConnection();
			
			PreparedStatement pst = connection.prepareStatement("select * from signup where email = ?");
			pst.setString(1, emaString);
			
			ResultSet rSet= pst.executeQuery();
			
			if(rSet.next()) {
				isexists = true;
			}
			else {
				isexists = false;
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isexists;
	}
	/**
	 * Create the frame.
	 */
	public Signup() {
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
		
		programmer = new JLabel("Devi Prasad Mishra\r\n");
		programmer.setForeground(new Color(255, 0, 0));
		programmer.setFont(new Font("Ubuntu", Font.BOLD, 19));
		programmer.setBounds(10, 11, 177, 27);
		AboutPanel.add(programmer);
		
		titlelabel = new JLabel("Welcome To\r\n");
		titlelabel.setForeground(new Color(255, 51, 102));
		titlelabel.setFont(new Font("Ubuntu", Font.BOLD, 29));
		titlelabel.setBounds(152, 59, 200, 51);
		AboutPanel.add(titlelabel);
		
		titlelabel_1 = new JLabel("Advance Library\r\n");
		titlelabel_1.setForeground(new Color(51, 255, 255));
		titlelabel_1.setFont(new Font("Ubuntu", Font.BOLD, 29));
		titlelabel_1.setBounds(289, 116, 227, 37);
		AboutPanel.add(titlelabel_1);
		
		designlabel = new JLabel("");
		designlabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\signup-library-icon.png"));
		designlabel.setBounds(0, 59, 758, 586);
		AboutPanel.add(designlabel);
		
		signupPanel = new JPanel();
		signupPanel.setBackground(new Color(102, 102, 255));
		signupPanel.setBounds(751, 0, 380, 645);
		contentPane.add(signupPanel);
		signupPanel.setLayout(null);
		
		Signuplabel = new JLabel("Sign UP Page\r\n");
		Signuplabel.setForeground(new Color(255, 255, 255));
		Signuplabel.setBackground(new Color(255, 255, 255));
		Signuplabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		Signuplabel.setBounds(90, 26, 198, 55);
		signupPanel.add(Signuplabel);
		
		narrationlabel = new JLabel("Create a new account Here");
		narrationlabel.setForeground(new Color(255, 255, 255));
		narrationlabel.setFont(new Font("Ubuntu", Font.ITALIC, 19));
		narrationlabel.setBounds(59, 62, 268, 31);
		signupPanel.add(narrationlabel);
		
		username = new JLabel("Usename\r\n");
		username.setForeground(new Color(255, 255, 255));
		username.setFont(new Font("Ubuntu", Font.BOLD, 19));
		username.setBounds(90, 141, 121, 31);
		signupPanel.add(username);
		
		txt_user = new JTextField();
		txt_user.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_user.setBackground(new Color(102, 102, 255));
		txt_user.setFont(new Font("Ubuntu", Font.BOLD, 16));
		txt_user.setBounds(90, 164, 237, 31);
		signupPanel.add(txt_user);
		txt_user.setColumns(10);
		
	    userlogolabel = new JLabel("");
		userlogolabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\icons8_Account_50px.png"));
		userlogolabel.setBounds(31, 149, 49, 46);
		signupPanel.add(userlogolabel);
		
		password = new JLabel("Password\r\n");
		password.setForeground(Color.WHITE);
		password.setFont(new Font("Ubuntu", Font.BOLD, 19));
		password.setBounds(90, 223, 121, 31);
		signupPanel.add(password);
		
		email = new JLabel("Email");
		email.setForeground(Color.WHITE);
		email.setFont(new Font("Ubuntu", Font.BOLD, 19));
		email.setBounds(90, 305, 121, 31);
		signupPanel.add(email);
		
		contact = new JLabel("Contact\r\n");
		contact.setForeground(Color.WHITE);
		contact.setFont(new Font("Ubuntu", Font.BOLD, 19));
		contact.setBounds(90, 389, 121, 31);
		signupPanel.add(contact);
		
		txt_email = new JTextField();
		txt_email.setFont(new Font("Ubuntu", Font.BOLD, 16));
		txt_email.setColumns(10);
		txt_email.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_email.setBackground(new Color(102, 102, 255));
		txt_email.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(checkduplicate() == true) {
					JOptionPane.showMessageDialog(txt_email,"Email already Registered");
				}
				
			}
		});
		txt_email.setBounds(90, 329, 237, 31);
		signupPanel.add(txt_email);
		
		txt_contact = new JTextField();
		txt_contact.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter !='\b')) {
					e.consume();
				}
				 
	                }
		});
		txt_contact.setFont(new Font("Ubuntu", Font.BOLD, 16));
		txt_contact.setColumns(10);
		txt_contact.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_contact.setBackground(new Color(102, 102, 255));
		txt_contact.setBounds(90, 420, 237, 27);
		signupPanel.add(txt_contact);
		
		passwordlogolabel = new JLabel("");
		passwordlogolabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\icons8_Secure_50px.png"));
		passwordlogolabel.setBounds(31, 231, 49, 46);
		signupPanel.add(passwordlogolabel);
		
		emaillogolabel = new JLabel("");
		emaillogolabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\icons8_Secured_Letter_50px.png"));
		emaillogolabel.setBounds(31, 314, 49, 46);
		signupPanel.add(emaillogolabel);
		
		contactlogolabel = new JLabel("");
		contactlogolabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\icons8_Google_Mobile_50px.png"));
		contactlogolabel.setBounds(31, 401, 49, 46);
		signupPanel.add(contactlogolabel);
		
		signup = new JButton("Sign Up");
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if((validation() == true  && checkduplicate()==false) && employee.validEmployee(txt_email.getText())==true ) {
					String emailLibrarian= txt_email.getText();
				    String PasswordEmail=
					 		 "Hi "
							+txt_user.getText()+","
					 		+ "\r\n"
					 		+ "Your Password is : "
					 		+ passwordField.getText();
						App.sendEmail(PasswordEmail,App.getSubject(),emailLibrarian, App.getFrom());
					insertsignupdata();
			}
				else { 
					JOptionPane.showMessageDialog(signup, "Incorrect Information.");
				}
			}
		});
		signup.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		signup.setBorderPainted(false);
		signup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signup.setBackground(new Color(255, 0, 51));
		signup.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		signup.setFocusTraversalKeysEnabled(false);
		signup.setFocusable(false);
		signup.setFocusPainted(false);
		signup.setFont(new Font("Ubuntu", Font.BOLD, 22));
		signup.setBounds(76, 477, 237, 46);
		signupPanel.add(signup);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginPage().setVisible(true);
			}
		});
		btnLogin.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBorderPainted(false);
		btnLogin.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
		btnLogin.setBackground(new Color(51, 51, 255));
		btnLogin.setFocusTraversalKeysEnabled(false);
		btnLogin.setFocusPainted(false);
		btnLogin.setFocusable(false);
		btnLogin.setFont(new Font("Ubuntu", Font.BOLD, 22));
		btnLogin.setBounds(76, 536, 237, 46);
		signupPanel.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		passwordField.setBackground(new Color(102, 102, 255));
		passwordField.setBounds(90, 248, 237, 31);
		signupPanel.add(passwordField);
		
		btnexit = new JButton("");
		btnexit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		btnexit.setFocusPainted(false);
		btnexit.setFocusTraversalKeysEnabled(false);
		btnexit.setFocusable(false);
		btnexit.setBorderPainted(false);
		btnexit.setBackground(new Color(102, 102, 255));
		btnexit.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Exit_26px_2.png"));
		btnexit.setBounds(312, 11, 44, 46);
		signupPanel.add(btnexit);
	}
}
