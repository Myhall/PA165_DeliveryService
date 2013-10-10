/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.DeliveryItem;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryItemDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * JPA implementation of DeliveryItemDAO interface.
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
public class JPADeliveryItemDAO implements DeliveryItemDAO {

    @PersistenceContext
    EntityManager em;

    public void createDeliveryItem(DeliveryItem deliveryItem) {
        if (deliveryItem == null || deliveryItem.getId() != null) {
            throw new IllegalArgumentException("Delivery item is null or is already present in DB.");
        }
        em.getTransaction().begin();
        em.persist(deliveryItem);
        em.getTransaction().commit();
    }

    public void deleteDeliveryItem(DeliveryItem deliveryItem) {
        if (deliveryItem == null || deliveryItem.getId() == null) {
            throw new IllegalArgumentException("Delivery item is null or has null ID.");
        }
        DeliveryItem toRemove = em.find(DeliveryItem.class, deliveryItem.getId());
        if (toRemove != null) {
            em.getTransaction().begin();
            em.remove(em);
            em.getTransaction().commit();
        }
    }

    public void updateDeliveryItem(DeliveryItem deliveryItem) {
        if (deliveryItem == null || deliveryItem.getId() == null) {
            throw new IllegalArgumentException("Delivery item is null or has null ID.");
        }
        
        if (em.find(DeliveryItem.class, deliveryItem.getId()) == null) {
            throw new IllegalArgumentException("Delivery item was not found in DB.");
        }
        
        em.getTransaction().begin();
        em.merge(deliveryItem);
        em.getTransaction().commit();
    }

    public List<DeliveryItem> getDeliveryItemsByDelivery(Delivery delivery) {
        if(delivery == null || delivery.getId() == null) {
            throw new IllegalArgumentException("Delivery is null or delivery id is null.");
        }                
        
        Delivery result = em.find(Delivery.class, delivery.getId());
        if (result == null) {
            throw new IllegalArgumentException("Delivery is not in DB.");
        }
        
        return result.getItems();
    }
}
