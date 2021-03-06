/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.Customer;
import cz.muni.fi.pa165.deliveryservice.User;
import java.util.List;

/**
 * DAO interface for Courier entity
 *
 * @author Jan Vorcak
 */
public interface CourierDAO {

    /**
     * Creates courier in a database
     * @param courier - courier object to be persisted 
     */
    void createCourier(Courier courier);

    /**
     * Removes courier from a database
     * @param courier - courier object to be removed
     */
    void deleteCourier(Courier courier);

    /**
     * Updates courier object
     * @param courier - courier object to be updated
     */
    Courier updateCourier(Courier courier);

    /**
     * Get all existing couriers
     * @param include_deleted
     * @return list of all courier objects
     */
    List<Courier> getAllCouriers(boolean include_deleted);

    /**
     * Get all existing couriers
     * @return list of all courier objects
     */
    List<Courier> getAllCouriers();
    
    /*
     * Returns specific Courier by id from database.
     * @param id Courier's id
     * @return Courier with given id
     * @throws NullPointerException id is null
     */
    Courier findCourier(Long id);
    /**
     * Returns associated User.
     * @return User associated with given courier
     */
    User getUser(Courier courier);
    /**
     * Find courier by associated User username.
     * @param username of courier
     * @return courier with given username
     */
    Courier findByUsername(String username);
}
