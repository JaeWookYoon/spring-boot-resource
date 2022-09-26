package com.jwyoon.www.controller;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jwyoon.www.model.UserList;
import com.jwyoon.www.repository.UserListRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class TempTestController {
	
	WebClient webClient = null;
	
    private final UserListRepository userListRepository;
	
    @Autowired
	public TempTestController(UserListRepository userListRepository) {
		this.userListRepository = userListRepository;
	}	
	@PostMapping("/token")
	public Mono<JSONObject> token(String id,String pw){
		
		String username = id;
        String password = pw;        
        //String client_id = id;
        
        UserList user = userListRepository.findByUserId(username);
        
		String auth = username + ":" + user.getIdx();// userlist Idx ?? oauth_client_details client_secret?�� ?��?��매핑?��???��.
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
		webClient = WebClient.create();
		Mono<JSONObject> result = webClient.post().
		  uri("http://localhost:8080/oauth/token")
		  .header(HttpHeaders.AUTHORIZATION, authHeader)
		  //.header("Content-Type","application/x-www-form-urlencoded; charset=utf-8")
		  .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		  .acceptCharset(Charset.forName("UTF-8"))
		  .bodyValue("grant_type=password&username="+username+"&password="+password)			  
		  .retrieve()
		  .bodyToMono(JSONObject.class);
		
		return result;
	}
}
