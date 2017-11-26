package com.lukhol.chat.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.HttpRequestHandler;
import com.lukhol.chat.services.HelloService;
import com.lukhol.chat.services.HelloServiceImpl;

@Configuration
@ComponentScan
@SpringBootApplication
public class Application {
	
	@Autowired
	HelloService helloService;
	
	@Autowired
	Endpoints endpoints;
	
	public static void main(String[] args){
		SpringApplication.run(Application.class,  args);
	}

	//Endpoints:
	//-----------------------------------------------------------------------
	@Bean(name = "/burlapHello")
	HttpRequestHandler burlap(){
		return endpoints.burlap(HelloService.class, helloService);
	}
	
	@Bean(name = "/hessianHello")
	HttpRequestHandler hessian(){
		return endpoints.hessian(HelloService.class, helloService);
	}
	
	//------------------------------------------------------------------------
	@Bean
	HelloService helloService(){
		return new HelloServiceImpl();
	}
	
}
