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
@Path("/Courier")
public interface RestCourierInterface {
    
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCourier(CourierDTO courier);
    
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCourier(CourierDTO courier);
    
    @DELETE
    @Path("/delete")
    public void deleteCourier(CourierDTO courier);
    
    @GET
    @Path("/getAllCouriers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CourierDTO> getAllCouriers();
    
    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CourierDTO findCourier(@PathParam("id") String id);
    
}
