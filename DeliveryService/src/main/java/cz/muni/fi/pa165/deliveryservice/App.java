package cz.muni.fi.pa165.deliveryservice;

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
        
        CourierDAO courierDAO = (CourierDAO)context.getBean("courierDao");
        System.out.println(courierDAO);

        courierDAO.createCourier(new Courier("first", "last", "email"));
        
        System.out.println("******************");
        System.out.println(courierDAO.getAllCouriers());
    
        
        
    }
}
