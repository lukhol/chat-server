package com.lukhol.chat.services;

public interface ChatServiceWrapper {
	boolean login(String user);
	void logout(String user);
	
	String createConversation(String sender, String receiver);
	boolean sendMessage(String sender, String receiver, String message);
	
	String waitForMessages(String waiter);
	
	String getLoggedInUsers(String dummy);
}
