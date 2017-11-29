package com.lukhol.chat.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lukhol.chat.models.Conversation;
import com.lukhol.chat.models.Message;
import com.lukhol.chat.models.User;

@Component
public class ChatServiceImpl implements ChatService {
	
	private final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);
	
	private List<String> loggedUsersList = new ArrayList<String>();
	private Map<String, Conversation> conversationsMap = new HashMap<String, Conversation>();
	private List<String> waitingUsersList = new ArrayList<String>();
	private List<Message> messageToSend = new ArrayList<Message>();
	
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
	public synchronized boolean logout(User user) {
		if(loggedUsersList.contains(user.getUsername())){
			loggedUsersList.remove(user.getUsername());
			waitingUsersList.remove(user.getUsername());
			logger.info(user.getUsername() + " succesfully logged out.");
			
			waitingUsersList.forEach(usr -> System.out.println(usr));
			notifyAll();
			
			return true;
		}
		
		logger.info(user.getUsername() + " cannot log out.");
		
		return false;
	}

	@Override
	public synchronized Conversation createConversation(User sender, User receiver) {
		String key = createKey(sender, receiver);
		
		Conversation conversation = new Conversation();
		conversation.setUserOne(sender);
		conversation.setUserTwo(receiver);
		conversation.setListOfMessages(new ArrayList<Message>());
		
		conversationsMap.put(key, conversation);
		
		return conversation;
	}

	@Override
	public synchronized boolean sendMessage(User sender, User receiver, Message message) {
		logger.info("Started sendMessage: " + message.getMessageContent());
		String key = createKey(sender, receiver);
		
		Conversation conversation;
		
		if(conversationsMap.containsKey(key)){
			conversation = conversationsMap.get(key);
			
		} else {
			conversation = createConversation(sender, receiver);
		}
		
		message.setDelivered(false);
		conversation.getListOfMessages().add(message);
		conversation.setUpToDate(false);
		messageToSend.add(message);
		
		//messageToSend.forEach(msg -> System.out.println(msg.getMessageContent()));
		
		notifyAll();
		
		return true;
	}

	@Override
	public synchronized List<Message> waitForMessages(User waiter) {
		
		List<Message> messagesList = new ArrayList<Message>();
		
		if(!waitingUsersList.contains(waiter.getUsername()))
			waitingUsersList.add(waiter.getUsername());
		
		try {
			while(waitingUsersList.contains(waiter.getUsername())){
				wait();
				
				messagesList = messageToSend.stream()
					.filter(message -> message.getReceiver().getUsername().equals(waiter.getUsername()))
					.collect(Collectors.toList());
				
				if(messagesList != null && messagesList.size() > 0)
					break;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		waitingUsersList.remove(waiter.getUsername());
		messageToSend.removeAll(messagesList);
		return messagesList;
	}
	
	private synchronized String createKey(User sender, User receiver) {
		String senderUsername = sender.getUsername();
		String reveiverUsername = receiver.getUsername();
		
		int compareResult = senderUsername.compareTo(reveiverUsername);
		
		if(compareResult > 0){
			return senderUsername + reveiverUsername;
		} else {
			return reveiverUsername + senderUsername;
		}
	}
}
