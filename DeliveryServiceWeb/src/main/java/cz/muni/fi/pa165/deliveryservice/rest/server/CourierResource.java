/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.rest.server;

import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
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
 * @author Jan Vorcak
 */

@Component
@Path("/couriers")
public class CourierResource {

    @Autowired
    public CourierService courierService;
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<CourierDTO> getAllCouriers() {
        return courierService.getAllCouriers();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public CourierDTO getCourierById(@PathParam("id") Long id) {
        CourierDTO courier = courierService.findCourier(id);
        if(courier == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return courier;
    } 
    
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        CourierDTO courier = courierService.findCourier(id);
        if(courier == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        courierService.deleteCourier(courier);
    }
    
    @PUT
    @Path("create")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public CourierDTO createCourier(CourierDTO courier) {
        CourierDTO courierNew;
        
        try {
            courierNew = courierService.createCourier(courier);
        } catch(DataAccessException ex) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
               
        return courierNew;
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public CourierDTO updateCourier(@PathParam("id") Long id, CourierDTO courier) {
        CourierDTO oldCourier = courierService.findCourier(id);
        if(courier == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        oldCourier.setFirstName(courier.getFirstName());
        oldCourier.setLastName(courier.getLastName());
        oldCourier.setEmail(courier.getEmail());
        return courierService.updateCourier(oldCourier);
    }
}
