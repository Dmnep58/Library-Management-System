package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;

import frame.StudentPanel;

public class StudentPanelFunctions {
	
	PasswordEncodingDecoding psDecoding = new PasswordEncodingDecoding();
	
	
	public boolean Studentvalidation(String user,String password){
		boolean isvalid = true;
		
		if(user.equals("") || password.equals("")) {
			isvalid=false;
		}
		
		return isvalid;
	}
	
	
	//password updation.	
	public void updatePassword( int RollnumberString,String passwordString) {
		try {
			Connection connection = DBconnection.getConnection();
			String query= "update manage_Students set password = ? where student_id= ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1 , psDecoding.encrypt(passwordString));
			preparedStatement.setLong(2 , RollnumberString);
			
			int rowcount = preparedStatement.executeUpdate();
			 if(rowcount >0) {
				 JOptionPane.showMessageDialog(null, "Password Updated to: "+passwordString);
			 }
		}
			catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
		//method to connect database;
		public void Studentlogindata(String userString ,String passwordString){	
		String pString = psDecoding.encrypt(passwordString);
		try {
				Connection connection = DBconnection.getConnection();
				String queryString=("select * from manage_students where student_id =? and password =?");
				PreparedStatement preparedStatement = connection.prepareStatement(queryString);
				
				preparedStatement.setString(1,userString);
				preparedStatement.setString(2,pString);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()) {
					StudentPanel st = new StudentPanel();
					st.setVisible(true);	
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid username or password");
				}

			}
			catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		
		
		
		// to generate the password
		private static final String[] charCategories = new String[] {
	            "abcdefghijklmnopqrstuvwxyz",
	            "0123456789"
	    };

	    public static String  generatePassword(int length) {
	        StringBuilder password = new StringBuilder(length);
	        Random random = new Random(System.nanoTime());

	        for (int i = 0; i < length; i++) {
	            String charCategory = charCategories[random.nextInt(charCategories.length)];
	            int position = random.nextInt(charCategory.length());
	            password.append(charCategory.charAt(position));
	        }
	       
	        return new String(password);
	    }
	    
	    
	    //check the values in the string
	    public boolean checkstring(String s , String n) {
	    	boolean issame = false;
		  		if(s.equals(n)) {
		  			issame = true;
		  		}
		  		
		  		return issame;
		  		
	    }
	    
	    
	    
	    // extrat all the data of the student from the data base
	    
	    public String studentData(String s) {
	    	String str = null;
	    	String id = null,name = null,branch = null,course = null;
	    	try {
				Connection connection = DBconnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from manage_students where student_id= '"+Integer.valueOf(s)+"'");
				
				
				while(resultSet.next()) {
					
					id = resultSet.getString("student_id");
					name= resultSet.getString("name");
					course=resultSet.getString("course");
					branch=resultSet.getString("Branch");
				}
					
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e.toString());
			}
	    	str ="Student_id&emsp;:"+id+"<br><hr>Name&emsp;&emsp;&emsp;&ensp:"+name+
					"<br><hr>Course&emsp;&emsp;&emsp;:"+course+"<br><hr>Branch&emsp;&emsp;&emsp;:"+branch+"<hr>";	    	
	    	return str;
	    }
	    
	    
	 // login record in login table
		public void Studentlogin(String userString) {
			int roll = Integer.valueOf(userString);
			try {
				Connection connection = DBconnection.getConnection();
				String queryString="Insert into loginStudent(Student_id,Date) values(?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(queryString);
				
				preparedStatement.setLong(1,roll);
				preparedStatement.setTimestamp(2,new java.sql.Timestamp(new java.util.Date().getTime()));
				
				 preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		// calculate the days of the issued books
		public int TwoDaysLeft(int integer) {
			int count =0 ;
			int roll = Integer.valueOf(integer);
			long day=0;
			try {
				Connection connection = DBconnection.getConnection();
				String queryString="SELECT issue_date,due_date from issue_book where student_id = '"+roll+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(queryString);	
				 ResultSet resultSet = preparedStatement.executeQuery();
				 while(resultSet.next()) {
					 String string = resultSet.getString("issue_date");
					 String string1 = resultSet.getString("due_date");
					 day = countdays(string,string1);
					 if(day<=2) {
						 count++;
					 }
				 }
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return count;
		}
		
		// count days
		public long countdays(CharSequence d1,CharSequence d2) {
			long daysDiff1 = 0;
			try {
			    LocalDate dateBefore = LocalDate.parse(d1);
			    LocalDate dateAfter = LocalDate.parse(d2);

			    
			    // Approach 2
			    daysDiff1 = dateBefore.until(dateAfter, ChronoUnit.DAYS);
//			    System.out.println("The number of days between dates: " + daysDiff1);
			    
			}catch(Exception e){
			    e.printStackTrace();
			}
			return  daysDiff1;
		    }
		
		
		
		
		
}
