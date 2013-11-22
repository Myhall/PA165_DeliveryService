/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.dto.DeliveryItemDTO;
import java.util.List;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
public interface DeliveryItemService {
    
    /**
     * Create new delivery item.
     * 
     * @param deliveryItemDTO New delivery item to be stored
     */
    void createDeliveryItem(DeliveryItemDTO deliveryItemDTO);

    /**
     * Delete delivery item.
     * 
     * @param deliveryItemDTO Delivery item to be removed
     */
    void deleteDeliveryItem(DeliveryItemDTO deliveryItemDTO);

    /**
     * Update delivery item.
     * 
     * @param deliveryItemDTO Delivery item to be updated in database
     * @return DTO version of updated delivery item
     */
    DeliveryItemDTO updateDeliveryItem(DeliveryItemDTO deliveryItemDTO);
    
    /**
     * Find delivery item by ID.
     * 
     * @param id ID of delivery item to be found
     * @return delivery item DTO object or null if no such item is found
     */
    DeliveryItemDTO findDeliveryItem(Long id);
    
     /**
     * Returns list of all delivery items.
     *
     * @return List<DeliveryItemDTO> list of all delivery items
     */
    List<DeliveryItemDTO> getAllDeliveryItems();
    
}
