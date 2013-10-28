/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.delivery.service;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.dao.CourierDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author janvorcak
 */
public class CourierServiceImpl implements CourierService {

    private CourierDAO courierDao;
    
    @Override
    public Courier createCourier(String firstName, String lastName, String email) {
        Courier courier = new Courier(firstName, lastName, email);
        System.out.println(courierDao);
        courierDao.createCourier(courier);
        return null;
    }

    public CourierDAO getCourierDao() {
        return courierDao;
    }

    @Autowired
    public void setCourierDao(CourierDAO courierDao) {
        this.courierDao = courierDao;
    }
    
    
    
}
