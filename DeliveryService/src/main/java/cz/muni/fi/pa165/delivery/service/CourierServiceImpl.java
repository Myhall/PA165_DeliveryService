/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.delivery.service;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.dao.CourierDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author janvorcak
 */
@Transactional
public class CourierServiceImpl implements CourierService {

    @Autowired
    private CourierDAO courierDao;
    
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
    public List<Courier> getAllCouriers() {
        return courierDao.getAllCouriers();
    }

    @Override
    public Courier findCourier(Long id) {
        return courierDao.findCourier(id);
    }

    public void setCourierDao(CourierDAO courierDao) {
        this.courierDao = courierDao;
    }
     
}
