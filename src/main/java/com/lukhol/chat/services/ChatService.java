package com.lukhol.chat.services;

import java.util.List;

import com.lukhol.chat.models.Conversation;
import com.lukhol.chat.models.Message;
import com.lukhol.chat.models.User;

public interface ChatService {
	public boolean login(User user);
	public boolean logout(User user);
	
	public Conversation createConversation(User sender, User receiver);
	public boolean sendMessage(User sender, User receiver, Message message);
	
	public List<Message> waitForMessages(User waiter);
}
