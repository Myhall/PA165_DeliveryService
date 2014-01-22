/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.dto.CourierUserDTO;
import cz.muni.fi.pa165.deliveryservice.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
@Service
@Transactional
public class CourierUserFacadeImpl implements CourierUserFacade{

    @Autowired
    private CourierService cservice;   
    @Autowired
    private UserService uservice;
    
    @Override
    public void create(CourierDTO courierDTO, UserDTO userDTO) {
        if (courierDTO == null) {
            throw new IllegalArgumentException("courier is null");
        }
        if (userDTO == null) {
            throw new IllegalArgumentException("user is null");
        }                
        if (courierDTO.getId() != null) {
            throw new IllegalArgumentException("courierDTO.id is null");
        }
        if (userDTO.getId() != null) {
            throw new IllegalArgumentException("userDTO.id is null");
        }        
        
        uservice.createUser(userDTO);
        courierDTO.setUser(userDTO);
        cservice.createCourier(courierDTO);
    }

    @Override
    public CourierUserDTO getByCourierId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        CourierDTO courier = cservice.findCourier(id);
        UserDTO user = courier.getUser();
        return new CourierUserDTO(courier, user);
    }

    @Override
    public CourierUserDTO getByUsername(String username) {
        UserDTO userDTO = uservice.findByUsername(username);        
        CourierDTO courierDTO = cservice.findByUsername(username);
        
        return new CourierUserDTO(courierDTO, userDTO);
    }

    @Override
    public void remove(CourierUserDTO courierUserDTO) {
        if (courierUserDTO == null) {
            throw new NullPointerException("courierUserDTO is null");
        }
        
        if (courierUserDTO.getCourier().getId() == null) {
            throw new IllegalArgumentException("courier.id is null");
        }
        if (courierUserDTO.getUser().getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        
        uservice.deleteUser(courierUserDTO.getUser());
        cservice.deleteCourier(courierUserDTO.getCourier());        
    }

    @Override
    public void update(CourierUserDTO courierUserDTO) {
        if (courierUserDTO == null) {
            throw new NullPointerException("courierUserDTO is null");
        }
        
        if (courierUserDTO.getCourier().getId() == null) {
            throw new IllegalArgumentException("courier.id is null");
        }
        if (courierUserDTO.getUser().getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        
        UserDTO userDTO = courierUserDTO.getUser();        
        CourierDTO courierDTO = courierUserDTO.getCourier();        
        
        courierDTO.setUser(userDTO);       
        uservice.updateUser(userDTO);
        cservice.createCourier(courierDTO);
    }

    @Override
    public List<CourierUserDTO> findAll() {
        List<CourierDTO> courierDTOList = cservice.getAllCouriers();
        List<CourierUserDTO> cuDTOList = new ArrayList<>();
        for (CourierDTO c : courierDTOList) {
            cuDTOList.add(new CourierUserDTO(c, c.getUser()));
        }
        return cuDTOList;
    }
    
}
