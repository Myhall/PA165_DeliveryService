/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.DeliveryItem;
import java.util.List;

/**
 * DAO interface for DeliveryItem entity
 * 
 * @author Filip Volner <volner@mail.muni.cz>
 */
public interface DeliveryItemDAO {
    
    /**
     * Store new delivery item to database. ID is generated automatically.
     * 
     * @throws IllegalArgumentException deliveryItem is null
     * @throws IllegalArgumentException id attribute of deliveryItem is NOT null
     * @param deliveryItem New delivery item to be stored
     */
    void createDeliveryItem(DeliveryItem deliveryItem);

    /**
     * Remove delivery item from database.
     * 
     * @throws IllegalArgumentException deliveryItem is null
     * @throws IllegalArgumentException id attribute of deliveryItem is null
     * @param deliveryItem Delivery item to be removed
     */
    void deleteDeliveryItem(DeliveryItem deliveryItem);

    /**
     * Update delivery item in database.
     * 
     * @param deliveryItem Delivery item to be updated in database
     * @throws IllegalArgumentException deliveryItem is null
     * @throws IllegalArgumentException id attribute of deliveryItem is null
     * @throws IllegalArgumentException delivery item was not found in database
     */
    void updateDeliveryItem(DeliveryItem deliveryItem);

    /**
     * Get list of delivery items belonging to specific delivery.
     * 
     * @param delivery Delivery to which the items belong
     * @return list of delivery items belonging to specified delivery
     * @throws IllegalArgumentException delivery is null
     * @throws IllegalArgumentException id attribute of delivery is null
     * @throws IllegalArgumentException delivery is not in database
     */
    List<DeliveryItem> getDeliveryItemsByDelivery(Delivery delivery);
    
}
