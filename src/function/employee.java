package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class employee {
	
	PasswordEncodingDecoding psDecoding = new PasswordEncodingDecoding();

	public static boolean validEmployee(String emaString) {
		boolean isvalid = false;
		
try {
			
			Connection connection = DBconnection.getConnection();
			
			PreparedStatement pst = connection.prepareStatement("select * from employee where email = ? and degination=?");
			pst.setString(1, emaString);
			pst.setString(2, "librarian");
			
			ResultSet rSet= pst.executeQuery();
			
			if(rSet.next()) {
				isvalid = true;
			}
			else {
				isvalid = false;
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return isvalid;
	}
	
	//password updation.
		public void updatePassword( String nameString,String passwordString) {
			try {
				Connection connection = DBconnection.getConnection();
				String query= "update signup set password = ? where name= ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1 , psDecoding.encrypt(passwordString));
				preparedStatement.setString(2 , nameString);
				
				int rowcount = preparedStatement.executeUpdate();
				 if(rowcount >0) {
					 JOptionPane.showMessageDialog(null, "Password Updated to: "+passwordString);
				 }
			}
				catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
}
