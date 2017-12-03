package com.lukhol.chat.common;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;

import com.lukhol.chat.services.ChatService;

public class ChatRequestProcessorFactoryFactory implements RequestProcessorFactoryFactory{

	private final RequestProcessorFactory factory = new ChatRequestProcessorFactory();
	private final ChatService chatService;
	
	public ChatRequestProcessorFactoryFactory(ChatService chatService){
		this.chatService = chatService;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public RequestProcessorFactory getRequestProcessorFactory(Class arg0) throws XmlRpcException {
		return factory;
	}

	private class ChatRequestProcessorFactory implements RequestProcessorFactory {
	      public Object getRequestProcessor(XmlRpcRequest xmlRpcRequest)
	          throws XmlRpcException {
	        return chatService;
	      }
	    }
}
