package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.ComponentOrientation;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import function.DBconnection;
import function.PasswordEncodingDecoding;
import function.StudentPanelFunctions;
import function.employee;
import rojeru_san.complementos.RSTableMetro;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReervationPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2791631781450576408L;
	private JPanel contentPane;
	private JPasswordField confirmPassword;
	private JPasswordField password;
	JLabel user;
	RSTableMetro table_1 ;
	DefaultTableModel model;
	
	LoginPage loginPage = new LoginPage();
	static String nameString  =LoginPage.userTeacherFiled.getText();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReervationPanel frame = new ReervationPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	PasswordEncodingDecoding psDecoding = new PasswordEncodingDecoding();
	
		
		// to set the records in tables
					public void setReservationDetailsStudentPanel() {
						try {
							Connection connection = DBconnection.getConnection();
							Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery("select * from reservation");
							
							while(resultSet.next()) {
								String snString = resultSet.getString("sn");
								String bookidString = resultSet.getString("student_id");
								String studentidString = resultSet.getString("book_id");
								
								
								Object[] objects= {snString, bookidString, studentidString};
								model= (DefaultTableModel)table_1.getModel();
								model.addRow(objects);
								
							}
							
							
						}catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				

	/**
	 * Create the frame.
	 */
	public ReervationPanel() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 927, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel StudentIssuedPanel = new JPanel();
		StudentIssuedPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		StudentIssuedPanel.setLayout(null);
		StudentIssuedPanel.setBackground(new Color(153, 255, 255));
		StudentIssuedPanel.setBounds(0, 0, 928, 526);
		contentPane.add(StudentIssuedPanel);
		
		JScrollPane ReserveScrollPane = new JScrollPane();
		ReserveScrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		ReserveScrollPane.setBounds(10, 123, 562, 295);
		StudentIssuedPanel.add(ReserveScrollPane);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(623, 71, 295, 423);
		StudentIssuedPanel.add(panel_4);
		
		confirmPassword = new JPasswordField();
		confirmPassword.setBounds(10, 215, 262, 33);
		panel_4.add(confirmPassword);
		
		JLabel lblNewLabel_2 = new JLabel("Reset Password");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_2.setBounds(23, 11, 249, 54);
		panel_4.add(lblNewLabel_2);
		
		JLabel ResetPasswordLable = new JLabel("Enter the new Password");
		ResetPasswordLable.setForeground(Color.BLACK);
		ResetPasswordLable.setFont(new Font("Verdana", Font.BOLD, 11));
		ResetPasswordLable.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		ResetPasswordLable.setBackground(Color.BLACK);
		ResetPasswordLable.setBounds(10, 76, 243, 21);
		panel_4.add(ResetPasswordLable);
		
		JLabel ConfirmPasswordLabel = new JLabel("Confirm your Password");
		ConfirmPasswordLabel.setFont(new Font("Verdana", Font.BOLD, 11));
		ConfirmPasswordLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		ConfirmPasswordLabel.setBackground(Color.BLACK);
		ConfirmPasswordLabel.setBounds(10, 172, 242, 21);
		panel_4.add(ConfirmPasswordLabel);
		
		JButton PaswwordresetBtn = new JButton("Submit");
		PaswwordresetBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StudentPanelFunctions studentPanelFunctions  = new StudentPanelFunctions();
				employee emp = new employee();
				if(studentPanelFunctions.Studentvalidation(password.getText(), confirmPassword.getText())==true) {
					emp.updatePassword(nameString,password.getText());
				}
			}
		});
		PaswwordresetBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		PaswwordresetBtn.setForeground(Color.WHITE);
		PaswwordresetBtn.setFont(new Font("Ubuntu", Font.BOLD, 16));
		PaswwordresetBtn.setFocusable(false);
		PaswwordresetBtn.setFocusPainted(false);
		PaswwordresetBtn.setBorderPainted(false);
		PaswwordresetBtn.setBackground(Color.BLUE);
		PaswwordresetBtn.setBounds(71, 292, 139, 39);
		panel_4.add(PaswwordresetBtn);
		
		password = new JPasswordField();
		password.setBounds(10, 123, 262, 28);
		panel_4.add(password);
		
		JLabel BackgroundImg = new JLabel("");
		BackgroundImg.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\LIBRARY_MANAGEMENT SYSTEM\\src\\icons\\reset password.png"));
		BackgroundImg.setBounds(0, 0, 295, 423);
		panel_4.add(BackgroundImg);
		
		JLabel lblNewLabel_3 = new JLabel("Applied for issue Book");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_3.setBounds(10, 57, 355, 31);
		StudentIssuedPanel.add(lblNewLabel_3);
		
		JPanel BackPanel = new JPanel();
		BackPanel.setLayout(null);
		BackPanel.setBackground(new Color(153, 255, 255));
		BackPanel.setBounds(0, 0, 165, 52);
		StudentIssuedPanel.add(BackPanel);
		
		JLabel BackLabel = new JLabel(" Back");
		BackLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage homePage = new HomePage();
				homePage.setVisible(true);
				dispose();
			}
		});
		BackLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackLabel.setIcon(new ImageIcon("C:\\Users\\pm429\\eclipse-workspace\\ScreenLoading\\Images\\white-logout-logo.png"));
		BackLabel.setForeground(new Color(0, 0, 51));
		BackLabel.setFont(new Font("Ubuntu", Font.BOLD, 30));
		BackLabel.setBackground(new Color(255, 0, 51));
		BackLabel.setBounds(10, 0, 152, 50);
		BackPanel.add(BackLabel);
		
		table_1 = new RSTableMetro();
		    table_1.setShowGrid(false);
		    table_1.setAutoscrolls(false);
		    ReserveScrollPane.setViewportView(table_1);
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
		    		"sn", "Student_id", "Book_id"
		    	}
		    ));
		    
		    user = new JLabel(nameString);
		    user.setFont(new Font("Ubuntu", Font.PLAIN, 18));
		    user.setBounds(347, 11, 284, 23);
		    StudentIssuedPanel.add(user);
		    table_1.getColumnModel().getColumn(0).setResizable(false);
		    
		    
		    setReservationDetailsStudentPanel();
	}
}
