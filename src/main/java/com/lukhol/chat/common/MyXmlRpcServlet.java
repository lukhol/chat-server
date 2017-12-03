package com.lukhol.chat.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.metadata.XmlRpcSystemImpl;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;

import com.lukhol.chat.services.ChatService;
import com.lukhol.chat.services.ChatServiceWrapper;
import com.lukhol.chat.services.ChatServiceWrapperImpl;

public class MyXmlRpcServlet extends XmlRpcServlet {
	
	private static final long serialVersionUID = 3243672819433429299L;

	private ChatServiceWrapper chatServiceWrapper;
	
	public MyXmlRpcServlet(ChatServiceWrapper chatServiceWrapper){
		this.chatServiceWrapper = chatServiceWrapper;
		System.out.println("XmlRpc servlet created.");
	}
	
	@Override
	protected XmlRpcHandlerMapping newXmlRpcHandlerMapping() throws XmlRpcException {
		PropertyHandlerMapping mapping = new PropertyHandlerMapping();
		mapping.setRequestProcessorFactoryFactory(new ChatRequestProcessorFactoryFactory(chatServiceWrapper));
		mapping.setVoidMethodEnabled(true);
		mapping.addHandler("ChatServiceWrapper", ChatServiceWrapperImpl.class);
	    XmlRpcSystemImpl.addSystemHandler(mapping);
		
		XmlRpcServletServer server = getXmlRpcServletServer();
		server.setHandlerMapping(mapping);
	    
		System.out.println("Mapping created");
		return mapping;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("DoPost");
		super.doPost(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest pRequest, HttpServletResponse pResponse)throws IOException, ServletException {
		System.out.println("Post in xml-rpc servlet");
		super.doPost(pRequest, pResponse);
	}
	
}
