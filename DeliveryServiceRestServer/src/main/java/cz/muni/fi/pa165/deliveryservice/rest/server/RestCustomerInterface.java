/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.rest.server;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Bufo
 */
@Path("/Customer")
public interface RestCustomerInterface {

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCustomer(CustomerDTO customerDto);

    @DELETE
    @Path("/delete")
    public void deleteCustomer(CustomerDTO customerDto);

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCustomer(CustomerDTO customerDto);

    @GET
    @Path("/getAllCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDTO> getAllCustomers();

    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDTO findCustomer(@PathParam("id") String id);
}
