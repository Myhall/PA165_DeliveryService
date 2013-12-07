/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliverysystem.rest;

import cz.muni.fi.pa165.deliveryservice.rest.server.CustomerDTO;
import org.codehaus.jackson.JsonNode;
import org.springframework.http.HttpEntity;

/**
 *
 * @author Bufo
 */
public class RestClientCustomerImpl extends RestClient implements RestClientCustomerInterface {

    public RestClientCustomerImpl(String url) {
        super(url);
    }

    @Override
    public void createCustomer(CustomerDTO customer) {
        HttpEntity<CustomerDTO> entity = new HttpEntity<>(customer,headers);
        uri = rt.postForLocation(url.concat("create="), entity);
    }

    @Override
    public void deleteCustomer(CustomerDTO customer) {
        HttpEntity<CustomerDTO> entity = new HttpEntity<>(customer, headers);
        rt.delete(url.concat("delete="), entity);
    }

    @Override
    public void updateCustomer(CustomerDTO customer) {
        HttpEntity<CustomerDTO> entity = new HttpEntity<>(customer, headers);
        rt.put(url.concat("update="), entity);
    }

    @Override
    public void getAllCustomers() {
        JsonNode jsonNode = rt.getForObject(url.concat("/getAllCustomers"), JsonNode.class);
        for (JsonNode m : jsonNode) {
            System.out.println("entry = " + m);
        }
    }

    @Override
    public JsonNode findCustomer(String id) {
        return rt.getForObject(url.concat("find=").concat(id), JsonNode.class);
    }
}
