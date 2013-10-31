/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.dto.DeliveryItemDTO;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
public interface DeliveryItemService {
    
    /**
     * Create new delivery item.
     * 
     * @param deliveryItem New delivery item to be stored
     */
    void createDeliveryItem(DeliveryItemDTO deliveryItem);

    /**
     * Delete delivery item.
     * 
     * @param deliveryItem Delivery item to be removed
     */
    void deleteDeliveryItem(DeliveryItemDTO deliveryItem);

    /**
     * Update delivery item.
     * 
     * @param deliveryItem Delivery item to be updated in database
     * @return DTO version of updated delivery item
     */
    DeliveryItemDTO updateDeliveryItem(DeliveryItemDTO deliveryItem);
    
    /**
     * Find delivery item by ID.
     * 
     * @param id ID of delivery item to be found
     * @return delivery item DTO object or null if no such item is found
     */
    DeliveryItemDTO findDeliveryItem(Long id);
    
}
