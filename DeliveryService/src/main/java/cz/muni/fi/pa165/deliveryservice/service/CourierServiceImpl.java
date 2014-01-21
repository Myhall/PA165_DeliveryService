/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.Delivery;
import cz.muni.fi.pa165.deliveryservice.dao.CourierDAO;
import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.dto.DeliveryDTO;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author janvorcak
 */
@Service
@Transactional
public class CourierServiceImpl implements CourierService {

    @Autowired
    private CourierDAO courierDao;
    @Autowired
    private Mapper mapper;

    @Override
    public CourierDTO createCourier(CourierDTO courierDto) {
        if (courierDto == null) {
            throw new NullPointerException();
        }

        Courier courier = mapper.map(courierDto, Courier.class);
        courierDao.createCourier(courier);
        return mapper.map(courier, CourierDTO.class);
    }

    @Override
    public void deleteCourier(CourierDTO courierDto) {
        if (courierDto == null) {
            throw new NullPointerException();
        }

        Courier courier = courierDao.findCourier(courierDto.getId());
        courierDao.deleteCourier(courier);
    }

    @Override
    public CourierDTO updateCourier(final CourierDTO courierDto) {
        if (courierDto == null) {
            throw new NullPointerException();
        }

        Courier courier = courierDao.findCourier(courierDto.getId());
        courier.setEmail(courierDto.getEmail());
        courier.setFirstName(courierDto.getFirstName());
        courier.setLastName(courierDto.getLastName());
        
        List<Delivery> deliveries = new ArrayList<Delivery>(){{
            for(DeliveryDTO deliveryDto : courierDto.getDeliveries()) {
                add(mapper.map(deliveryDto, Delivery.class));
            }
        }};
        
        courier.setDeliveries(deliveries);
        
        return mapper.map(courierDao.updateCourier(courier), CourierDTO.class);
    }

    @Override
    public List<CourierDTO> getAllCouriers() {
        List<CourierDTO> resultList = new ArrayList<>();
        for (Courier courier : courierDao.getAllCouriers()) {
            resultList.add(mapper.map(courier, CourierDTO.class));
        }
        return resultList;
    }

    @Override
    public CourierDTO findCourier(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }

        Courier fromDB = courierDao.findCourier(id);
        if(fromDB == null) {
            return null;
        }
        return mapper.map(fromDB, CourierDTO.class);
    }
    
    @Override
    public CourierDTO findByUsername(String username) {
        if (username == null) {
            throw new NullPointerException("null username");
        }
        if (username.trim().isEmpty()) {
            throw new IllegalArgumentException("empty username");
        }
        Courier courier = courierDao.findByUsername(username);
        CourierDTO courierFromDB = mapper.map(courier, CourierDTO.class);
        return courierFromDB;
    }

    public void setCourierDao(CourierDAO courierDao) {
        this.courierDao = courierDao;
    }
}
