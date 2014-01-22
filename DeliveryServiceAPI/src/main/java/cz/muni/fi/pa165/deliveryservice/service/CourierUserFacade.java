/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.dto.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.dto.CourierUserDTO;
import cz.muni.fi.pa165.deliveryservice.dto.UserDTO;
import java.util.List;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
public interface CourierUserFacade {
    /**
     * Creates new Courier and User.
     *
     * @param courierDTO Courier transfer object
     * @param userDTO User transfer object
     */
    void create(CourierDTO courierDTO, UserDTO userDTO);

    /**
     * Returns CourierUserDTO by courier's id.
     *
     * @param id of stored CourierUserDTO
     * @return CourierUserDTO transfer object
     */
    CourierUserDTO getByCourierId(Long id);

    /**
     * Finds CourierUserDTO by username
     *
     * @param username lookup parameter for finding user
     * @return found CourierUserDTO object with given username
     */
    CourierUserDTO getByUsername(String username);

    /**
     * Removes given Courier and User represented by given CourierUserDTO object.
     *
     * @param courierUserDTO object representing entities to be removed
     */
    void remove(CourierUserDTO courierUserDTO);

    /**
     * Updates information of given CourierUserDTO.
     *
     * @param courierUserDTO object containing updated information
     */
    void update(CourierUserDTO courierUserDTO);

    /**
     * Finds and returns in list all CourierUserDTO objects.
     *
     * @return List of CourierUserDTO entities
     */
    List<CourierUserDTO> findAll();
}
