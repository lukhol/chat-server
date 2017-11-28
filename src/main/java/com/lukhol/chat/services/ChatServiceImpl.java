package com.lukhol.chat.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.lukhol.chat.models.User;

@Component
public class ChatServiceImpl implements ChatService {
	
	private final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);
	
	List<String> loggedUsersList = new ArrayList<String>();
	
	public synchronized boolean login(User user){	
		if(!loggedUsersList.contains(user.getUsername())){
			loggedUsersList.add(user.getUsername());
			logger.info(user.getUsername() + " is now logged in.");
			return true;
		}
		
		logger.info("Cannot login user: " + user.getUsername());
		
		return false;
	}

	@Override
	public boolean logout(User user) {
		if(loggedUsersList.contains(user.getUsername())){
			loggedUsersList.remove(user.getUsername());
			
			logger.info(user.getUsername() + " succesfully logged out.");
			
			return true;
		}
		
		logger.info(user.getUsername() + " cannot log out.");
		
		return false;
	}
}
