package com.jgroups.server;

public class Rpc {

	
	public   User findUser(String code) {

		if ("00008888".equalsIgnoreCase(code)) {
			return new User("00008888", "蔡铭琴");
		}
		return null;
	}

}
