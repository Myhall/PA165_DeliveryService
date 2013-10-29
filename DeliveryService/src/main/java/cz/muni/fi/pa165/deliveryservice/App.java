package cz.muni.fi.pa165.deliveryservice;

import cz.muni.fi.pa165.delivery.service.CourierService;
import cz.muni.fi.pa165.deliveryservice.dao.CourierDAO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;

/**
 * Hello world!
 *
 */
public class App {

     public static void main(String[] args) throws Exception {
         
        ApplicationContext context = 
    	  new ClassPathXmlApplicationContext(new String[] {"classpath:applicationContext.xml"});
        
        CourierService courierService = (CourierService) context.getBean("courierService");
        courierService.createCourier("first", "last", "email");
        courierService.createCourier("firstName", "lastName", "email@email.com");
        
        System.out.println("******************");
        System.out.println(courierService.getAllCouriers());
    
    }
}
