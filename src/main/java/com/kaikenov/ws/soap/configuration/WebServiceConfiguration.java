package com.kaikenov.ws.soap.configuration;

import com.kaikenov.ws.soap.CustomerOrderWsImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfiguration {

    @Autowired
    public Bus bus;

    @Bean
    public Endpoint endpoint() {
        Endpoint endpoint = new EndpointImpl(bus, new CustomerOrderWsImpl());
        endpoint.publish("/customer-order-service");

        return endpoint;
    }

}
