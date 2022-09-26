package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TokenTest {

	@Autowired
	private WebApplicationContext ctx;
	public MockMvc mockMvc;
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	@Test
	public void 토큰받아오기() throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject param = new JSONObject();
		param.put("id", "client");		
		param.put("pw", "1234");
		String content = objectMapper.writeValueAsString(param);
		mockMvc.perform(post("/test/token").content(content)).andDo(print());
	}
}
