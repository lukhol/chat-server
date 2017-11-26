package com.lukhol.chat.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.caucho.BurlapServiceExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

@SuppressWarnings("deprecation")
@Component
public class Endpoints {
	
	private final Logger logger = LoggerFactory.getLogger(Endpoints.class);
	
	public <T> HttpRequestHandler burlap(Class<T> clazz, T service) {
		BurlapServiceExporter exporter = new BurlapServiceExporter();
		exporter.setService(service);
		exporter.setServiceInterface(clazz);
		logger.info("Burlap endpoint created.");

		return exporter;
	}
	
	public <T> HttpRequestHandler hessian(Class<T> clazz, T service){
		HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(service);
        exporter.setServiceInterface(clazz);
		logger.info("Hessian endpoint created.");
        return exporter;
	}
}
