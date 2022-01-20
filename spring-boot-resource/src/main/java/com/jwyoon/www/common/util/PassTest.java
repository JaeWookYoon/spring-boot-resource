package com.jwyoon.www.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.json.simple.parser.ParseException;

public class PassTest {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, ParseException {
		
		String pwd = "dkssud12@Q";
		AES256Cipher.getInstance();
		
		pwd = AES256Cipher.AES_Encode(pwd.replace("\n" , "").trim());
		
		String data = "{\"password\":\""+pwd+"\" , \"userIdx\":\"2410fd8e-bdf7-444d-ad10-840f8819b458\" , \"code\":\"ETH/KRW\"}";
		
		data = AES256Cipher.AES_Encode(data);
		System.out.println("pwd : "+pwd);
		System.out.println("encoded : "+data);
		
		String deData = AES256Cipher.AES_Decode(data.replace("\n" , "").trim());
		System.out.println("decoded : "+deData);
	
		
	}

}
