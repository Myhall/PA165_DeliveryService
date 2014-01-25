package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.dao.DeliveryDAO;
import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryDTO;
import cz.muni.fi.pa165.deliveryservice.enums.DeliveryStatus;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michal Sorentiny
 */
@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryDAO deliveryDAO;
    @Autowired
    private Mapper mapper;

    @PreAuthorize("hasRole('ROLE_ADMIN') or "
            + "(hasRole('ROLE_USER') and principal.customer.id == #delivery.customer.id)")
    @Override
    public DeliveryDTO createDelivery(DeliveryDTO delivery) {
        if (delivery == null) {
            throw new NullPointerException("delivery");
        }

        Delivery d = mapper.map(delivery, Delivery.class);

        deliveryDAO.createDelivery(d);

        return mapper.map(d, DeliveryDTO.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or "
            + "(hasRole('ROLE_USER') and principal.customer.id == #delivery.customer.id ")
    @Override
    public void deleteDelivery(DeliveryDTO delivery) {
        if (delivery == null) {
            throw new NullPointerException("delivery");
        }

        Delivery d = mapper.map(delivery, Delivery.class);
        deliveryDAO.deleteDelivery(d);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or "
            + "(hasRole('ROLE_USER') and principal.customer.id == #delivery.customer.id ")
    @Override
    public DeliveryDTO updateDelivery(DeliveryDTO delivery) {
        if (delivery == null) {
            throw new NullPointerException("delivery");
        }

        Delivery d = mapper.map(delivery, Delivery.class);

        return mapper.map(deliveryDAO.updateDelivery(d), DeliveryDTO.class);
    }

    @PostAuthorize("hasRole('ROLE_ADMIN') or "
            + "(hasRole('ROLE_USER') and returnObject != null and principal.customer.id == returnObject.customer.id)")
    @Transactional(readOnly = true)
    @Override
    public DeliveryDTO findDelivery(Long id) {
        if (id == null) {
            throw new NullPointerException("id");
        }

        Delivery d = deliveryDAO.findDelivery(id);
        return mapper.map(d, DeliveryDTO.class);
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    @Override
    public List<DeliveryDTO> getAllDeliveries() {
        List<DeliveryDTO> returnMe = new ArrayList<>();
        for (Delivery d : deliveryDAO.getAllDeliveries()) {
            returnMe.add(mapper.map(d, DeliveryDTO.class));
        }
        return returnMe;
    }

    @Override
    public List<DeliveryDTO> getDeliveriesByStatus(DeliveryStatus status) {
        if (status == null) {
            throw new NullPointerException("status");
        }

        List<DeliveryDTO> returnMe = new ArrayList<>();
        for (Delivery d : deliveryDAO.getDeliveriesByStatus(status)) {
            returnMe.add(mapper.map(d, DeliveryDTO.class));
        }
        return returnMe;
    }

    @Override
    public List<DeliveryDTO> getDeliveriesByCustomer(CustomerDTO customer) {
        if (customer == null) {
            throw new NullPointerException("customer");
        }

        List<DeliveryDTO> returnMe = new ArrayList<>();
        for (Delivery d : deliveryDAO.getDeliveriesByCustomer(mapper.map(customer, Customer.class))) {
            returnMe.add(mapper.map(d, DeliveryDTO.class));
        }
        return returnMe;
    }

    @Override
    public List<DeliveryDTO> getDeliveriesByCourier(CourierDTO courier) {
        if (courier == null) {
            throw new NullPointerException("courier");
        }

        List<DeliveryDTO> returnMe = new ArrayList<>();
        for (Delivery d : deliveryDAO.getDeliveriesByCourier(mapper.map(courier, Courier.class))) {
            returnMe.add(mapper.map(d, DeliveryDTO.class));
        }
        return returnMe;
    }
}
