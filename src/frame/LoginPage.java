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
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import javax.swing.DebugGraphics;
import java.awt.Component;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 134203147940197996L;
	
	private JPanel contentPane;
	private JPanel AboutPanel;
	private JPanel loginPanel;
	
	private JLabel loginlabel;
	private JLabel ProgrammerName; 
	private JLabel titlelabel;
	private JLabel titlelabel_1;
	private JLabel designlabel;
	private JLabel narrationlabel;
	private JLabel passwordlabel;
	private JLabel emaillabel;
	private JLabel passwordsymbollabel;
	private JLabel emailsymbollabel;
	
	protected JTextField txt_email;
	protected JPasswordField passwordField;

	private JButton btnLogin ;
	private JButton exitbtn;
	private JButton btnSignup;

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
		String passwordString = passwordField.getText();
		String emailString = txt_email.getText();
	
		
		try {
			Connection connection = DBconnection.getConnection();
			String queryString=("select * from signup where email= ? and password =?");
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			
			preparedStatement.setString(1, emailString);
			preparedStatement.setString(2, passwordString);
			
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				JOptionPane.showMessageDialog(this, "login success");
				HomePage home = new HomePage();
				home.setVisible(true);
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "Invalid email or password");
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
		String passwordString = passwordField.getText();
		String emailString = txt_email.getText();
	
		if(passwordField.equals("")) {
			JOptionPane.showConfirmDialog(this,"Please enter Password");
			return false;
		}
		if(emailString.equals("") || !emailString.matches("(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2,3})")) {
			JOptionPane.showConfirmDialog(this,"Please enter a valid email");
			return false;
		}
		
		return true;
		
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
		
		ProgrammerName = new JLabel("Devi Prasad Mishra\r\n");
		ProgrammerName.setForeground(new Color(255, 0, 0));
		ProgrammerName.setFont(new Font("Ubuntu", Font.BOLD, 19));
		ProgrammerName.setBounds(10, 11, 177, 27);
		AboutPanel.add(ProgrammerName);
		
		titlelabel = new JLabel("Welcome To\r\n");
		titlelabel.setForeground(new Color(255, 51, 102));
		titlelabel.setFont(new Font("Ubuntu", Font.BOLD, 29));
		titlelabel.setBounds(152, 59, 200, 51);
		AboutPanel.add(titlelabel);
		
		titlelabel_1 = new JLabel("Advance Library\r\n");
		titlelabel_1.setForeground(new Color(255, 51, 51));
		titlelabel_1.setFont(new Font("Ubuntu", Font.BOLD, 29));
		titlelabel_1.setBounds(336, 90, 227, 37);
		AboutPanel.add(titlelabel_1);
		
		designlabel = new JLabel("");
		designlabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\library-3.png.png"));
		designlabel.setBounds(0, 59, 758, 586);
		AboutPanel.add(designlabel);
		
		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(102, 102, 255));
		loginPanel.setBounds(751, 0, 380, 645);
		contentPane.add(loginPanel);
		loginPanel.setLayout(null);
		
		loginlabel = new JLabel("Login Page\r\n");
		loginlabel.setForeground(new Color(255, 255, 255));
		loginlabel.setBackground(new Color(255, 255, 255));
		loginlabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		loginlabel.setBounds(104, 21, 198, 55);
		loginPanel.add(loginlabel);
		
		narrationlabel = new JLabel("Welcome, Login To Your Account");
		narrationlabel.setForeground(new Color(255, 255, 255));
		narrationlabel.setFont(new Font("Ubuntu", Font.ITALIC, 19));
		narrationlabel.setBounds(48, 71, 294, 31);
		loginPanel.add(narrationlabel);
		
		passwordlabel = new JLabel("Password\r\n");
		passwordlabel.setForeground(Color.WHITE);
		passwordlabel.setFont(new Font("Ubuntu", Font.BOLD, 19));
		passwordlabel.setBounds(90, 258, 121, 31);
		loginPanel.add(passwordlabel);
		
		emaillabel = new JLabel("Email");
		emaillabel.setForeground(Color.WHITE);
		emaillabel.setFont(new Font("Ubuntu", Font.BOLD, 19));
		emaillabel.setBounds(90, 148, 121, 31);
		loginPanel.add(emaillabel);
		
		txt_email = new JTextField();
		txt_email.setFont(new Font("Ubuntu", Font.BOLD, 16));
		txt_email.setColumns(10);
		txt_email.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_email.setBackground(new Color(102, 102, 255));
		txt_email.setBounds(90, 185, 237, 31);
		loginPanel.add(txt_email);
		
		passwordsymbollabel = new JLabel("");
		passwordsymbollabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\icons8_Secure_50px.png"));
		passwordsymbollabel.setBounds(31, 274, 49, 46);
		loginPanel.add(passwordsymbollabel);
		
		emailsymbollabel = new JLabel("");
		emailsymbollabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\icons8_Secured_Letter_50px.png"));
		emailsymbollabel.setBounds(31, 166, 49, 46);
		loginPanel.add(emailsymbollabel);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validation()) {
					logindata();
				}
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
		btnLogin.setBounds(76, 361, 237, 46);
		loginPanel.add(btnLogin);
		
		
		btnSignup = new JButton("Sign Up");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup signup=new Signup();
				signup.setVisible(true);
				
			}
		});
		btnSignup.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		btnSignup.setBorderPainted(false);
		btnSignup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSignup.setBackground(new Color(255, 0, 51));
		btnSignup.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnSignup.setFocusTraversalKeysEnabled(false);
		btnSignup.setFocusable(false);
		btnSignup.setFocusPainted(false);
		btnSignup.setFont(new Font("Ubuntu", Font.BOLD, 22));
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
				System.exit(ABORT);
			}
		});
		exitbtn.setFocusPainted(false);
		exitbtn.setFocusTraversalKeysEnabled(false);
		exitbtn.setFocusable(false);
		exitbtn.setBorderPainted(false);
		exitbtn.setBackground(new Color(102, 102, 255));
		exitbtn.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\adminIcons\\icons8_Exit_26px_2.png"));
		exitbtn.setBounds(312, 11, 44, 46);
		loginPanel.add(exitbtn);
	}
}
