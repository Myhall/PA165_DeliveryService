/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.dto.CustomerDTO;
import cz.muni.fi.pa165.deliveryservice.dto.CustomerUserDTO;
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
public class CustomerUserFacadeImpl implements CustomerUserFacade {

    @Autowired
    private CustomerService cservice;   
    @Autowired
    private UserService uservice;
    
    @Override
    public void create(CustomerDTO customerDTO, UserDTO userDTO) {
        if (customerDTO == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (userDTO == null) {
            throw new IllegalArgumentException("user is null");
        }                
        if (customerDTO.getId() != null) {
            throw new IllegalArgumentException("customerDTO.id is null");
        }
        if (userDTO.getId() != null) {
            throw new IllegalArgumentException("userDTO.id is null");
        }        
        
        uservice.create(userDTO);
        customerDTO.setUser(userDTO);
        cservice.createCustomer(customerDTO);
    }

    @Override
    public CustomerUserDTO getByCustomerId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        CustomerDTO customer = cservice.findCustomer(id);
        UserDTO user = customer.getUser();
        return new CustomerUserDTO(customer, user);
    }

    @Override
    public CustomerUserDTO getByUsername(String username) {
        UserDTO userDTO = uservice.getByUsername(username);        
        CustomerDTO customerDTO = cservice.findByUsername(username);
        
        return new CustomerUserDTO(customerDTO, userDTO);
    }

    @Override
    public void remove(CustomerUserDTO customerUserDTO) {
        if (customerUserDTO == null) {
            throw new NullPointerException("customerUserDTO is null");
        }
        
        if (customerUserDTO.getCustomer().getId() == null) {
            throw new IllegalArgumentException("customer.id is null");
        }
        if (customerUserDTO.getUser().getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        
        uservice.remove(customerUserDTO.getUser());
        cservice.deleteCustomer(customerUserDTO.getCustomer());        
    }

    @Override
    public void update(CustomerUserDTO customerUserDTO) {
        if (customerUserDTO == null) {
            throw new NullPointerException("customerUserDTO is null");
        }
        
        if (customerUserDTO.getCustomer().getId() == null) {
            throw new IllegalArgumentException("customer.id is null");
        }
        if (customerUserDTO.getUser().getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        
        UserDTO userDTO = customerUserDTO.getUser();        
        CustomerDTO customerDTO = customerUserDTO.getCustomer();        
        
        customerDTO.setUser(userDTO);       
        uservice.update(userDTO);
        cservice.createCustomer(customerDTO);
    }

    @Override
    public List<CustomerUserDTO> findAll() {
        List<CustomerDTO> customerDTOList = cservice.getAllCustomers();
        List<CustomerUserDTO> cuDTOList = new ArrayList<>();
        for (CustomerDTO c : customerDTOList) {
            cuDTOList.add(new CustomerUserDTO(c, c.getUser()));
        }
        return cuDTOList;
    }
    
}
