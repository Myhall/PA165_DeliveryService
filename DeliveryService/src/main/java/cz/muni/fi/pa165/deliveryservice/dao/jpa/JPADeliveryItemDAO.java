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
        if (em == null) {
            throw new NullPointerException("Entity manager is null.");
        }
        this.em = em;
    }        

    public void createDeliveryItem(DeliveryItem deliveryItem) {
        if (deliveryItem == null) {
            throw new NullPointerException("Delivery item is null.");
        }
        
        if (deliveryItem.getId() != null) {
            throw new IllegalArgumentException("Delivery item is already present in DB.");
        }
                
        em.persist(deliveryItem);        
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
            em.remove(em);            
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
                
        deliveryItem = em.merge(deliveryItem);        
    }

    public DeliveryItem findDeliveryItem(Long id) {
        if (id == null) {
            throw new NullPointerException("ID is null.");
        }
        
        DeliveryItem result;
        result = em.find(DeliveryItem.class, id);
        
        return result;
    }
    
    
}
