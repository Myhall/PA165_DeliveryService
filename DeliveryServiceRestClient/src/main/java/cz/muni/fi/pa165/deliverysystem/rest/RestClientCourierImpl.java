/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliverysystem.rest;

import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import org.codehaus.jackson.JsonNode;
import org.springframework.http.HttpEntity;

/**
 *
 * @author Bufo
 */
public class RestClientCourierImpl extends RestClient implements RestClientCourierInterface {

    public RestClientCourierImpl(String url) {
        super(url);
    }

    @Override
    public void createCourier(CourierDTO courier) {
        HttpEntity<CourierDTO> entity = new HttpEntity<>(courier, headers);
        uri = rt.postForLocation(url.concat("create"), entity);
    }

    @Override
    public void updateCourier(CourierDTO courier) {
        HttpEntity<CourierDTO> entity = new HttpEntity<>(courier, headers);
        rt.put(url.concat("update"), entity);
    }

    @Override
    public void deleteCourier(CourierDTO courier) {
        HttpEntity<CourierDTO> entity = new HttpEntity<>(courier, headers);
        rt.delete(url.concat("delete="), entity);
    }

    @Override
    public void getAllCouriers() {
        JsonNode jsonNode = rt.getForObject(url.concat("/getAllCouriers"), JsonNode.class);
        for (JsonNode m : jsonNode) {
            System.out.println("entry = " + m);
        }
    }

    @Override
    public JsonNode findCourier(String id) {
        return rt.getForObject(url.concat("find=").concat(id), JsonNode.class);
    }
}
