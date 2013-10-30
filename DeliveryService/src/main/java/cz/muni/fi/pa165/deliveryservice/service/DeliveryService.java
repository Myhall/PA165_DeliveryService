package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.DeliveryStatus;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryDTO;
import java.util.List;

/**
 *
 * @author Michal Sorentiny
 */
public interface DeliveryService {
    
    DeliveryDTO createDelivery(DeliveryDTO delivery);
    
    void deleteDelivery(DeliveryDTO delivery);
    
    DeliveryDTO updateDelivery(DeliveryDTO delivery);
    
    DeliveryDTO findDelivery(Long id);
    
    List<DeliveryDTO> getAllDeliveries();
    
    List<DeliveryDTO> getDeliveriesByStatus(DeliveryStatus status);
    
    List<DeliveryDTO> getDeliveriesByCustomer(Customer customer);
    
    List<DeliveryDTO> getDeliveriesByCourier(Courier courier);
}
