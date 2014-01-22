/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerUserDTO;
import cz.muni.fi.pa165.deliveryservice.dto.UserDTO;
import java.util.List;

/**
 * Facade covering Customer and User DTOs'.
 * 
 * @author Filip Volner <volner@mail.muni.cz>
 */
public interface CustomerUserFacade {
    /**
     * Creates new Customer and User.
     *
     * @param customerDTO Customer transfer object
     * @param userDTO User transfer object
     */
    void create(CustomerDTO customerDTO, UserDTO userDTO);

    /**
     * Returns CustomerUserDTO by customer's id.
     *
     * @param id of stored CustomerUserDTO
     * @return CustomerUserDTO transfer object
     */
    CustomerUserDTO getByCustomerId(Long id);

    /**
     * Finds CustomerUserDTO by username
     *
     * @param username lookup parameter for finding user
     * @return found CustomerUserDTO object with given username
     */
    CustomerUserDTO getByUsername(String username);

    /**
     * Removes given Customer and User represented by given CustomerUserDTO object.
     *
     * @param customerUserDTO object representing entities to be removed
     */
    void remove(CustomerUserDTO customerUserDTO);

    /**
     * Updates information of given CustomerUserDTO.
     *
     * @param customerUserDTO object containing updated information
     */
    void update(CustomerUserDTO customerUserDTO);

    /**
     * Finds and returns in list all CustomerUserDTO objects.
     *
     * @return List of CustomerUserDTO entities
     */
    List<CustomerUserDTO> findAll();
}
