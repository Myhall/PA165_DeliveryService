/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.DeliveryItem;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryItemDAO;
import javax.persistence.EntityManager;

/**
 * JPA implementation of DeliveryItemDAO interface.
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
public class JPADeliveryItemDAO implements DeliveryItemDAO {

    private EntityManager em;

    public JPADeliveryItemDAO(EntityManager em) {
        this.em = em;
    }        

    public void createDeliveryItem(DeliveryItem deliveryItem) {
        if (deliveryItem == null) {
            throw new NullPointerException("Delivery item is null.");
        }
        
        if (deliveryItem.getId() != null) {
            throw new IllegalArgumentException("Delivery item is already present in DB.");
        }
        
        em.getTransaction().begin();
        em.persist(deliveryItem);
        em.getTransaction().commit();
    }

    public void deleteDeliveryItem(DeliveryItem deliveryItem) {
        if (deliveryItem == null) {
            throw new NullPointerException("Delivery item is null.");
        }
        
        if (deliveryItem.getId() == null) {
            throw new NullPointerException("Delivery item ID is null.");
        }
        DeliveryItem toRemove = em.find(DeliveryItem.class, deliveryItem.getId());
        if (toRemove != null) {
            em.getTransaction().begin();
            em.remove(em);
            em.getTransaction().commit();
        }
    }

    public void updateDeliveryItem(DeliveryItem deliveryItem) {
        if (deliveryItem == null) {
            throw new NullPointerException("Delivery item is null.");
        }
        
        if (deliveryItem.getId() == null) {
            throw new NullPointerException("Delivery item ID is null.");
        }
        
        if (em.find(DeliveryItem.class, deliveryItem.getId()) == null) {
            throw new NullPointerException("Delivery item was not found in DB."); //NullPointer or IllegalArgument?
        }
        
        em.getTransaction().begin();
        em.merge(deliveryItem);
        em.getTransaction().commit();
    }
}
