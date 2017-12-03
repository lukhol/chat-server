package com.lukhol.chat.services;

import java.util.List;

import com.google.gson.Gson;
import com.lukhol.chat.models.Message;
import com.lukhol.chat.models.User;

public class ChatServiceWrapperImpl implements ChatServiceWrapper {

	private ChatService chatService;
	private Gson gson;
	
	public ChatServiceWrapperImpl(ChatService chatService) {
		this.chatService = chatService;
		this.gson = new Gson();
	}
	
	@Override
	public boolean login(String user) {
		User trueUser = gson.fromJson(user, User.class);
		return chatService.login(trueUser);
	}

	@Override
	public void logout(String user) {
		User trueUser = gson.fromJson(user, User.class);
		chatService.logout(trueUser);
	}

	@Override
	public String createConversation(String sender, String receiver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sendMessage(String sender, String receiver, String message) {
		User trueSender = gson.fromJson(sender, User.class);
		User trueReceiver = gson.fromJson(receiver, User.class);
		Message trueMessage = gson.fromJson(message,  Message.class);
		return chatService.sendMessage(trueSender, trueReceiver, trueMessage);
	}

	@Override
	public String waitForMessages(String waiter) {
		User trueWaiter = gson.fromJson(waiter, User.class);
		List<Message> listOfMessages = chatService.waitForMessages(trueWaiter);
		return gson.toJson(listOfMessages);
	}

	@Override
	public String getLoggedInUsers(String dummy) {
		List<String> listOfLoggedInUsers = chatService.getLoggedInUsers();
		return gson.toJson(listOfLoggedInUsers);
	}

}
