/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.dao.CustomerDAO;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryDTO;
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
 * @author Tomas Frkan
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDao;
    @Autowired
    private Mapper mapper;

    public void setCustomerDao(CustomerDAO customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("customerDto");
        }
        Customer customer = mapper.map(customerDto, Customer.class);
        customerDao.createCustomer(customer);
        return mapper.map(customer, CustomerDTO.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or "
            + "(hasRole('ROLE_USER') and principal.customer.id == #customerDto.id)")
    @Override
    public void deleteCustomer(CustomerDTO customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("customerDto");
        }
        if (customerDto.getId() == null) {
            throw new IllegalArgumentException("Unable to remove CustomerDTO with null ID");
        }
        Customer customer = mapper.map(customerDto, Customer.class);
        customerDao.deleteCustomer(customer);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or "
            + "(hasRole('ROLE_USER') and principal.customer.id == #customerDto.id)")
    @Override
    public CustomerDTO updateCustomer(final CustomerDTO customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("customerDto");
        }

        Customer customer = customerDao.findCustomer(customerDto.getId());
        customer.setCity(customerDto.getCity());
        customer.setCountry(customerDto.getCountry());
        customer.setEmail(customerDto.getEmail());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setStreet(customerDto.getStreet());
        customer.setTelephoneNumber(customerDto.getTelephoneNumber());
        customer.setZipCode(customerDto.getZipCode());

        List<Delivery> deliveries = new ArrayList<Delivery>() {
            {
                for (DeliveryDTO deliveryDto : customerDto.getDeliveries()) {
                    add(mapper.map(deliveryDto, Delivery.class));
                }
            }
        };


        customer.setDeliveries(deliveries);
        return mapper.map(customerDao.updateCustomer(customer), CustomerDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> list = new ArrayList<>();
        for (Customer customer : customerDao.getAllCustomers()) {
            list.add(mapper.map(customer, CustomerDTO.class));
        }
        return list;
    }

    @PostAuthorize("hasRole('ROLE_ADMIN') or "
            + "(hasRole('ROLE_USER') and returnObject != null and principal.customer.id == returnObject.id)")
    @Transactional(readOnly = true)
    @Override
    public CustomerDTO findCustomer(Long id) {
        if (id == null) {
            throw new NullPointerException("id");
        }
        Customer customerFromDB = customerDao.findCustomer(id);
        if (customerFromDB == null) {
            return null;
        }
        return mapper.map(customerFromDB, CustomerDTO.class);
    }

    @Override
    public CustomerDTO findByUsername(String username) {
        if (username == null) {
            throw new NullPointerException("null username");
        }
        if (username.trim().isEmpty()) {
            throw new IllegalArgumentException("empty username");
        }
        Customer customer = customerDao.findByUsername(username);
        CustomerDTO customerFromDB = mapper.map(customer, CustomerDTO.class);
        return customerFromDB;
    }
}
