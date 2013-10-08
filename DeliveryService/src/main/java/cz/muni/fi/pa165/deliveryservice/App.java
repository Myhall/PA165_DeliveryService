package cz.muni.fi.pa165.deliveryservice;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeliveryServicePu");

    public static void main(String[] args) throws Exception {
        EntityManager em = emf.createEntityManager();

        Courier courier = new Courier("Jan", "Vorcak", "vorcak@gmail.com");
        Delivery delivery = new Delivery();
        
        DeliveryItem item = new DeliveryItem();
        List<DeliveryItem> items = new ArrayList<DeliveryItem>();
        items.add(item);
        delivery.setItems(items);
        
        delivery.setStatus(DeliveryStatus.CREATED);

        em.getTransaction().begin();

        em.persist(courier);
        em.persist(delivery);

        em.getTransaction().commit();

        em.getTransaction().begin();

        courier.pickDelivery(delivery);
        courier.deliver(delivery);

        em.getTransaction().commit();

    }
}
