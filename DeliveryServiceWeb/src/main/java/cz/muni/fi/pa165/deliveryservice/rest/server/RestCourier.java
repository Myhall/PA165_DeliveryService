/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.rest.server;

import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Vorcak
 */

@Component
@Path("/couriers")
public class RestCourier {

    @Autowired
    public CourierService courierService;
    
    @GET
    @Produces("text/plain")
    public String getText() {
        if(courierService == null) 
            return "Null";
        else
            return "Not null!";
    }
    
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CourierDTO> getAll() {
        return courierService.getAllCouriers();
    }
    

}
