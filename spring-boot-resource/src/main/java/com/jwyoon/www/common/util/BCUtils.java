package com.jwyoon.www.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class BCUtils {
    
	public static String decodeString(String encodedString) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		AES256Cipher.getInstance();
		String jsonObjectStr = AES256Cipher.AES_Decode(encodedString.replace("\n", "").trim());			
		return jsonObjectStr;
	}
	
	public static String snake2camel(String snake) {
		
		String camel = "";
		if(snake.contains("_")) {
			String[] strs = snake.split("_");
			
			for(int i=1; i<strs.length; i++) {
				camel += Character.toUpperCase(strs[i].charAt(0)) + strs[i].substring(1);
			}
			camel = strs[0] + camel;
			
		}
		return camel;
	}
	
	public static String nowTime() {
		
		Date today = new Date();
	    SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
		
		return time.format(today) + " :: ";
	}
	
}
