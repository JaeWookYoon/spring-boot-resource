package com.jwyoon.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//http://www.thymeleaf.org/doc/articles/layouts.html
/*@ComponentScan({"com.seekersbc.bridge.resource.android.credential.repository.*" , 
	"com.seekersbc.bridge.resource.android.credential.controller.*", 
	"com.seekersbc.bridge.resource.android.credential.utils.*",
	"com.seekersbc.bridge.resource.oauth.*",
	"com.seekersbc.bridge.resource.error.*"	})*/
/*@EnableJpaRepositories("com.seekersbc.bridge.resource.android.credential.repository")*/
@SpringBootApplication/*(exclude= {SecurityAutoConfiguration.class})*/
@EnableScheduling
public class SpringBootWebApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}	
}