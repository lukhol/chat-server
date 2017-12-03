package com.lukhol.chat.common;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;

import com.lukhol.chat.services.ChatServiceWrapper;

public class ChatRequestProcessorFactoryFactory implements RequestProcessorFactoryFactory{

	private final RequestProcessorFactory factory = new ChatRequestProcessorFactory();
	private final ChatServiceWrapper chatServiceWrapper;
	
	public ChatRequestProcessorFactoryFactory(ChatServiceWrapper chatServiceWrapper){
		this.chatServiceWrapper = chatServiceWrapper;
	}
	
	@Override
	public RequestProcessorFactory getRequestProcessorFactory(Class arg0) throws XmlRpcException {
		return factory;
	}

	private class ChatRequestProcessorFactory implements RequestProcessorFactory {
	      public Object getRequestProcessor(XmlRpcRequest xmlRpcRequest)
	          throws XmlRpcException {
	        return chatServiceWrapper;
	      }
	    }
}
