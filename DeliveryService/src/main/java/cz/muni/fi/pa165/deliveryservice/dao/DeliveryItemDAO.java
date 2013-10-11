/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.DeliveryItem;

/**
 * DAO interface for DeliveryItem entity
 * 
 * @author Filip Volner <volner@mail.muni.cz>
 */
public interface DeliveryItemDAO {
    
    /**
     * Store new delivery item to database. ID is generated automatically.
     * 
     * @throws NullPointerException deliveryItem is null
     * @param deliveryItem New delivery item to be stored
     */
    void createDeliveryItem(DeliveryItem deliveryItem);

    /**
     * Remove delivery item from database.
     * 
     * @throws NullPointerException deliveryItem is null
     * @throws IllegalStateException id attribute of deliveryItem is null
     * @param deliveryItem Delivery item to be removed
     */
    void deleteDeliveryItem(DeliveryItem deliveryItem);

    /**
     * Update delivery item in database.
     * 
     * @param deliveryItem Delivery item to be updated in database
     * @throws NullPointerException deliveryItem is null
     */
    DeliveryItem updateDeliveryItem(DeliveryItem deliveryItem);
    
    /**
     * Find delivery item in database.
     * 
     * @param id ID of delivery item to be found
     * @return delivery item with specified ID from DB or null if no such item is found
     * @throws NullPointerException id is null
     */
    DeliveryItem findDeliveryItem(Long id);
}
