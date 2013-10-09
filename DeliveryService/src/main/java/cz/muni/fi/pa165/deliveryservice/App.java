package cz.muni.fi.pa165.deliveryservice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeliveryServicePu");

    public static void main( String[] args )
    {
        EntityManager em = emf.createEntityManager();
        
    }
}
