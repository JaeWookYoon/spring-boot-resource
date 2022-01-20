package com.jwyoon.www.controlloer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import com.jwyoon.www.common.util.AES256Cipher;
import com.jwyoon.www.common.util.Constants;
import com.jwyoon.www.model.BankAccount;
import com.jwyoon.www.model.CryptoList;
import com.jwyoon.www.model.UserWallet;
import com.jwyoon.www.model.WalletList;


@RequestMapping(value = "/private", produces = "application/json")
@RestController
public class PrivateInsertController {

	private final static String PATH_URL = "/insert";
	
	
	//	/api/postgre/v1/insert/bidOrAsk
	@PostMapping(value=Constants.BASIC_URL + PATH_URL
			+ "/bidOrAsk", produces = { "application/json; charset=UTF-8" },consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String bidOrAsk(HttpServletRequest request,@RequestBody String encodedString){
		/*
		 * boolean result = false; AES256Cipher.getInstance();
		 * 
		 * String decodedString; JSONObject resultObj = null; try { decodedString =
		 * AES256Cipher.AES_Decode(encodedString.replace("\n", "").trim()); resultObj =
		 * (JSONObject) new JSONParser().parse(decodedString);
		 * 
		 * } catch (InvalidKeyException | NoSuchAlgorithmException |
		 * NoSuchPaddingException | InvalidAlgorithmParameterException |
		 * UnsupportedEncodingException | IllegalBlockSizeException |
		 * BadPaddingException e) { e.printStackTrace(); } catch (ParseException e) { }
		 * String userIdx = (String) resultObj.get("userIdx"); String walletIdx =
		 * (String) resultObj.get("walletIdx"); String type = (String)
		 * resultObj.get("type"); //"bid" or "ask" double amount = (double)
		 * resultObj.get("amount"); double price = (double) resultObj.get("price");
		 * WalletList walletList = walletListRepository.findByIdx(walletIdx); String
		 * cryptoIdx = walletList.getCryptoIdx();
		 * 
		 * String resultMessage = "0000"; // 0000:�꽦?�� , 0001 ~ 0006:�떎�뙣 boolean
		 * existsUserWallet = userWalletRepository.existsByUserIdxAndWalletIdx(userIdx,
		 * walletIdx);
		 * 
		 * List<UserWallet> userWallets = null; BankAccount bankAccount = null;
		 * 
		 * if (existsUserWallet) { userWallets =
		 * userWalletRepository.findByUserIdxAndWalletIdx(userIdx, walletIdx); } else {
		 * if (type.equals("bid")) { bankAccount =
		 * bankAccountRepository.findByUserIdx(userIdx);
		 * 
		 * if(bankAccount.getAsset() < multiplyDouble(amount, price)) { resultMessage =
		 * "0001"; } else { UserWallet insertList = new UserWallet();
		 * insertList.setIdx(UUID.randomUUID().toString());
		 * insertList.setUserIdx(userIdx); insertList.setWalletIdx(walletIdx);
		 * insertList.setAmount(0); insertList.setAveragePriceDollar(price > 0?price:0);
		 * //insert userWalletRepository.save(insertList); //吏�媛� �깮�꽦 ��??���?
		 * existsUserWallet = userWalletRepository.existsByUserIdxAndWalletIdx(userIdx,
		 * walletIdx);
		 * 
		 * if(existsUserWallet) { resultMessage = "0000"; } else { resultMessage =
		 * "0005"; } } } else { resultMessage = "0002"; } }
		 * 
		 * StringBuffer requestURL = request.getRequestURL(); JSONObject json = new
		 * JSONObject(); json.put("resultMessage", resultMessage);
		 * 
		 * try { resultMessage =
		 * AES256Cipher.getInstance().AES_Encode(json.toJSONString().trim()); } catch
		 * (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException |
		 * InvalidAlgorithmParameterException | IllegalBlockSizeException |
		 * BadPaddingException | UnsupportedEncodingException e) { }
		 */
		
		return null;
	}
	
	public double addDouble(double num1, double num2) {
		double result = 0;
		
		BigDecimal number1 = BigDecimal.valueOf(num1);
		BigDecimal number2 = BigDecimal.valueOf(num2);
		BigDecimal resultNum = number1.add(number2);
		result = resultNum.doubleValue();
		
		return result;
	}
	
	public double minusDouble(double num1, double num2) {
		double result = 0;
		
		BigDecimal number1 = BigDecimal.valueOf(num1);
		BigDecimal number2 = BigDecimal.valueOf(num2);
		BigDecimal resultNum = number1.subtract(number2);
		result = resultNum.doubleValue();
		
		return result;
	}
	
	public double multiplyDouble(double num1, double num2) {
		double result = 0;
		
		BigDecimal number1 = BigDecimal.valueOf(num1);
		BigDecimal number2 = BigDecimal.valueOf(num2);
		BigDecimal resultNum = number1.multiply(number2);
		result = resultNum.doubleValue();
		
		return result;
	}
	
	public double divideDouble(double num1, double num2) {
		double result = 0;
		
		BigDecimal number1 = BigDecimal.valueOf(num1);
		BigDecimal number2 = BigDecimal.valueOf(num2);
		BigDecimal resultNum = number1.divide(number2, 2, BigDecimal.ROUND_UP);
		result = resultNum.doubleValue();
		return result;
	}
	
	
	@PostMapping(value = Constants.BASIC_URL + PATH_URL
			+ "/makeWallet", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String makeWallet(@RequestBody String encodedString) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException,
			BadPaddingException, ParseException{
		
		
		
		return null;
	}

	public Map<String, String> createEthOrErc20Wallet(final String password , final String uuid, String code) {
		Map<String, String> result = new HashMap<String, String>();
		code = code.replace("/KRW", "");
		
		try {
			File path = new File("/storage/seekers/" + uuid + "/bittop/wallets");
			if (!path.exists()) {
				path.mkdirs();
			}
			File file = new File(path + "/" + code + ".json");
			if (!file.exists()) {
				String fileName = WalletUtils.generateLightNewWalletFile(password, new File(String.valueOf(path))); // 吏�媛묒�?�꽦
				String tempPath = path + "/" + fileName;
				
				Credentials credentials = WalletUtils.loadCredentials(password, tempPath);
	
				result.put("address", credentials.getAddress());
				
				file = new File(tempPath);
				file.renameTo( new File(path + "/" + code + ".json"));
				file = new File(path + "/" + code +".json");
			} else {
				Credentials credentials = WalletUtils.loadCredentials(password, file.toString());
				
				result.put("address", credentials.getAddress());
			}
			
			BufferedReader br = null;
			StringBuffer sb = new StringBuffer();
			try {
				br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			result.put("tokenInfo", sb.toString());
			result.put("tokenPath", file.toString());

			return result;
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException | IOException
				| CipherException e) {
			e.printStackTrace();
			return null;
		}
	}
}
