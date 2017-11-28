package com.lukhol.chat.services;

import com.lukhol.chat.models.User;

public interface ChatService {
	public boolean login(User user);
	public boolean logout(User user);
}
