package com.lukhol.chat.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.caucho.BurlapServiceExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.metadata.XmlRpcSystemImpl;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcErrorLogger;

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
	
	public <T> HttpRequestHandler xmlRpc(Class<T> clazz, T service) {
		
//		XmlRpcServerConfigImpl xmlRpcConfig = new XmlRpcServerConfigImpl();
//		xmlRpcConfig.setEncoding(XmlRpcServerConfigImpl.UTF8_ENCODING);
//		xmlRpcConfig.setEnabledForExceptions(false);
//		xmlRpcConfig.setKeepAliveEnabled(true);
//		
//		PropertyHandlerMapping handlerMapping = new PropertyHandlerMapping();
//		handlerMapping.setRequestProcessorFactoryFactory(pClass -> pRequest -> service);
//		handlerMapping.addHandler(clazz.getSimpleName(), clazz);
//		
//		XmlRpcSystemImpl.addSystemHandler(handlerMapping);
//		
////		AnyXmlRpcServer server = new AnyXmlRpcServer(); // [axe-180254 or [use XmlRpcServletServer]]
////        server.setConfig(config);
////        server.setErrorLogger(new XmlRpcErrorLogger());
////        server.setHandlerMapping(handlerMapping);
////        server.setTypeFactory(new AnyTypeFactory(server)); // [axe-180254 or [remove statement]]
////        server.setFaultMapper(faultMapper); 
		
		return null;
	}
}
