/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.dao.CustomerDAO;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.exceptions.DataPersistenceException;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            Customer customer = mapper.map(customerDto, Customer.class);
            customerDao.createCustomer(customer);
            return mapper.map(customer, CustomerDTO.class);
        } catch (Exception ex) {
            throw new DataPersistenceException("There has been an error creating customer on persistence layer.", ex);
        }
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDto) {
        if (customerDto.getId() == null) {
            throw new IllegalArgumentException("Unable to remove CustomerDTO with null ID");
        }
        try {
            Customer customer = mapper.map(customerDto, Customer.class);
            customerDao.deleteCustomer(customer);
        } catch (Exception ex) {
            throw new DataPersistenceException("There has been an error deleting customer on persistence layer.", ex);
        }
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDto) {
        try {
            Customer customer = mapper.map(customerDto, Customer.class);
            return mapper.map(customerDao.updateCustomer(customer), CustomerDTO.class);
        } catch (Exception ex) {
            throw new DataPersistenceException("There has been an error updating customer on persistence layer.", ex);
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        try {
            List<CustomerDTO> list = new ArrayList<>();
            for (Customer customer : customerDao.getAllCustomers()) {
                list.add(mapper.map(customer, CustomerDTO.class));
            }
            return list;
        } catch (Exception ex) {
            throw new DataPersistenceException("There has been an error creating customer on persistence layer.", ex);
        }
    }

    @Override
    public CustomerDTO findCustomer(Long id) {
        try {
            return mapper.map(customerDao.findCustomer(id), CustomerDTO.class);
        } catch (Exception ex) {
            throw new DataPersistenceException("There has been an error finding customer on persistence layer.", ex);
        }
    }
}
