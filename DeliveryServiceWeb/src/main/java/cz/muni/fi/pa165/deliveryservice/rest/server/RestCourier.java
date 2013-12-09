/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.rest.server;

import cz.muni.fi.pa165.deliveryservice.service.CourierServiceImpl;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 *
 * @author Jan Vorcak
 */

@Path("/couriers")
public class RestCourier {

    @SpringBean
    public CourierServiceImpl courierService;
    
    @GET
    @Produces("text/plain")
    public String getText() {
        if(courierService == null) 
            return "Null";
        else
            return "Not null!";
    }
    
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CourierDTO> getAll() {
        return courierService.getAllCouriers();
    }
    */

}
