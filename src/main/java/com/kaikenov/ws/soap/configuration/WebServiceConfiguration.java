package com.kaikenov.ws.soap.configuration;

import com.kaikenov.ws.soap.CustomerOrderWsImpl;
import com.kaikenov.ws.soap.handler.SiteHandler;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebServiceConfiguration {

    @Autowired
    public Bus bus;

    @Bean
    public Endpoint endpoint() {
        Endpoint endpoint = new EndpointImpl(bus, new CustomerOrderWsImpl());
        endpoint.publish("/customer-order-service");

        SOAPBinding binding = (SOAPBinding) endpoint.getBinding();
        List<Handler> handlerChain = new ArrayList<>();
        handlerChain.add(new SiteHandler());
        binding.setHandlerChain(handlerChain);

        return endpoint;
    }

}
