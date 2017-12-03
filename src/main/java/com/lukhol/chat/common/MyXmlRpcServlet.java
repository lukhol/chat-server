package com.lukhol.chat.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.metadata.XmlRpcSystemImpl;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.XmlRpcServlet;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;

import com.lukhol.chat.services.ChatService;
import com.lukhol.chat.services.ChatServiceImpl;

public class MyXmlRpcServlet extends XmlRpcServlet {
	
	private static final long serialVersionUID = 3243672819433429299L;

	private ChatService chatService;
	
	public MyXmlRpcServlet(ChatService chatService){
		this.chatService = chatService;
		System.out.println("XmlRpc servlet created.");
	}
	
	@Override
	protected XmlRpcHandlerMapping newXmlRpcHandlerMapping() throws XmlRpcException {
		PropertyHandlerMapping mapping = new PropertyHandlerMapping();
		mapping.setRequestProcessorFactoryFactory(new ChatRequestProcessorFactoryFactory(chatService));
		mapping.setVoidMethodEnabled(true);
		mapping.addHandler("ChatService", ChatServiceImpl.class);
	    XmlRpcSystemImpl.addSystemHandler(mapping);
		
		XmlRpcServletServer server = getXmlRpcServletServer();
		server.setHandlerMapping(mapping);
		
		XmlRpcServerConfigImpl config = (XmlRpcServerConfigImpl)server.getConfig();
		config.setEnabledForExtensions(true);
		server.setConfig(config);
		
		System.out.println("Mapping created");
		return mapping;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest pRequest, HttpServletResponse pResponse)throws IOException, ServletException {
		super.doPost(pRequest, pResponse);
	}
	
}
