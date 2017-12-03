package com.lukhol.chat.common;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.HttpRequestHandler;

import com.lukhol.chat.services.ChatService;
import com.lukhol.chat.services.ChatServiceImpl;

@Configuration
@ComponentScan
@SpringBootApplication
public class Application {
	
	@Autowired
	ChatService chatService;
	
	@Autowired
	Endpoints endpoints;
	
	public static void main(String[] args){
		SpringApplication.run(Application.class,  args);
	}

	//Endpoints:
	//-----------------------------------------------------------------------
	
	@Bean(name = "/burlapChat")
	HttpRequestHandler burlapChat(){
		return endpoints.burlap(ChatService.class, chatService);
	}
	
	@Bean(name = "/hessianChat")
	HttpRequestHandler hessianChat(){
		return endpoints.hessian(ChatService.class, chatService);
	}
	
	@Bean(name ="/xmlRpc")
	public ServletRegistrationBean servletRegistrationBean(){
		HttpServlet servlet = new MyXmlRpcServlet(chatService);
	    return new ServletRegistrationBean(servlet, "/xmlRpc");
	}
	
	//------------------------------------------------------------------------
	
	@Bean
	ChatService chatService(){
		return new ChatServiceImpl();
	}
}
