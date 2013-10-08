/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.Courier;
import java.util.List;

/**
 * DAO interface for Courier entity
 *
 * @author Jan Vorcak
 */
public interface CourierDAO {

    void createCourier(Courier courier);

    void deleteCourier(Courier courier);

    void updateCourier(Courier courier);

    List<Courier> getAllCouriers();

}
