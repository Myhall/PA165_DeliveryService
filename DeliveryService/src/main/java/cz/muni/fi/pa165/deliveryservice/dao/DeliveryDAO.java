package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.DeliveryStatus;
import java.util.List;

/**
 *
 * @author Michal Sorentiny
 */
public interface DeliveryDAO {
    
    public void createDelivery(Delivery delivery);
    
    public void deleteDelivery(Delivery delivery);
    
    public void updateDelivery(Delivery delivery);
    
    public Delivery findDelivery(Long id);
    
    public List<Delivery> getAllDeliveries();
    
    public List<Delivery> getDeliveriesByStatus(DeliveryStatus status);
    
    public List<Delivery> getDeliveriesByCustomer(Customer customer);
    
    public List<Delivery> getDeliveriesByCourier(Courier courier);
}
