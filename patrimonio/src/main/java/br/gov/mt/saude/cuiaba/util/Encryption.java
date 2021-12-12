package br.gov.mt.saude.cuiaba.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encryption {
	
	public String encrypt(String toEncrypt){
		
		MessageDigest m;
		String hashtext = null;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(toEncrypt.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			hashtext = bigInt.toString(16);
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			
			return hashtext;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashtext;
	}
	
	
//	public static void main(String[] args) {
//		Encryption encryption = new Encryption();
//		
//		System.out.println(encryption.encrypt("1203"));
//	}
}
