package function;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class PasswordEncodingDecoding {

	// Encrypt the password
		public String encrypt(String password) {
			
			Encoder encoder = Base64.getEncoder();
			
			String encodinString = encoder.encodeToString(password.getBytes());
			
			return encodinString;
		}
		
		
		//to decrypt the password.
		public String decrypt(String password) {
			Decoder decoder = Base64.getDecoder();
			byte[] decodedDataInBytes = decoder.decode(password.getBytes());
			String decodedData = new String(decodedDataInBytes);
		return decodedData;
		}
			
}
