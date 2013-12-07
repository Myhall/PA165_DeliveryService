/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliverysystem.rest;

import cz.muni.fi.pa165.deliveryservice.rest.server.CustomerDTO;
import org.codehaus.jackson.JsonNode;

/**
 *
 * @author Bufo
 */
public interface RestClientCustomerInterface {

    public void createCustomer(CustomerDTO customerDto);

    public void deleteCustomer(CustomerDTO customerDto);

    public void updateCustomer(CustomerDTO customerDto);

    public void getAllCustomers();

    public JsonNode findCustomer(String id);
}
