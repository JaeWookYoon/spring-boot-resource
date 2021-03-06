package com.jwyoon.www.utils;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.jwyoon.www.repository.UserListRepository;

import reactor.core.publisher.Mono;

@Component
public class RecommendCodeGenerator {

	@Resource(name = "userListRepository")
	private UserListRepository userListRepository;

	private String[] alphabets = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	private Random rnd = new Random();

	public String getRandom() {
		return alphabets[rnd.nextInt(alphabets.length)];
	}
	public String codeGenerate() {
		
		String rnd = getRandom() + getRandom() + getRandom() + getRandom() + getRandom() + getRandom();
		
		return rnd;
	}
	public static void main(String[]args) {
		WebClient webClient = WebClient.create("http://localhost:8081");
		Mono<String> res = webClient.get().uri("/timer")
		.retrieve().bodyToMono(String.class);
		res.subscribe(r->System.out.println(r));
	}
}