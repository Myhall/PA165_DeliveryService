/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import java.util.List;

/**
 *
 * @author janvorcak
 */
public interface CourierService {
    
    CourierDTO createCourier(CourierDTO courier);
    
    void deleteCourier(CourierDTO courier);
    
    CourierDTO updateCourier(CourierDTO courier);
    
    List<CourierDTO> getAllCouriers(boolean include_deleted);
    
    List<CourierDTO> getAllCouriers();
    
    CourierDTO findCourier(Long id);
    
    CourierDTO findByUsername(String username);
    
}
