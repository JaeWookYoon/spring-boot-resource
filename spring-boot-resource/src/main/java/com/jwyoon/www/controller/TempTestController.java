package com.jwyoon.www.controller;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jwyoon.www.model.UserList;
import com.jwyoon.www.repository.UserListRepository;

import reactor.core.publisher.Flux;

@EnableWebMvc
@Controller()
@RequestMapping("/test")
public class TempTestController {
	
	WebClient webClient = null;
	
    private final UserListRepository userListRepository;
	
    @Autowired
	public TempTestController(UserListRepository userListRepository) {
		this.userListRepository = userListRepository;
	}
	@GetMapping("/quiz")
	public String testPage() {
		ModelAndView modelAndView = new ModelAndView();
        
        return "quiz";
	}
	@GetMapping("/")
	public String hello() {
		return "index";
	}
	@PostMapping("/token")
	public Flux<String> token(String id,String pw){
		
		String username = id;
        String password = pw;        
        //String client_id = id;
        
        UserList user = userListRepository.findByUserId(username);
        
		String auth = username + ":" + user.getIdx();// userlist Idx ?? oauth_client_details client_secret?�� ?��?��매핑?��???��.
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
		webClient = WebClient.create();
		String result = webClient.post().
		  uri("http://localhost:8080/oauth/token")
		  .header(HttpHeaders.AUTHORIZATION, authHeader)
		  //.header("Content-Type","application/x-www-form-urlencoded; charset=utf-8")
		  .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		  .acceptCharset(Charset.forName("UTF-8"))
		  .bodyValue("grant_type=password&username="+username+"&password="+password)			  
		  .retrieve()
		  .bodyToMono(JSONObject.class)
		  .block().toJSONString();
		System.out.println(result);
		return null;
	}
}
