package cz.muni.fi.pa165.deliveryservice.rest.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyHelper {
    
    @Value("${rest.couriers}")
    private String couriers;
    
    @Value("${rest.customers}")
    private String customers;
    
    @Value("${rest.host}") 
    private String host;
    @Value("${rest.port}")
    private int port;       
    @Value("${rest.webapp}")
    private String webapp;
    
    public String getCustomersURL() {
        return "http://" + host + ":" + port + "/" + webapp + "/" + customers;
    }
    
    public String getCouriersURL() {
        return "http://" + host + ":" + port + "/" + webapp + "/" + couriers;
    }
}
