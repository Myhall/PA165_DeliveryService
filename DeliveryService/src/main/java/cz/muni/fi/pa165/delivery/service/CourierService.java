/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.delivery.service;

import cz.muni.fi.pa165.deliveryservice.Courier;
import java.util.List;

/**
 *
 * @author janvorcak
 */
public interface CourierService {
    
    Courier createCourier(String firstName, String lastName, String email);
    
    void deleteCourier(Courier courier);
    
    Courier updateCourier(Courier courier);
    
    List<Courier> getAllCouriers();
    
    Courier findCourier(Long id);
    
}
