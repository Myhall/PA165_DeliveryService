/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.dao.CourierDAO;
import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
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
    public Courier createCourier(String firstName, String lastName, String email) {
        Courier courier = new Courier(firstName, lastName, email);
        courierDao.createCourier(courier);
        return courier;
    }

    @Override
    public void deleteCourier(Courier courier) {
        courierDao.deleteCourier(courier);
    }

    @Override
    public Courier updateCourier(Courier courier) {
        return courierDao.updateCourier(courier);
    }

    @Override
    public List<CourierDTO> getAllCouriers() {
        List<CourierDTO> resultList = new ArrayList<>();
        for(Courier courier : courierDao.getAllCouriers()) {
            resultList.add(mapper.map(courier, CourierDTO.class));
        }
        return resultList;
    }

    @Override
    public CourierDTO findCourier(Long id) {
        return mapper.map(courierDao.findCourier(id), CourierDTO.class);
    }

    public void setCourierDao(CourierDAO courierDao) {
        this.courierDao = courierDao;
    }
     
}
