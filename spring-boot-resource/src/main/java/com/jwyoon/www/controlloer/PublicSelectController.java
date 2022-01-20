package com.jwyoon.www.controlloer;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwyoon.www.common.util.AES256Cipher;
import com.jwyoon.www.common.util.BCUtils;
import com.jwyoon.www.common.util.Constants;
import com.jwyoon.www.model.MessageConsumer;
import com.jwyoon.www.model.MessageProducer;
import com.jwyoon.www.repository.UserListRepository;

@RequestMapping(value = "/public", produces = "application/json")
@RestController
public class PublicSelectController {

	private final static String PATH_URL = "/select";

	/*
	 * @Autowired private MessageProducer producer ;
	 * 
	 * @Autowired private MessageConsumer consumer;
	 */
	
	@Resource(name = "userListRepository")
	private UserListRepository userListRepository;	
	
	@GetMapping(value=Constants.BASIC_URL + "/test")
	public String test(String test) {
		//producer.sendMessage("myTopic", "HELLO WORLD!");
		return userListRepository.findAll().get(0).getUserId();		
	}
	@GetMapping(value=Constants.BASIC_URL + "/test1")
	public String test1(String test) {
		//consumer.getLatch();
		//consumer.getLatch2();
		return userListRepository.findAll().get(0).getUserId();		
	}
	@PostMapping(value = Constants.BASIC_URL + PATH_URL
			+ "/validation/{type}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String findByUserEmail(@PathVariable(value = "type") String type, @RequestBody String encodedString)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {

		boolean result = true;

		result = validateUserInfo(type, encodedString);

		JSONObject resultObj = new JSONObject();
		resultObj.put("result", result);

		return AES256Cipher.AES_Encode(resultObj.toString());

	} 


	public boolean validateUserInfo(String key, String encodedString) {

		boolean result = false;

		String valueStr = "";

		key = BCUtils.snake2camel(key);

		try {
			String jsonObjectStr = BCUtils.decodeString(encodedString);
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonObjectStr);
			valueStr = ((String) jsonObject.get(key)).trim();

		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | UnsupportedEncodingException | IllegalBlockSizeException
				| BadPaddingException | ParseException e1) {
			e1.printStackTrace();
		}

		return result;
	}

}
