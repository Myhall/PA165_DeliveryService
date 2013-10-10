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
     * @throws IllegalArgumentException id attribute of deliveryItem is NOT null
     * @param deliveryItem New delivery item to be stored
     */
    void createDeliveryItem(DeliveryItem deliveryItem);

    /**
     * Remove delivery item from database.
     * 
     * @throws NullPointerException deliveryItem is null
     * @throws NullPointerException id attribute of deliveryItem is null
     * @param deliveryItem Delivery item to be removed
     */
    void deleteDeliveryItem(DeliveryItem deliveryItem);

    /**
     * Update delivery item in database.
     * 
     * @param deliveryItem Delivery item to be updated in database
     * @throws NullPointerException deliveryItem is null
     * @throws NullPointerException id attribute of deliveryItem is null
     * @throws NullPointerException delivery item was not found in database
     */
    void updateDeliveryItem(DeliveryItem deliveryItem);
    
}
