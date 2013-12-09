/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliverysystem.rest;
import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import org.codehaus.jackson.JsonNode;

/**
 *
 * @author Bufo
 */
public interface RestClientCourierInterface {
    
    public void createCourier(CourierDTO courier);
    public void updateCourier(CourierDTO courier);
    public void deleteCourier(CourierDTO courier);
    public void getAllCouriers();
    public JsonNode findCourier(String id);
    
}
