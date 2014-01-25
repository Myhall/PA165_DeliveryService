/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import java.util.List;

/**
 * Customer service interface.
 * @author Tomas Frkan
 */
public interface CustomerService {

    /**
     * Creates new customer.
     *
     * @param customerDto Customer's dto.
     * @return CustomerDTO created customer data transfer object.
     */
    CustomerDTO createCustomer(CustomerDTO customerDto);

    /**
     * Deletes customer.
     *
     * @param customerDto Customer to remove.
     */
    void deleteCustomer(CustomerDTO customerDto);

    /**
     * Updates customer.
     *
     * @param customer Customer to update.
     * @return CustomerDTO updated customer dto.
     */
    CustomerDTO updateCustomer(CustomerDTO customerDto);

    /**
     * Returns list of all customers.
     *
     * @return List<CustomerDTO> list of all customers
     */
    List<CustomerDTO> getAllCustomers();
    
    /**
     * Returns list of all customers.
     *
     * @return List<CustomerDTO> list of all customers
     */
    List<CustomerDTO> getAllCustomers(boolean includeDeleted);

    /**
     * Returns customer with given id.
     *
     * @param id customer's id
     * @return CustomerDTO with given id
     */
    CustomerDTO findCustomer(Long id);
    
    /**
     * Returns customer with given username.
     *
     * @param username username of customer
     * @return customer with given username
     */
    CustomerDTO findByUsername(String username);
}
