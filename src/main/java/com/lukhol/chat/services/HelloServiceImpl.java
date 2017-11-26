package com.lukhol.chat.services;

import org.springframework.stereotype.Component;

import com.lukhol.chat.models.User;

@Component
public class HelloServiceImpl implements HelloService {
	public void sayHello(User user) {
		System.out.println("Hello " + user.getUsername());
	}	
}
