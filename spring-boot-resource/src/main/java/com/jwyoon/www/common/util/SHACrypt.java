package com.jwyoon.www.common.util;

import org.springframework.stereotype.Component;

import com.lambdaworks.crypto.SCryptUtil;
@Component
public class SHACrypt {

	public String shaCrypt(String password) {
		return SCryptUtil.scrypt(password, 16384, 8, 1);
	}
}
