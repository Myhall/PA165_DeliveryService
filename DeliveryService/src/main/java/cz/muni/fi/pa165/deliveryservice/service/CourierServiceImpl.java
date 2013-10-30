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
    public CourierDTO createCourier(CourierDTO courierDto) {
        Courier courier = mapper.map(courierDto, Courier.class);
        courierDao.createCourier(courier);
        return mapper.map(courier, CourierDTO.class);
    }

    @Override
    public void deleteCourier(CourierDTO courierDto) {
        //TODO throw exception if no id found
        Courier courier = courierDao.findCourier(courierDto.getId());
        courierDao.deleteCourier(courier);
    }

    @Override
    public CourierDTO updateCourier(CourierDTO courierDto) {
        Courier courier = courierDao.findCourier(courierDto.getId());
        return mapper.map(courierDao.updateCourier(courier), CourierDTO.class);
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
