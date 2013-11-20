/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.DeliveryItem;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryItemDAO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * JPA implementation of DeliveryItemDAO interface.
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
@Repository
public class JPADeliveryItemDAO implements DeliveryItemDAO {

    @PersistenceContext
    private EntityManager em;

    public JPADeliveryItemDAO() {
    }
    
    public JPADeliveryItemDAO(EntityManager em) {
        if (em == null) {
            throw new NullPointerException("Entity manager is null.");
        }
        this.em = em;
    }        

    @Override
    public void createDeliveryItem(DeliveryItem deliveryItem) {
        if (deliveryItem == null) {
            throw new NullPointerException("Delivery item is null.");
        }
                
        em.persist(deliveryItem);        
    }

    @Override
    public void deleteDeliveryItem(DeliveryItem deliveryItem) {
        if (deliveryItem == null) {
            throw new NullPointerException("Delivery item is null.");
        }
        
        if (deliveryItem.getId() == null) {
            throw new IllegalStateException("Delivery item ID is null.");
        }
        
        DeliveryItem toRemove = em.find(DeliveryItem.class, deliveryItem.getId());
        if (toRemove != null) {            
            em.remove(deliveryItem);            
        }
    }

    @Override
    public DeliveryItem updateDeliveryItem(DeliveryItem deliveryItem) {
        if (deliveryItem == null) {
            throw new NullPointerException("Delivery item is null.");
        }

        return em.merge(deliveryItem);

    }

    @Override
    public DeliveryItem findDeliveryItem(Long id) {
        if (id == null) {
            throw new NullPointerException("ID is null.");
        }
        
        DeliveryItem result;
        result = em.find(DeliveryItem.class, id);
        
        return result;
    }
    
    
}
