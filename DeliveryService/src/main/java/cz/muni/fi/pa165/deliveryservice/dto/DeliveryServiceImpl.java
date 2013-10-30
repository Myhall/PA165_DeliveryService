package cz.muni.fi.pa165.deliveryservice.dto;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.DeliveryStatus;
import cz.muni.fi.pa165.deliveryservice.service.DeliveryService;
import java.util.List;

/**
 *
 * @author Michal Sorentiny
 */
public class DeliveryServiceImpl implements DeliveryService {

    @Override
    public DeliveryDTO createDelivery(DeliveryDTO delivery) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDelivery(DeliveryDTO delivery) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DeliveryDTO updateDelivery(DeliveryDTO delivery) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DeliveryDTO findDelivery(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DeliveryDTO> getAllDeliveries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DeliveryDTO> getDeliveriesByStatus(DeliveryStatus status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DeliveryDTO> getDeliveriesByCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DeliveryDTO> getDeliveriesByCourier(Courier courier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
