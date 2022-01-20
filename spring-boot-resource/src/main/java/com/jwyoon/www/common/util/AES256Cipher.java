package com.jwyoon.www.common.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AES256Cipher implements Serializable{

	private static final long serialVersionUID = 1L;

	private static volatile AES256Cipher INSTANCE;
	private static Logger logger = LoggerFactory.getLogger(AES256Cipher.class);
	private final static String secretKey = "12345678901234567890123456789012"; // 32bit
	private static String IV = ""; // 16bit

	public static AES256Cipher getInstance() {
		if (INSTANCE == null) {
			synchronized (AES256Cipher.class) {
				if (INSTANCE == null)
					INSTANCE = new AES256Cipher();
			}
		}
		return INSTANCE;
	}

	private AES256Cipher() {
		IV = secretKey.substring(0, 16);
	}

	public static String AES_Encode(String pwd) throws 
	NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
	InvalidAlgorithmParameterException, IllegalBlockSizeException, 
	BadPaddingException, UnsupportedEncodingException {

		byte[] keyData = secretKey.getBytes();
		
		SecretKey secureKey = new SecretKeySpec(keyData, "AES");
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes()));

		byte[] encrypted = c.doFinal(pwd.getBytes("UTF-8"));
		String enStr = new String(Base64.getEncoder().encode(encrypted));


		return enStr;

	}

	public static String AES_Decode(String pwd) throws 
	NoSuchAlgorithmException, NoSuchPaddingException, 
	InvalidKeyException, InvalidAlgorithmParameterException, 
	UnsupportedEncodingException, IllegalBlockSizeException, 
	BadPaddingException {

		byte[] keyData = secretKey.getBytes();
		
		SecretKey secureKey = new SecretKeySpec(keyData, "AES");
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes("UTF-8")));

		byte[] byteStr = Base64.getDecoder().decode(pwd.getBytes());


		return new String(c.doFinal(byteStr), "UTF-8");
	}
}
