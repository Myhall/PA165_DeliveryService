package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.DeliveryStatus;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryDAO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Michal Sorentiny
 */
public class JPADeliveryDAO implements DeliveryDAO{
    
//    @PersistenceContext
    EntityManager em;

    public JPADeliveryDAO() {
    }
    
    public JPADeliveryDAO(EntityManager em) {
        if (em == null) {
            throw new NullPointerException("em cannot be null");
        }
        this.em = em;
    }

    
    public void createDelivery(Delivery delivery) {
        if (delivery == null) {
            throw new NullPointerException("delivery cannot be null");
        }
        em.persist(delivery);
    }

    public void deleteDelivery(Delivery delivery) {
        if (delivery == null) {
            throw new NullPointerException("delivery cannot be null");
        }
        Delivery toRemove = em.find(Delivery.class, delivery.getId());
        if (toRemove != null) {
            em.remove(toRemove);
        }
    }

    public void updateDelivery(Delivery delivery) {
        if (delivery == null) {
            throw new NullPointerException("delivery cannot be null");
        }
        delivery = em.merge(delivery);
    }

    public Delivery findDelivery(Long id) {
        if (id == null) {
            throw new NullPointerException("id cannot be null");
        }
        return em.find(Delivery.class, id);
    }
    
    

    public List<Delivery> getAllDeliveries() {
        return em.createQuery("SELECT d FROM Delivery d;").getResultList();
    }

    public List<Delivery> getDeliveriesByStatus(DeliveryStatus status) {
        return em.createQuery("SELECT d FROM Delivery d WHERE d.status = :status;").setParameter("status", status).getResultList();
    }

    public List<Delivery> getDeliveriesByCustomer(Customer customer) {
        return em.createQuery("SELECT d FROM Delivery d WHERE d.customer = :customer;").setParameter("customer", customer).getResultList();
    }

    public List<Delivery> getDeliveriesByCourier(Courier courier) {
        return em.createQuery("SELECT d FROM Delivery d WHERE d.status = :courier;").setParameter("courier", courier).getResultList();
    }    
}
