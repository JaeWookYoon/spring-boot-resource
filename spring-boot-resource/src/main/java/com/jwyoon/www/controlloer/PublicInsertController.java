package com.jwyoon.www.controlloer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import com.jwyoon.www.common.util.Constants;
import com.jwyoon.www.common.util.SHACrypt;
import com.jwyoon.www.model.UserList;
import com.jwyoon.www.repository.OauthClientDetailsRepository;
import com.jwyoon.www.repository.UserAuthRepository;
import com.jwyoon.www.repository.UserListRepository;

@RequestMapping(value = "/public", produces = "application/json")
@RestController
public class PublicInsertController {
	private final static String PATH_URL = "/insert";

	@Resource(name = "userListRepository")
	private UserListRepository userListRepository;

	@Resource(name = "oauthClientDetailsRepository")
	private OauthClientDetailsRepository oauthClientDetailsRepository;

	@Resource(name = "userAuthRepository")
	private UserAuthRepository userAuthRepository;


	@Autowired
	private SHACrypt sha;
	
	@PostMapping(value = Constants.BASIC_URL + PATH_URL
			+ "/signUp",consumes="application/json")
	public String insertUser(@RequestBody UserList user)
			{
		System.out.println(user.getUserId());
		/*System.out.println(userList.getUserId());
		String decodeString = null;		
		ObjectMapper mapper = new ObjectMapper();
		String userIdx = UUID.randomUUID().toString();
		JSONObject json = new JSONObject();
		try {
			UserList user = userList;

			user.setIdx(userIdx);
			user.setDeleted(false);
			user.setBlocked(false);
			user.setNickname(false);
			RecommendCodeGenerator rcg = new RecommendCodeGenerator();
			String rc = null;
			boolean rcCk = false;
			while(true) {
				rc = rcg.codeGenerate();
				rcCk = userListRepository.existsByRecommendCode(rc);
				if(!rcCk)
					break;
			}
			user.setRecommendCode(rc);
			userListRepository.save(user);

			OauthClientDetails ocd = new OauthClientDetails();
			ocd.setClientId(user.getUserId());
			AES256Cipher.getInstance();
			ocd.setClientSecret(userIdx);
			ocd.setScope("read,write");
			ocd.setAuthorizedGrantTypes("password,authorization_code,refresh_token");
			ocd.setWebServerRedirectUri("/callback");
			ocd.setAuthorities("ROLE_USER");
			ocd.setAccessTokenValidity(3600);
			ocd.setRefreshTokenValidity(3600);
			ocd.setAutoapprove("true");
			oauthClientDetailsRepository.save(ocd);

			UserAuth userAuth = new UserAuth();
			userAuth.setIdx(UUID.randomUUID().toString());
			userAuth.setId(user.getUserId());
			userAuth.setAuth("ROLE_USER");
			userAuthRepository.save(userAuth);

			json.put("result", true);
			json.put("userIdx", userIdx);

		} catch (Exception e) {
			json.put("result", false);
		}

		FrogUserList frogUserList = new FrogUserList();
		try {
			frogUserList.setIdx(userIdx);
			frogUserList.setUserName(userList.getUserName());
			frogUserList.setUserId(userList.getUserId());
			frogUserList.setUserEmail(userList.getUserEmail());
			frogUserList.setUserPassword(sha.shaCrypt(AES256Cipher.getInstance().AES_Encode(userList.getUserPassword())));
			frogUserList.setBlocked(false);
			frogUserList.setDeleted(false);
			frogUserList.setNickname(false);
			
			frogUserListRepository.save(frogUserList);
			FrogCryptoList frogCryptoList = frogCryptoListRepository.findByCode("FRG");
			FrogWalletList frogWalletList = frogWalletListRepository.findByCryptoIdx(frogCryptoList.getIdx());

			Map<String, String> walletCreate = new HashMap<>();

			walletCreate = createFrogTokenWallet(Constants.FROGWALLET_PASSWORD, userIdx);

			FrogUserWallet frogUserWallet = new FrogUserWallet();

			frogUserWallet.setIdx(UUID.randomUUID().toString());
			frogUserWallet.setUserIdx(userIdx);
			frogUserWallet.setWalletIdx(frogWalletList.getIdx());
			frogUserWallet.setWalletAddress(walletCreate.get("address"));
			frogUserWallet.setAmount(0);
			frogUserWallet.setAveragePriceDollar(0.0);
			frogUserWallet.setTokenInfo(walletCreate.get("tokenInfo"));
			frogUserWallet.setTokenPath(walletCreate.get("tokenPath"));

			frogUserWalletRepository.save(frogUserWallet);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("result", false);
		}*/

		return "";
	}

	public Map<String, String> createFrogTokenWallet(final String password, final String userIdx) {
		Map<String, String> result = new HashMap<String, String>();

		try {
			File path = new File("/storage/seekers/" + userIdx + "/frog/wallets");
			if (!path.exists()) {
				path.mkdirs();
			}
			File file = new File(path + "/FRG.json");
			if (!file.exists()) {
				String fileName = WalletUtils.generateLightNewWalletFile(password, new File(String.valueOf(path))); // Ïß?Í∞ëÏÉù?Ñ±
				String tempPath = path + "/" + fileName;

				Credentials credentials = WalletUtils.loadCredentials(password, tempPath);

				result.put("address", credentials.getAddress());

				file = new File(tempPath);
				file.renameTo(new File(path + "/FRG.json"));
				file = new File(path + "/FRG.json");
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
