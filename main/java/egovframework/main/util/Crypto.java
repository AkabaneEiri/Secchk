package egovframework.main.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component("crypto")
public class Crypto {

	public String Sha256(String data) throws NoSuchAlgorithmException{
		
		String encryptData = "";		
		
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		
		sha.update(data.getBytes());
		
		byte[] digest = sha.digest();
		
		for(int i=0; i<digest.length; i++)
		{
			encryptData += Integer.toHexString(digest[i] & 0xFF).toUpperCase();			
		}
		
		return encryptData;
	}

}
