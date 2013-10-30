/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.main.AbstractIntegrationTest;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 
 */
public class CourierServiceTest extends AbstractIntegrationTest {
    
    @Autowired
    CourierService courierService;
    
    public CourierServiceTest() {
    }
   
    @Ignore
    @Test
    public void testCreateCourier() {
        CourierDTO courier = mock(CourierDTO.class);
        courier.setId(null);
        courier.setFirstName("Jan");
        courier.setLastName("Vorcak");
        courier.setEmail("vorcak@gmail.com");
        
        // mockito forces couriers's id value = 0 instead of null, that makes
        // the test fail
        System.out.println("courier id = " + courier.getId());
        courierService.createCourier(courier);
        System.out.println(courierService.getAllCouriers());
    }
    
    
}
