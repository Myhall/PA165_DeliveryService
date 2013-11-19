package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.enums.DeliveryStatus;
import java.util.List;

/**
 * DAO layer interface for Delivery entity.
 * @author Michal Sorentiny
 */
public interface DeliveryDAO {
    
    /**
     * Takes delivery and persists it in the database.
     * @param delivery to persist. Can not be null
     * @throws NullPointerException if delivery is null
     */
    public void createDelivery(Delivery delivery);
    
    /**
     * Removes delivery from database
     * @param delivery to remove. Cannot be null
     * @throws NullPointerException if delivery is null
     * @throws IllegalStateException if delivery.getId() is null
     */
    public void deleteDelivery(Delivery delivery);
    
    /**
     * Updates delivery in the database. If delivery is not persisted, inserts it
     * into the DB.
     * @param delivery to insert. Cannot be null.
     * @throws NullPointerException if delivery is null
     */
    public Delivery updateDelivery(Delivery delivery);
    
    /**
     * Gets Delivery object from DB
     * @param id of desired Delivery entity
     * @return Delivery from DB, or null
     * @throws NullPointerException if id is null
     */
    public Delivery findDelivery(Long id);
    
    /**
     * Gets all Deliveries from DB.
     * @return List of Delivery entities.
     */
    public List<Delivery> getAllDeliveries();
    
    /**
     * Gets all Deliveries with given status.
     * @param status of the Delivery
     * @return List of Delivery Entities
     */
    public List<Delivery> getDeliveriesByStatus(DeliveryStatus status);
    
    /**
     * Gets all Deliveries from given Customer.
     * @param customer who created the Delivery
     * @return List of Delivery Entities
     */
    public List<Delivery> getDeliveriesByCustomer(Customer customer);
    
    /**
     * Gets all Deliveries handled by given Courier.
     * @param courier who handles  the Delivery
     * @return List of Delivery Entities
     */
    public List<Delivery> getDeliveriesByCourier(Courier courier);
}
