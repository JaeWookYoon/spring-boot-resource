package com.jwyoon.www.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.jwyoon.www.common.util.Constants;
import com.jwyoon.www.model.UserList;
import com.jwyoon.www.oauth.PasswordEncoders;
import com.jwyoon.www.repository.UserListRepository;

@RequestMapping(value = "/private", produces = "application/json")
@RestController
public class PrivateSelectController {

    private final static String PATH_URL = "/select";

    @Resource(name = "userListRepository")
    private UserListRepository userListRepository;
    @GetMapping(value=Constants.BASIC_URL+"/test")
    public String test(String test) {
    	return test;
    }
    @PostMapping(value = Constants.BASIC_URL + "/check_token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String privateTest(HttpServletRequest request) {
        String tokenFromAndroid = request.getHeader("Authorization").split(" ")[1];
        JSONObject json = null;
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("https://localhost:8080" + "/oauth/check_token");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("token", tokenFromAndroid));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);
        post.setEntity(entity);
        HttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONParser parser = new JSONParser();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String str = "";
            StringBuffer strBuf = new StringBuffer();
            while ((str = br.readLine()) != null) {
                strBuf.append(str);
            }
            json = (JSONObject) parser.parse(strBuf.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String user_id = (String) json.get("user_name");
        UserList user_model = userListRepository.findByUserId(user_id);
        Gson gson = new Gson();
        String str = gson.toJson(user_model);
        PasswordEncoders passwordEncoders = new PasswordEncoders();
        str = passwordEncoders.encode(str);

        return str;
    }
    @GetMapping(value = Constants.BASIC_URL + "/fuck", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String testGet() {
    	return "Fuck";
    }

}