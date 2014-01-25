package cz.muni.fi.pa165.deliveryservice.rest.server;

import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
@Component
@Path("/customers")
public class CustomerResource {
    @Autowired
    public CustomerService customerService;
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers(false);
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public CustomerDTO getCustomerById(@PathParam("id") Long id) {
        CustomerDTO customer = customerService.findCustomer(id);
        if(customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return customer;
    } 
    
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        CustomerDTO customer = customerService.findCustomer(id);
        if(customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        customerService.deleteCustomer(customer);
    }
    
    @PUT
    @Path("create")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public CustomerDTO createCustomer(CustomerDTO customer) {
        CustomerDTO customerNew;
        
        try {
            customerNew = customerService.createCustomer(customer);
        } catch(DataAccessException ex) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
               
        return customerNew;
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public CustomerDTO updateCustomer(@PathParam("id") Long id, CustomerDTO customer) {
        CustomerDTO oldCustomer = customerService.findCustomer(id);
        if(customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        oldCustomer.setFirstName(customer.getFirstName());
        oldCustomer.setLastName(customer.getLastName());
        oldCustomer.setEmail(customer.getEmail());
        return customerService.updateCustomer(oldCustomer);
    }
}
