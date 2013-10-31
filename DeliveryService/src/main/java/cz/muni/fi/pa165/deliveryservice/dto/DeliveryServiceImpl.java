package cz.muni.fi.pa165.deliveryservice.dto;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.DeliveryStatus;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryDAO;
import cz.muni.fi.pa165.deliveryservice.exceptions.DataPersistenceException;
import cz.muni.fi.pa165.deliveryservice.service.DeliveryService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Michal Sorentiny
 */
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryDAO deliveryDAO;
    @Autowired
    private Mapper mapper;

    @Override
    public DeliveryDTO createDelivery(DeliveryDTO delivery) {
        try {
            Delivery d = mapper.map(delivery, Delivery.class);
            deliveryDAO.createDelivery(d);
            return mapper.map(d, DeliveryDTO.class);
        } catch (Exception e) {
            //TODO exception message
            throw new DataPersistenceException("There has been an error on Persistence layer.", e);
        }
    }

    @Override
    public void deleteDelivery(DeliveryDTO delivery) {
        try {
            Delivery d = mapper.map(delivery, Delivery.class);
            deliveryDAO.deleteDelivery(d);
        } catch (Exception e) {
            //TODO exception message
            throw new DataPersistenceException("There has been an error on Persistence layer.", e);
        }
    }

    @Override
    public DeliveryDTO updateDelivery(DeliveryDTO delivery) {
        try {
            Delivery d = mapper.map(delivery, Delivery.class);
            return mapper.map(deliveryDAO.updateDelivery(d), DeliveryDTO.class);
        } catch (Exception e) {
            //TODO exception message
            throw new DataPersistenceException("There has been an error on Persistence layer.", e);
        }
    }

    @Override
    public DeliveryDTO findDelivery(Long id) {
        try {
            Delivery d = deliveryDAO.findDelivery(id);
            return mapper.map(d, DeliveryDTO.class);
        } catch (Exception e) {
            //TODO exception message
            throw new DataPersistenceException("There has been an error on Persistence layer.", e);
        }
    }

    @Override
    public List<DeliveryDTO> getAllDeliveries() {
        try {
            List<DeliveryDTO> returnMe = new ArrayList<>();
            for (Delivery d : deliveryDAO.getAllDeliveries()) {
                returnMe.add(mapper.map(d, DeliveryDTO.class));
            }
            return returnMe;
        } catch (Exception e) {
            //TODO exception message
            throw new DataPersistenceException("There has been an error on Persistence layer.", e);
        }
    }

    @Override
    public List<DeliveryDTO> getDeliveriesByStatus(DeliveryStatus status) {
        try {
            List<DeliveryDTO> returnMe = new ArrayList<>();
            for (Delivery d : deliveryDAO.getDeliveriesByStatus(status)) {
                returnMe.add(mapper.map(d, DeliveryDTO.class));
            }
            return returnMe;
        } catch (Exception e) {
            //TODO exception message
            throw new DataPersistenceException("There has been an error on Persistence layer.", e);
        }
    }

    @Override
    public List<DeliveryDTO> getDeliveriesByCustomer(Customer customer) {
        try {
            List<DeliveryDTO> returnMe = new ArrayList<>();
            for (Delivery d : deliveryDAO.getDeliveriesByCustomer(customer)) {
                returnMe.add(mapper.map(d, DeliveryDTO.class));
            }
            return returnMe;
        } catch (Exception e) {
            //TODO exception message
            throw new DataPersistenceException("There has been an error on Persistence layer.", e);
        }
    }

    @Override
    public List<DeliveryDTO> getDeliveriesByCourier(Courier courier) {
        try {
            List<DeliveryDTO> returnMe = new ArrayList<>();
            for (Delivery d : deliveryDAO.getDeliveriesByCourier(courier)) {
                returnMe.add(mapper.map(d, DeliveryDTO.class));
            }
            return returnMe;
        } catch (Exception e) {
            //TODO exception message
            throw new DataPersistenceException("There has been an error on Persistence layer.", e);
        }
    }
}
