package cz.muni.fi.pa165.deliveryservice;

import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

     public static void main(String[] args) throws Exception {
         
        ApplicationContext context = 
    	  new ClassPathXmlApplicationContext(new String[] {"classpath:applicationContext.xml"});
        
        CourierService courierService = (CourierService) context.getBean("courierService");
        courierService.createCourier(new CourierDTO("first", "last", "email"));
        CourierDTO c = courierService.createCourier(new CourierDTO("firstName", "lastName", "email@email.com"));
        
        
        System.out.println("******************");
        System.out.println(courierService.findCourier(1L).getFirstName());
        System.out.println(courierService.getAllCouriers());
        
        courierService.createCourier(c);
    }
}
